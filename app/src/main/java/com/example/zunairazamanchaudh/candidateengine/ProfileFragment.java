package com.example.zunairazamanchaudh.candidateengine;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPreference;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.UploadPicture;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.education;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.experience;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.skills;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.WelcomeRecruiter;
import com.example.zunairazamanchaudh.candidateengine.util.UniversalImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener {
StorageReference imagepath,imagepath222;
FirebaseDatabase database;
DatabaseReference ref;

    private static final double MB_THRESHHOLD = 5.0;
    private static final double MB = 1000000.0;

    private Uri mSelectedImageUri;
    private Bitmap mSelectedImageBitmap;
    private byte[] mBytes;
    private double progress;

    public void uploadNewPhoto(Uri imageUri){
        /*
            upload a new profile photo to firebase storage
         */
        Log.d(TAG, "uploadNewPhoto: uploading new profile photo to firebase storage.");

        //Only accept image sizes that are compressed to under 5MB. If thats not possible
        //then do not allow image to be uploaded
        BackgroundImageResize resize = new BackgroundImageResize(null);
        resize.execute(imageUri);
    }
    public class BackgroundImageResize extends AsyncTask<Uri, Integer, byte[]> {

        Bitmap mBitmap;
        public BackgroundImageResize(Bitmap bm) {
            if(bm != null){
                mBitmap = bm;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog();
            Toast.makeText(getActivity(), "compressing image", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected byte[] doInBackground(Uri... params ) {
            Log.d(TAG, "doInBackground: started.");

            if(mBitmap == null){

                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), params[0]);
                    Log.d(TAG, "doInBackground: bitmap size: megabytes: " + mBitmap.getByteCount()/MB + " MB");
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: IOException: ", e.getCause());
                }
            }

            byte[] bytes = null;
            for (int i = 1; i < 11; i++){
                if(i == 10){
                    Toast.makeText(getActivity(), "That image is too large.", Toast.LENGTH_SHORT).show();
                    break;
                }
                bytes = getBytesFromBitmap(mBitmap,100/i);
                Log.d(TAG, "doInBackground: megabytes: (" + (11-i) + "0%) "  + bytes.length/MB + " MB");
                if(bytes.length/MB  < MB_THRESHHOLD){
                    return bytes;
                }
            }
            return bytes;
        }


        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            hideDialog();
            mBytes = bytes;
            //execute the upload
            executeUploadTask();
        }
    }
    // convert from bitmap to byte array
    public static byte[] getBytesFromBitmap(Bitmap bitmap, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        return stream.toByteArray();
    }








    public ProfileFragment() {
        // Required empty public constructor
    }

    public static final int  GALLERY_INTENT = 5467;//random number
    public static final int CAMERA_REQUEST_CODE = 8352;//random number



    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
    }
    TextView nameP,date22lastupdated,cv,dateebirth,national,residence,editEmailP,editPhoneP;
    TextView fpsdesignation,fpscountry,fpscity,fpssalary,fpsindustry,fpscareer,fpsempType;
    TextView txtskillsuser,txtlanguageuser1;
    //experience
    TextView fpedesignationout,fpeorganizationout;
    //education
    TextView fpeCourseout,fpeInstituteout;
    ProgressBar mProgressbarprofile;
    CircleImageView imageView21P;
    Button button22PUpload,btnskill,btnlanguage,btnjobalert;
    ImageButton editpersonalinfo,editcontactinfo,editpreferedjobinfo,editskillinfo,
            editlanguageinfo,editrecomendinfo,editexperienceinfo,editeduinfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);


        imagepath= FirebaseStorage.getInstance().getReference().child("Profiling");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("uploadFotos");

        imagepath222= FirebaseStorage.getInstance().getReference("Profilejobseekers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("profile_image");


        fpedesignationout =(TextView)view.findViewById(R.id.fpedesignationout);
        fpeorganizationout=(TextView)view.findViewById(R.id.fpeorganization);
        fpeCourseout=(TextView)view.findViewById(R.id.fpeCourseout);
        fpeInstituteout=(TextView)view.findViewById(R.id.fpeInstituteout);
        //editimagebuttons
        editpersonalinfo=(ImageButton)view.findViewById(R.id.editpersonalinfo);
        editpersonalinfo.setOnClickListener(this);
        editcontactinfo=(ImageButton)view.findViewById(R.id.editcontactinfo);
        editcontactinfo.setOnClickListener(this);
        editpreferedjobinfo=(ImageButton)view.findViewById(R.id.editpreferedjobinfo);
        editpreferedjobinfo.setOnClickListener(this);
        editskillinfo=(ImageButton)view.findViewById(R.id.editskillinfo);
        editskillinfo.setOnClickListener(this);
        editlanguageinfo=(ImageButton)view.findViewById(R.id.editlanguageinfo);
        editlanguageinfo.setOnClickListener(this);
        editrecomendinfo=(ImageButton)view.findViewById(R.id.editrecomendinfo);
        editrecomendinfo.setOnClickListener(this);
        editeduinfo=(ImageButton)view.findViewById(R.id.editeduinfo);
        editeduinfo.setOnClickListener(this);
        editexperienceinfo=(ImageButton)view.findViewById(R.id.editexperienceinfo);
        editexperienceinfo.setOnClickListener(this);

        /////////
        fpscareer=(TextView)view.findViewById(R.id.fpscareer);
        nameP=(TextView)view.findViewById(R.id.nameP);
        cv=(TextView)view.findViewById(R.id.cvP);
        date22lastupdated=(TextView)view.findViewById(R.id.date22);
        dateebirth=(TextView)view.findViewById(R.id.dateebirth);
        national=(TextView)view.findViewById(R.id.national);
        residence=(TextView)view.findViewById(R.id.residence);
        editEmailP=(TextView)view.findViewById(R.id.editEmailP);
        editPhoneP=(TextView)view.findViewById(R.id.editPhoneP);
        //jobpreference
        fpsdesignation=(TextView)view.findViewById(R.id.fpsdesignation);
        fpscountry=(TextView)view.findViewById(R.id.fpscountry);
        fpscity=(TextView)view.findViewById(R.id.fpscity);
        fpssalary=(TextView)view.findViewById(R.id.fpsalary);
        fpsindustry=(TextView)view.findViewById(R.id.fpsindustry);
        fpsempType=(TextView)view.findViewById(R.id.fpsemptype);
        //skills
        txtskillsuser=(TextView)view.findViewById(R.id.txtskillsuser);
        //languages
        txtlanguageuser1=(TextView)view.findViewById(R.id.txtlanguageuser1);
        imageView21P=(CircleImageView)view.findViewById(R.id.imageView21P);

        button22PUpload=(Button)view.findViewById(R.id.button22P);
        button22PUpload.setOnClickListener(this);
        btnlanguage=(Button)view.findViewById(R.id.button25);
        btnlanguage.setOnClickListener(this);
        btnskill=(Button)view.findViewById(R.id.button24);
        btnskill.setOnClickListener(this);
        btnjobalert=(Button)view.findViewById(R.id.button26);
        btnjobalert.setOnClickListener(this);
        mProgressbarprofile=(ProgressBar)view.findViewById(R.id.progressBarProfile);
        setupFirebaseAuth();
        getUserAccountData();
        getUserPreferenceData();
        getUserExperienceData();
        getUserEducationData();
        getUserSkillsData();
       //loadimagefromdb();
//      loadWithGlide();
    //    showUploadedImage();
//downloadURL();
//  picpic();
        displayprofilephoto();
        hideSoftKeyboard();
        return view;
    }
private  void displayprofilephoto(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
}
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button26:
             Intent i=new Intent(getActivity(),JobAlertsJobseeker.class);
             startActivity(i);
                break;
            case R.id.button24:
                Intent ii=new Intent(getActivity(),SkillsEdit.class);
                startActivity(ii);
                break;
            case R.id.button25:
                Intent iil=new Intent(getActivity(),languagesKnow.class);
                startActivity(iil);

                break;
            case R.id.button22P:
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
                break;
            case R.id.editpersonalinfo:
                Intent ei=new Intent(getActivity(),EditPersonalInfoJobseeker.class);
                startActivity(ei);
                break;
            case R.id.editcontactinfo:
                break;
            case R.id.editlanguageinfo:
                Intent iil1=new Intent(getActivity(),languagesKnow.class);
                startActivity(iil1);
                break;
            case R.id.editpreferedjobinfo:
                Jobpreferencedialog jobpreferencedialog=new Jobpreferencedialog();
                jobpreferencedialog.show(getFragmentManager(),"dialog_job_preferences");
                break;
            case R.id.editskillinfo:
                Intent iei=new Intent(getActivity(),SkillsEdit.class);
                startActivity(iei);
                break;
            case R.id.editrecomendinfo:
                Intent iir=new Intent(getActivity(),SearchWebScrappedJobs.class);
                startActivity(iir);
                break;
            case R.id.editexperienceinfo:
                break;
            case R.id.editeduinfo:
                break;
            default:
                break;
        }
    }


    private void updateUserPhoto(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_users))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
//                    updateExistingPhoto();
                }else{
     //               saveFirstTimeFoto();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

/*
    private void updateExistingPhoto() {
                  DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                  Map<String, Object> postValues = new HashMap<String,Object>();
        postValues.put("profile_image",imagepath.toString());
            reference.child(getString(R.string.dbnode_users))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("profile_image").updateChildren(postValues);
    }
  */
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    DatabaseReference mDatabaseRef;
    public void showUploadedImage(){
        DatabaseReference reer;
        reer= FirebaseDatabase.getInstance().getReference("uploadFoto")
        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
        .child("profile_image");

        reer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadPicture upload = postSnapshot.getValue(UploadPicture.class);
                    //mUploads.add(upload);
                    Context mContext=getActivity();
                    Picasso.get()
                            .load(upload.getFotopath())
                            .fit()
                            .centerCrop()
                            .into(imageView21P);
                //    Uri uri=Uri.parse(upload);
                    Toast.makeText(getActivity(),"displayed :"+upload.toString(),Toast.LENGTH_SHORT).show();

                          //  Picasso.with(mContext)
                    //        .load(upload.getFotopath())
                      //      .placeholder(R.mipmap.ic_launcher)
                        //    .fit()
                          //  .centerCrop()
                            //.into(imageView21P);
                }

                //mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);

               // mRecyclerView.setAdapter(mAdapter);
               // mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               // Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
               // mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }
Uri uriimage;

    private void executeUploadTask(){
        showDialog();
      final StorageReference storageReference = imagepath.child(System.currentTimeMillis()
                + "." + getFileExtension(mSelectedImageUri));

        if(mBytes.length/MB < MB_THRESHHOLD) {

            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("image/jpg")
                    .setContentLanguage("en") //see nodes below
                    .setCustomMetadata("Mitch's special meta data", "JK nothing special here")
                    .setCustomMetadata("location", "Iceland")
                    .build();
            //if the image size is valid then we can submit to database
            UploadTask uploadTask = null;
//            uploadTask = storageReference.putBytes(mBytes, metadata);
            uploadTask = storageReference.putBytes(mBytes); //without metadata

            storageReference.putFile(mSelectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                    DatabaseReference imagestore=FirebaseDatabase.getInstance().getReference().child("uploadFoto")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("profile_image",String.valueOf(uri));
                    imagestore.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                       Toast.makeText(getActivity(),"Image Uploaded",Toast.LENGTH_LONG).show();
                        }
                    });

                    //users table
                            DatabaseReference imagestore1=FirebaseDatabase.getInstance().getReference().child("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            HashMap<String,Object> hashMap1=new HashMap<>();
                            hashMap1.put("profile_image",String.valueOf(uri));
                            imagestore1.updateChildren(hashMap1);
                      ///////

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(getActivity(), "could not upload photo", Toast.LENGTH_SHORT).show();

                    hideDialog();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double currentProgress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    if(currentProgress > (progress + 15)){
                        progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        Log.d(TAG, "onProgress: Upload is " + progress + "% done");
                        Toast.makeText(getActivity(), progress + "%", Toast.LENGTH_SHORT).show();
                    }

                }
            });

          }else{
            Toast.makeText(getActivity(), "Image is too Large", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

                    if(requestCode == GALLERY_INTENT && resultCode == Activity.RESULT_OK){
            Uri selectedImageUri = data.getData();
            mSelectedImageUri=data.getData();
            //Log.d(TAG, "onActivityResult: image: " + selectedImageUri);
            imageView21P.setImageURI(selectedImageUri);
           //             ImageLoader.getInstance().displayImage(selectedImageUri.toString(),imageView21P);
     uploadNewPhoto(selectedImageUri);
        //    Picasso.with(this).load(selectedImageUri).into(imageView21P);
                      //  StorageReference fileReference = imagepath.child(System.currentTimeMillis()
                        //        + "." + getFileExtension(selectedImageUri))
                 /*       StorageReference fileReference = imagepath.child(System.currentTimeMillis()
                                + "." + getFileExtension(selectedImageUri));


                        UploadTask uploadTask = null;
                        uploadTask = fileReference.putBytes(mBytes);

                        fileReference.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressbarprofile.setProgress(0);
                        }
                    },5000);
                  Toast.makeText(getActivity(),"Image Uploaded",Toast.LENGTH_SHORT).show();
                    //Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    Uri firebaseURL = taskSnapshot.getStorage().getDownloadUrl().getResult();
                    UploadPicture upload = new UploadPicture(firebaseURL.toString());
                    String uploadId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String pp=imagepath222.getDownloadUrl().toString();
                   // String photosaver= String.valueOf(uriimage);
                    FirebaseDatabase.getInstance().getReference()
                            .child("uploadFoto")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("profile_image")
                            .setValue(firebaseURL.toString());

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(),"Could not Upload Image",Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    mProgressbarprofile.setProgress((int)progress);
                }
            });*/

//            getDialog().dismiss();

        }

        else if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Log.d(TAG, "onActivityResult: done taking a photo.");

            Bitmap bitmap;
            bitmap = (Bitmap) data.getExtras().get("data");

            imageView21P.setImageBitmap(bitmap);
         //   getDialog().dismiss();
        }
    }


private void picpic(){
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    Query query1 = reference.child("uploadFoto")
            .orderByKey()
            .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
    query1.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.getValue()!=null){
                //this loop will return a single result
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: (QUERY METHOD 1) found user: "
                            + singleSnapshot.getValue(users.class).toString());
                    UploadPicture user=singleSnapshot.getValue(UploadPicture.class);
                   ImageLoader.getInstance().displayImage(user.getFotopath(),imageView21P);
                 //   Picasso.get().load(user.getFotopath()).into(imageView21P);
                }}else {
                Toast.makeText(getActivity(),"No photos ",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

}
    private static String TAG="ProfileFragment";
    private void getUserAccountData(){
        Log.d(TAG, "getUserAccountData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_users))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
if(dataSnapshot.getValue()!=null){
                //this loop will return a single result
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: (QUERY METHOD 1) found user: "
                            + singleSnapshot.getValue(users.class).toString());
                    users user=singleSnapshot.getValue(users.class);
                    nameP.setText(user.getFirstname()+" "+user.getLastname());
                    dateebirth.setText(user.getDob());
                    national.setText(user.getNationality());
                    editPhoneP.setText(user.getPhone());
                    editEmailP.setText(user.getEmail());
//                    imageView21P.setImageURI(user.getProfile_image().toString());
                     if(user.getProfile_image().equals("")){

                     }else {
                         Picasso.get().load(user.getProfile_image()).into(imageView21P);
                     }
                }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

      //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    public void loadWithGlide() {
        // [START storage_load_with_glide]
        // Reference to an image file in Cloud Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("Profilejobseekers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("profile_image");
              //  .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        // ImageView in your Activity

        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)
        GlideApp.with(getActivity() /* context */)
                .load(storageReference)
                .into(imageView21P);
        // [END storage_load_with_glide]
    }
    private void getUserPreferenceData(){
        Log.d(TAG, "getUserPreferenceData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
             FirebaseDatabase.getInstance().getReference()
            .child("job")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .setValue(eduuser)
         */
        Query query1 = reference.child(getString(R.string.dbnode_jobpreference))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
if(dataSnapshot.getValue()!=null) {
    //this loop will return a single result
    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
        Log.d(TAG, "onDataChange: (QUERY METHOD 1) found user: "
                + singleSnapshot.getValue(JobPreference.class).toString());
        JobPreference jpuser = singleSnapshot.getValue(JobPreference.class);
        fpsdesignation.setText(jpuser.getDesignation());
        fpscountry.setText(jpuser.getCountry());
        fpscity.setText(jpuser.getCity());
        fpssalary.setText(jpuser.getSalary());
        fpsindustry.setText(jpuser.getIndustry());
        fpscareer.setText(jpuser.getCareerlevel());
        fpsempType.setText(jpuser.getEmploymenttype());
    }
}else{}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void getUserExperienceData(){
        Log.d(TAG, "getUserExperienceData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
             FirebaseDatabase.getInstance().getReference()
            .child("job")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .setValue(eduuser)
         */
        Query query1 = reference.child(getString(R.string.dbnode_experience))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){

                    //this loop will return a single result
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: (QUERY METHOD 1) found user: "
                            + singleSnapshot.getValue(experience.class).toString());
                    experience jpuser=singleSnapshot.getValue(experience.class);
                    fpedesignationout.setText(jpuser.getDesignation());
                    fpeorganizationout.setText(jpuser.getOrganization());
                   }}
            else{}}


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }


    private void getUserEducationData(){

        Log.d(TAG, "getUserEducationData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
             FirebaseDatabase.getInstance().getReference()
            .child("job")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .setValue(eduuser)
         */
        Query query1 = reference.child(getString(R.string.dbnode_education))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
if(dataSnapshot.getValue()!=null) {
    //this loop will return a single result
    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
        Log.d(TAG, "onDataChange: (QUERY METHOD 1) found user: "
                + singleSnapshot.getValue(education.class).toString());
        education eduuser = singleSnapshot.getValue(education.class);
        fpeCourseout.setText(eduuser.getCourse());
        fpeInstituteout.setText(eduuser.getInstitute());
    }
}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }


    private void getUserSkillsData(){
        Log.d(TAG, "getUserEducationData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
             FirebaseDatabase.getInstance().getReference()
            .child("job")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .setValue(eduuser)
         */
        Query query1 = reference.child(getString(R.string.dbnode_skills))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
if(dataSnapshot.getValue()!=null){

                //this loop will return a single result
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
                    Log.d(TAG, "onDataChange: (QUERY METHOD 1) found user: "
                            + singleSnapshot.getValue(skills.class).toString());
                    skills skillsuser = singleSnapshot.getValue(skills.class);
                  //   txtskillsuser.setText();
                }
                }else{
    Log.d(TAG, "getUserSkillsData: getting the user's account information does not exist");
         }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    //firebase
    private FirebaseAuth.AuthStateListener mAuthListener;
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: started.");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: " + user.getEmail());


                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(getActivity(), "Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginJobSeeker.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                // ...
            }
        };


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    private void showDialog(){
        mProgressbarprofile.setVisibility(View.VISIBLE);

    }

    private void hideDialog(){
        if(mProgressbarprofile.getVisibility() == View.VISIBLE){
            mProgressbarprofile.setVisibility(View.INVISIBLE);
        }
    }

    private void hideSoftKeyboard(){
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
