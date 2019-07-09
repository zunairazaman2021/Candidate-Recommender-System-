package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPreference;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterCompany;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterFollowers;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterUser;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.experience;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.LoginJobSeeker;
import com.example.zunairazamanchaudh.candidateengine.ProfileFragment;
import com.example.zunairazamanchaudh.candidateengine.R;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecruiterProfileFragment extends Fragment implements View.OnClickListener{
ProgressBar mProgressbarprofile;
CircleImageView imageViewRecruiter;
Button btnuploadRecruiter;
ImageView editRecruiter,editcompanyInfo;
TextView nameRecruiter,showphone,showemail,showplace;
TextView showcompanyName,showjobrole,showceoname,showheadHR,
        showgroupOfCompany,showIndustryname,showownershipType,showCompanydescription;
    public RecruiterProfileFragment() {
        // Required empty public constructor
    }


    DatabaseReference mDatabaseRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_recruiter_profile, container, false);
        editRecruiter=(ImageView)view.findViewById(R.id.editrecruiterinfo);
        editcompanyInfo=(ImageView)view.findViewById(R.id.editCompanyinfo);
        imagepath= FirebaseStorage.getInstance().getReference().child("Profiling");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("uploadFotos");

        imagepath222= FirebaseStorage.getInstance().getReference("Profilejobseekers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("profile_image");

        btnuploadRecruiter=(Button)view.findViewById(R.id.buttonFotoRecruiter);
        editRecruiter.setOnClickListener(this);
        editcompanyInfo.setOnClickListener(this);
        btnuploadRecruiter.setOnClickListener(this);
        imageViewRecruiter=(CircleImageView)view.findViewById(R.id.imageViewRecruiter);
        ////TextViews
        nameRecruiter=(TextView)view.findViewById(R.id.nameRecruiter);
        showphone=(TextView)view.findViewById(R.id.show_phone);
        showemail=(TextView)view.findViewById(R.id.show_email);
        //company views
        showplace=(TextView)view.findViewById(R.id.show_place);
        showcompanyName=(TextView)view.findViewById(R.id.show_companyName);
        showjobrole=(TextView)view.findViewById(R.id.show_jobRole);
        showceoname=(TextView)view.findViewById(R.id.show_ceoName);
        showheadHR=(TextView)view.findViewById(R.id.show_headHRdepartment);
        showgroupOfCompany=(TextView)view.findViewById(R.id.show_groupOfCompany);
        showIndustryname=(TextView)view.findViewById(R.id.show_industryName);
        showownershipType=(TextView)view.findViewById(R.id.show_ownershiptype);
        showCompanydescription=(TextView)view.findViewById(R.id.show_companyDescription);
        mProgressbarprofile=(ProgressBar)view.findViewById(R.id.progressBarProfileViewR);
        setupFirebaseRecruiterAuth();
        getRecruiterAccountData();
        getUserCompanyData();
        hideSoftKeyboard();
    return view;
    }

    private static final double MB_THRESHHOLD = 5.0;
    private static final double MB = 1000000.0;

    private Uri mSelectedImageUri;
    private Bitmap mSelectedImageBitmap;
    private byte[] mBytes;
    private double progress;

    public static final int  GALLERY_INTENT = 5467;//random number
    public static final int CAMERA_REQUEST_CODE = 8352;//random number
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonFotoRecruiter:
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);

                break;
            case R.id.editrecruiterinfo:
                break;
            case R.id.editCompanyinfo:
                Intent i=new Intent(getActivity(),EditCompanyDetails.class);
                startActivity(i);
                break;
        }
    }

    private void followForJob(){
        RecruiterFollowers ja=new RecruiterFollowers();
        ja.setRecruiterid(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ja.setFolloweddate("");
        ja.setFollowingstatus("false");
        ja.setUser_id("000");
        ja.setFollowid("000");
        ja.setFirstname("");
        ja.setLastname("");
        ja.setDob("");
        ja.setNationality("");
        ja.setPhone("");
        ja.setEmail("");
        ja.setSecurity_level("");
        ja.setCountry("");
        ja.setCity("");
        ja.setZipcode("");
        ja.setState("");
        ja.setProfile_image("");
        ja.setRimage("");
        ja.setRfirstname("");
        ja.setRlastname("");
        ja.setRemail("");
        ja.setRphone("");
        FirebaseDatabase.getInstance().getReference()
                .child("RecruiterFollowers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("000")
                .setValue(ja).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    private void getRecruiterAccountData(){
        Log.d(TAG, "getRecruiterAccountData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
         */
        Query query1 = reference.child(getString(R.string.dbnode_Recruiterusers))
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
                        RecruiterUser user=singleSnapshot.getValue(RecruiterUser.class);
                        nameRecruiter.setText(user.getFirstname()+" "+user.getLastname());
                        showemail.setText(user.getEmail());
                        showphone.setText(user.getPhone());
                        if(user.getProfile_image().equals("")){

                        }else {
                            Picasso.get()
                                    .load(user.getProfile_image())
                                    .fit()
                                    .centerCrop()
                                    .into(imageViewRecruiter);
                        }

                    }}else
                            {
                            }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void getUserCompanyData(){
        Log.d(TAG, "getUserPreferenceData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
             FirebaseDatabase.getInstance().getReference()
            .child("job")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .setValue(eduuser)
         */
        Query query1 = reference.child(getString(R.string.dbnode_Company))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
                    //this loop will return a single result
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Log.d(TAG, "onDataChange: (QUERY METHOD 1) found company: "
                                + singleSnapshot.getValue(JobPreference.class).toString());
                        RecruiterCompany jpuser = singleSnapshot.getValue(RecruiterCompany.class);
                        showcompanyName.setText(jpuser.getCompanyName());
                        showjobrole.setText(jpuser.getJobRole());
                        showceoname.setText(jpuser.getCeoName());
                        showgroupOfCompany.setText(jpuser.getGroupOfCompany());
                        showheadHR.setText(jpuser.getHeadHRdepartment());
                        showIndustryname.setText(jpuser.getIndustryName());
                        showownershipType.setText(jpuser.getOwnershipType());
                        showplace.setText(jpuser.getCity()+", "+jpuser.getCountry());
                        showCompanydescription.setText(jpuser.getCompanyDescription());
                    }
                }else{}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }



    private static String TAG="RecruiterProfile";
    //firebase
    private FirebaseAuth.AuthStateListener mAuthListener;
    private void setupFirebaseRecruiterAuth(){
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
                    Intent intent = new Intent(getActivity(), LoginRecruiter.class);
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




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == Activity.RESULT_OK){
            Uri selectedImageUri = data.getData();
            mSelectedImageUri=data.getData();
            //Log.d(TAG, "onActivityResult: image: " + selectedImageUri);
            imageViewRecruiter.setImageURI(selectedImageUri);
            //             ImageLoader.getInstance().displayImage(selectedImageUri.toString(),imageView21P);
            uploadNewPhoto(selectedImageUri);
            //    Picasso.with(this).load(selectedImageUri).into(imageView21P);

        }

        else if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Log.d(TAG, "onActivityResult: done taking a photo.");

            Bitmap bitmap;
            bitmap = (Bitmap) data.getExtras().get("data");

            imageViewRecruiter.setImageBitmap(bitmap);
            //   getDialog().dismiss();
        }
    }


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

    Uri uriimage;

    StorageReference imagepath,imagepath222;
    FirebaseDatabase database;
    DatabaseReference ref;
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
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
                            DatabaseReference imagestore1=FirebaseDatabase.getInstance().getReference().child("RecruiterUser")
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



}
