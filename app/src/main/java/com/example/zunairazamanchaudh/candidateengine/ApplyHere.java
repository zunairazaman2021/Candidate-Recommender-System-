package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.AdressJobseeker;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.coverletter;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ApplyHere extends AppCompatActivity {
    EditText clname,clcompanyname,clbody,cldate,claddress;
    Button save;

    //jobapplication user

    private String firstname;
    private String lastname;
    private String profile_image;
    private String security_level;
    private String dob;
    private String phone;
    private String email;
    private String nationality;
    //user address
    private String city;
    private String country;
    private String state;
    private String zipcode;
    //jobapplication jobPost
    private String companyName,yourName,jcity,jcountry,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_here);
        cldate=(EditText)findViewById(R.id.datetoday);
        clcompanyname=(EditText)findViewById(R.id.clcompanyname);
        clname=(EditText)findViewById(R.id.clname);
        clbody=(EditText)findViewById(R.id.clbody);
        claddress=(EditText)findViewById(R.id.claddress);
        save=(Button)findViewById(R.id.savecl1);
        jobid=getIntent().getStringExtra("ah_jobid");
        creatorid=getIntent().getStringExtra("ah_createrid");
        getUser();
        Toast.makeText(ApplyHere.this,jobid+"            "+creatorid,Toast.LENGTH_LONG).show();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              saveCV();
              //applyForJob();
            }
        });
    }
    private void getUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child("coverletter")
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        coverletter user=singleSnapshot.getValue(coverletter.class);
                        cldate.setText(user.getDate());
                        clname.setText(user.getRecruitername());
                        clcompanyname.setText(user.getCompanyname());
                        claddress.setText(user.getAddress());
                        clbody.setText(user.getBody());
                    }}else {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
                    String strDate = mdformat.format(calendar.getTime());
                    cldate.setText(strDate);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }


    private void saveCV(){
        final coverletter sauser=new coverletter();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        sauser.setDate(cldate.getText().toString());
        sauser.setRecruitername(clname.getText().toString());
        sauser.setCompanyname(clcompanyname.getText().toString());
        sauser.setAddress(claddress.getText().toString());
        sauser.setBody(clbody.getText().toString());
        sauser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        //getUser();
        //final String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference()
                .child("coverletter")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   redirectjbpreferenceFragment();
                // Intent i=new Intent(ApplyHere.this,WelcomeCandidate.class);
                //startActivity(i);
                Toast.makeText(ApplyHere.this,"Cover letter saved!",Toast.LENGTH_SHORT).show();
            applyForJob();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(ApplyHere.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
    String key,jobid,creatorid;
    private void applyForJob(){


        //////////////////////////////////////////////////////


        DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
        JobApplication ja=new JobApplication();
        ja.setApplicationstatus("submitted");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        ja.setApplicationDate(strDate);
        ja.setCv_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ja.setJob_id(jobid);
        ja.setUseridd(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ja.setRecruiter_id(creatorid);
        key=mDatabase.child("AllApplications").push().getKey();
      //  key=FirebaseAuth.getInstance().getCurrentUser().getUid();
        ja.setApplication_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        final String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.child("AllApplications")
                .child(key)
                .setValue(ja);
        FirebaseDatabase.getInstance().getReference()
                .child("jobApplication")
                .child(creatorid)
                .child(jobid)
                .child(key)
                .setValue(ja).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

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
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues1);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues1);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues2);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues2);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues3);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues3);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues4);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues4);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues5);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues5);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues6);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues6);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues7);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues7);


                            }}else {}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                 Toast.makeText(ApplyHere.this,"DB error :"+databaseError.toString(),Toast.LENGTH_LONG).show();
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
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues1);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues1);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues2);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues2);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues3);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues3);

                            }}else {}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query3 = reference.child("Posts")
                        .orderByKey()
                        .equalTo(jobid);
                query3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue()!=null){
                            //this loop will return a single result
                            for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                                JobPost user=singleSnapshot.getValue(JobPost.class);
                                jcity=user.getCity();
                                yourName=user.getJobtitle();
                                jcountry=user.getCountry();
                                companyName=user.getCompanyName();
                                status=String.valueOf(user.getStatus());
                                Map<String, Object> postValues = new HashMap<String,Object>();
                                Map<String, Object> postValues1 = new HashMap<String,Object>();
                                Map<String, Object> postValues2 = new HashMap<String,Object>();
                                Map<String, Object> postValues3 = new HashMap<String,Object>();
                                Map<String, Object> postValues4 = new HashMap<String,Object>();
                                postValues.put("jcountry",jcountry);
                                postValues1.put("jcity",jcity);
                                postValues2.put("yourName",yourName);
                                postValues3.put("companyName",companyName);
                                postValues4.put("status",status);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues1);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues1);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues2);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues2);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues3);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues3);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("AllApplications")
                                        .child(key)
                                        .updateChildren(postValues4);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("jobApplication")
                                        .child(creatorid)
                                        .child(jobid)
                                        .child(key)
                                        .updateChildren(postValues4);


                            }}else {}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

//           applyForJob();
                Toast.makeText(ApplyHere.this,"Successfully applied for job vacancy",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(ApplyHere.this,WelcomeCandidate.class);
                startActivity(i);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ApplyHere.this,"Could not apply for Job vacancy",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
