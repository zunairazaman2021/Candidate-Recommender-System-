package com.example.zunairazamanchaudh.candidateengine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.AdressJobseeker;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterCompany;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterFollowers;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterUser;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.detailjobposted2Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecruiterProfileUI extends AppCompatActivity {
ImageView imageView;
int iz=0;
Button imagebutton4followme;
ImageButton imagebutton5listFollowers;
TextView textViewName,textView2place;
TextView textView3posts,textView4answers;
TextView textView7profession,textView9company,textView11phone;
RecyclerView recyclerView;
ProgressDialog progressDialog;
TextView textView4followers;
List<JobPost> list = new ArrayList<>();
String creatorid;


//follower data variables

    private String firstname;
    private String lastname;
    private String profile_image;
    private String security_level;
    private String dob;
    private String phone;
    private String email;
    private String nationality;

    private String city;
    private String country;
    private String state;
    private String zipcode;
    private String rfirstname;
    private String rlastname;
    private String rphone;
    private String remail;
    private String rimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_profile_ui);
        creatorid=getIntent().getStringExtra("intent_vr");
        imageView=(ImageView)findViewById(R.id.imageView);
        imagebutton5listFollowers=(ImageButton)findViewById(R.id.imageButton5);
        imagebutton5listFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RecruiterProfileUI.this,ViewRecruiterFollowersList.class);
                i.putExtra("intent_recruiterid",creatorid);
                startActivity(i);
            }
        });
        textView4followers=(TextView)findViewById(R.id.textView4);
        textViewName=(TextView)findViewById(R.id.textView);
        textView2place=(TextView)findViewById(R.id.textView2);
        textView3posts=(TextView)findViewById(R.id.textView3);
        textView4answers=(TextView)findViewById(R.id.textView4);
        textView7profession=(TextView)findViewById(R.id.textView7);
        textView9company=(TextView)findViewById(R.id.textView9);
        textView11phone=(TextView)findViewById(R.id.textView11);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclePostedJobs);
        imagebutton4followme=(Button)findViewById(R.id.imageButton4);
        imagebutton4followme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //    if(imagebutton4followme.getText().toString().equals("Follow me")){
            DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child(creatorid);

            Query q  = ref.orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                unfollowRecruiter();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
                  followForJob();
            //  }else if(imagebutton4followme.getText().toString().equals("Following")){
                //  unfollowRecruiter();
             // }
            }
        });
        showSpecificJobs();
        showsimpleDetails();
       // alreadyfollowinginit();
        getfollowerdata();
        getNoofFollowers();
    }
    private void showsimpleDetails(){

        DatabaseReference databaseReference;
        DatabaseReference databaseReference2;
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("RecruiterUser");

        Query queryA=databaseReference.orderByKey().equalTo(creatorid);
        queryA.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    RecruiterUser mjobs = dataSnapshot1.getValue(RecruiterUser.class);
                    textViewName.setText(mjobs.getFirstname()+" "+mjobs.getLastname());
                    textView11phone.setText(mjobs.getPhone());
                    if(mjobs.getProfile_image().equals("")){

                    }else{
                        Picasso.get().load(mjobs.getProfile_image()).into(imageView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference2 = FirebaseDatabase.getInstance().getReference()
                .child("company");
        Query queryC=databaseReference2.orderByKey().equalTo(creatorid);
        queryC.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {

                    RecruiterCompany mjobs = dataSnapshot2.getValue(RecruiterCompany.class);
                    textView9company.setText(mjobs.getCompanyName());
                    textView7profession.setText(mjobs.getJobRole());
                    textView2place.setText(mjobs.getCity()+", "+mjobs.getCountry());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
private void showSpecificJobs(){

    DatabaseReference databaseReference;
    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    progressDialog = new ProgressDialog(this);

    progressDialog.setMessage("Loading...");

    progressDialog.show();
    databaseReference = FirebaseDatabase.getInstance().getReference()
            .child("JobPost").child(creatorid);
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {

            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                JobPost mjobs = dataSnapshot.getValue(JobPost.class);
                list.add(mjobs);
            }
            textView3posts.setText(list.size()+"\nPosts");
            detailjobposted2Adapter adapter = new detailjobposted2Adapter(getApplicationContext(), list);

            recyclerView.setAdapter(adapter);

            progressDialog.dismiss();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

            progressDialog.dismiss();

        }
    });
}
    List<RecruiterFollowers> listfollowers=new ArrayList<>();
private void getNoofFollowers(){
        DatabaseReference databaseReference;
    databaseReference = FirebaseDatabase.getInstance().getReference()
            .child("RecruiterFollowers").child(creatorid);
           Query q= databaseReference.orderByChild("followingstatus").equalTo("true");
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {

            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                RecruiterFollowers mjobs = dataSnapshot.getValue(RecruiterFollowers.class);

                listfollowers.add(mjobs);
            }
            textView4followers.setText(listfollowers.size()+"\nFollowers");
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}
private void getfollowerdata(){
DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
    Query query1 = reference.child(getString(R.string.dbnode_users))
            .orderByKey()
            .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
    query1.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.getValue()!=null){
                //this loop will return a single result
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                    users user=singleSnapshot.getValue(users.class);
                    firstname=user.getFirstname();
                    lastname=user.getLastname();
                    profile_image=user.getProfile_image();
                    security_level=user.getSecurity_level();
                    dob=user.getDob();
                    nationality=user.getNationality();
                    phone=user.getPhone();
                    email=user.getEmail();

                }}else {}
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

    Query query2 = reference.child(getString(R.string.dbnode_addressDetails))
            .orderByKey()
            .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
    query2.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.getValue()!=null){
                //this loop will return a single result
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                    AdressJobseeker user=singleSnapshot.getValue(AdressJobseeker.class);
                    city=user.getCity();
                    state=user.getState();
                    country=user.getCountry();
                    zipcode=String.valueOf(user.getZipcode());
                            }}else {}
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

}
String key1;
String key;
    private void followForJob(){
        RecruiterFollowers ja=new RecruiterFollowers();
      //  getfollowerdata();
        ja.setRecruiterid(creatorid);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        final String strDate = mdformat.format(calendar.getTime());
        ja.setFolloweddate(strDate);
        ja.setFollowingstatus("true");
      //  ja.setProfile_image(profile_image);
       // ja.setCity(city);
       // ja.setCountry(country);
        //ja.setEmail(email);
        //ja.setDob(dob);
        //ja.setFirstname(firstname);
        //ja.setLastname(lastname);
        //ja.setNationality(nationality);
        //ja.setPhone(phone);
        //ja.setSecurity_level(security_level);
        //ja.setState(state);
        //ja.setZipcode(zipcode);
        ja.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
       key1=mDatabase.child("Followers").push().getKey();
        // ja.setFollowid(key);
        key=FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.child("Followers")
                .child(key1)
                .setValue(ja);
        FirebaseDatabase.getInstance().getReference()
                .child("RecruiterFollowers")
                .child(creatorid)
                .child(key)
                .setValue(ja).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
                Query query1 = reference.child(getString(R.string.dbnode_users))
                        .orderByKey()
                        .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue()!=null){
                            //this loop will return a single result
                            for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                                users user=singleSnapshot.getValue(users.class);
                                firstname=user.getFirstname();
                                lastname=user.getLastname();
                                profile_image=user.getProfile_image();
                                security_level=user.getSecurity_level();
                                dob=user.getDob();
                                nationality=user.getNationality();
                                phone=user.getPhone();
                                email=user.getEmail();
                                Map<String, Object> postValues = new HashMap<String,Object>();
                                Map<String, Object> postValues1 = new HashMap<String,Object>();
                                Map<String, Object> postValues2 = new HashMap<String,Object>();
                                Map<String, Object> postValues3 = new HashMap<String,Object>();
                                Map<String, Object> postValues4 = new HashMap<String,Object>();
                                Map<String, Object> postValues5 = new HashMap<String,Object>();
                                Map<String, Object> postValues6 = new HashMap<String,Object>();
                                Map<String, Object> postValues7 = new HashMap<String,Object>();
                                postValues.put("profile_image",profile_image);
                                postValues1.put("firstname",firstname);
                                postValues2.put("lastname",lastname);
                                postValues3.put("dob",dob);
                                postValues4.put("nationality",nationality);
                                postValues5.put("phone",phone);
                                postValues6.put("email",email);
                                postValues7.put("security_level",security_level);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues);
                         FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues1);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues1);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues2);
                               FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues2);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues3);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues3);


                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues4);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues4);


                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues5);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues5);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues6);
                               FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues6);


                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues7);
                               FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues7);

                            }}else {}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query2 = reference.child(getString(R.string.dbnode_addressDetails))
                        .orderByKey()
                        .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue()!=null){
                            //this loop will return a single result
                            for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                                AdressJobseeker user=singleSnapshot.getValue(AdressJobseeker.class);
                                city=user.getCity();
                                state=user.getState();
                                country=user.getCountry();
                                zipcode=String.valueOf(user.getZipcode());
                                Map<String, Object> postValues = new HashMap<String,Object>();
                                Map<String, Object> postValues1 = new HashMap<String,Object>();
                                Map<String, Object> postValues2 = new HashMap<String,Object>();
                                Map<String, Object> postValues3 = new HashMap<String,Object>();
                                postValues.put("country",country);
                                postValues1.put("city",city);
                                postValues2.put("state",state);
                                postValues3.put("zipcode",zipcode);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues);


                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues1);
                                 FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues1);


                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues2);
                                  FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues2);


                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues3);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues3);
                            }}else {}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query query3 = reference.child("RecruiterUser")
                        .orderByKey()
                        .equalTo(creatorid);
                query3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue()!=null){
                            //this loop will return a single result
                            for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                                RecruiterUser user=singleSnapshot.getValue(RecruiterUser.class);
                                rfirstname=user.getFirstname();
                                rlastname=user.getLastname();
                                rphone=user.getPhone();
                                remail=user.getEmail();
                                rimage=user.getProfile_image();
                                Map<String, Object> postValues = new HashMap<String,Object>();
                                Map<String, Object> postValues1 = new HashMap<String,Object>();
                                Map<String, Object> postValues2 = new HashMap<String,Object>();
                                Map<String, Object> postValues3 = new HashMap<String,Object>();
                                Map<String, Object> postValues4 = new HashMap<String,Object>();

                                postValues.put("rfirstname",rfirstname);
                                postValues1.put("rlastname",rlastname);
                                postValues2.put("rphone",rphone);
                                postValues3.put("remail",remail);
                                postValues4.put("rimage",rimage);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues);


                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues1);
                             FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues1);


                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues2);
                               FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues2);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues3);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues3);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(postValues4);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(postValues4);

                                Map<String,Object> map=new HashMap<String ,Object>();
                                Map<String,Object> map1=new HashMap<String ,Object>();
                                Map<String,Object> map2=new HashMap<String ,Object>();
                                Map<String,Object> map3=new HashMap<String ,Object>();
                                Map<String,Object> map4=new HashMap<String ,Object>();
                                map.put("recruiterid",creatorid);
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
                                String strDatee = mdformat.format(calendar.getTime());
                                map1.put("followeddate",strDatee);
                                map2.put("followingstatus","true");
                                map3.put("followid",FirebaseAuth.getInstance().getCurrentUser().getUid());
                                map4.put("user_id",FirebaseAuth.getInstance().getCurrentUser().getUid());
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(map);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(map);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(map1);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(map1);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(map2);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(map2);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(map3);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(map3);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("RecruiterFollowers")
                                        .child(creatorid)
                                        .child(key)
                                        .updateChildren(map4);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Followers")
                                        .child(key1)
                                        .updateChildren(map4);






                            }}else {}

                        imagebutton4followme.setText("Following");
                        iz=1;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //imagebutton4followme.setImageResource(R.drawable.followed);
                //imagebutton4followme.setClickable(false);
                Toast.makeText(RecruiterProfileUI.this,"Congratulations you are following this recruiter to get job updates",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RecruiterProfileUI.this,"Could not follow this recruiter",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void alreadyfollowing(){
        DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
        Query query= mDatabase.child("RecruiterFollowers").child(creatorid).orderByChild("user_id")
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    imagebutton4followme.setText("Following");
                    //imagebutton4followme.setImageResource(R.drawable.followed);
                    unfollowRecruiter();
                    Toast.makeText(RecruiterProfileUI.this,"You are already following this recruiter",Toast.LENGTH_SHORT).show();
                }else if(dataSnapshot==null){
                    followForJob();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void alreadyfollowinginit(){
        DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
        Query query= mDatabase.child("RecruiterFollowers")
                .child(creatorid).orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                      imagebutton4followme.setText("Following");
                      iz=1;
                      Toast.makeText(RecruiterProfileUI.this,"You are already following this recruiter"
                              ,Toast.LENGTH_SHORT).show();
                      }
                    }
                    }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void unfollowRecruiter(){
        final DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
        Query query= mDatabase.child("RecruiterFollowers").child(creatorid)
                .orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        final Query query1=mDatabase.child("Followers").orderByChild("user_id")
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                           appleSnapshot.getRef().removeValue();
                    /*    Map<String,Object> map=new HashMap<>();
                        map.put("followingstatus","false");
                        mDatabase.child("Followers").updateChildren(map);

                        mDatabase.child("RecruiterFollowers").child(creatorid)
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                iz=0;
                                imagebutton4followme.setText("Follow me");
                                Toast.makeText(RecruiterProfileUI.this, "Unfollowed successfully", Toast.LENGTH_SHORT).show();
                            }
                        });*/
                       }
                }if(dataSnapshot==null){
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        appleSnapshot.getRef().removeValue();
                     }
                }if(dataSnapshot==null){
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
