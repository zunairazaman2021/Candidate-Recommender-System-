package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.AdressJobseeker;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobPostVacancyActivity;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.WelcomeRecruiter;
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

public class MainCVTitle extends AppCompatActivity implements View.OnClickListener {
Toolbar toolbar;
EditText editTitle;
Button btn;



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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cvtitle);
        toolbar=(Toolbar)findViewById(R.id.toolbarCV);
        toolbar.setTitle("Resume Builder");
        editTitle=(EditText)findViewById(R.id.CVTitlep);
        btn=(Button)findViewById(R.id.createcv);
        btn.setOnClickListener(this);
        getUser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createcv:
                if(editTitle.getText().toString()!=""){
                    saveCV();
                }else {
                    editTitle.setError("Field cannot be empty");
                }
                break;
            case R.id.cancelcv:
                break;
        }
    }

    private void getUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_MyResume))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        CVTitle user=singleSnapshot.getValue(CVTitle.class);
                        editTitle.setText(user.getCv_title());
                       }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void saveCV(){
        final CVTitle sauser=new CVTitle();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        sauser.setCreatedon(strDate);
        sauser.setCv_title(editTitle.getText().toString());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        getUser();
        final String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference()
                .child("MyResume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   redirectjbpreferenceFragment();


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
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues1);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues2);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues3);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues4);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues5);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues6);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
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
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues);

                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues1);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues2);
                                FirebaseDatabase.getInstance().getReference()
                                        .child("MyResume")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(postValues3);

                            }}else {}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Intent i=new Intent(MainCVTitle.this,CVDashBoard.class);
                startActivity(i);
                Toast.makeText(MainCVTitle.this,"CV created!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainCVTitle.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
