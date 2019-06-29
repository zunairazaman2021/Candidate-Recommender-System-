package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Personalinfo;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.SkillsCV;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.experience;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.ResumeManager.ViewFullCV;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileView2 extends AppCompatActivity implements View.OnClickListener {
CircleImageView profile;
TextView name,locationorg,designation,citycv,dob,marriage,college,
        occupationskill,mobilenumber,country, email,educationcourse;
Button btnviewfullcv;
String cv_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_view2);
        cv_id=getIntent().getStringExtra("intent_cvid");
        profile=(CircleImageView)findViewById(R.id.profile);
        name=(TextView)findViewById(R.id.name);
        locationorg=(TextView)findViewById(R.id.location);
        designation=(TextView)findViewById(R.id.designation);
         citycv=(TextView)findViewById(R.id.citycv);
         dob=(TextView)findViewById(R.id.dob);
         marriage=(TextView)findViewById(R.id.marriage);
         college=(TextView)findViewById(R.id.college);
         occupationskill=(TextView)findViewById(R.id.occupation);
         mobilenumber=(TextView)findViewById(R.id.mobileNumber);
         country=(TextView)findViewById(R.id.countrycv);
         email=(TextView)findViewById(R.id.email);
         educationcourse=(TextView)findViewById(R.id.education);
         btnviewfullcv=(Button)findViewById(R.id.btnviewfullcv);
         btnviewfullcv.setOnClickListener(this);

     //   Toast.makeText(this,"id is : "+cv_id,Toast.LENGTH_LONG).show();
         getDataFromCVTitle(cv_id);
        getDataFromExperience(cv_id);
        getDataFromEducationResume(cv_id);
         getDataFromSkillsResume(cv_id);
       getDataFromPersonalInfoResume(cv_id);
    }
    private void getDataFromCVTitle(String id){

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            Query query1 = reference.child(getString(R.string.dbnode_MyResume))
                    .orderByKey().equalTo(id);
            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue()!=null){
                        //this loop will return a single result
                        for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                            CVTitle user=singleSnapshot.getValue(CVTitle.class);
                            name.setText(user.getFirstname()+" "+user.getLastname());
                            citycv.setText(user.getNationality());
                            country.setText(user.getCity()+", "+user.getCountry());
                            dob.setText(user.getDob());
                            mobilenumber.setText(user.getPhone());
                            email.setText(user.getEmail());
                            //setimage later IA
                        }}else {}
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

    }

    private void getDataFromExperience(String id){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_experience))
                .orderByKey().equalTo(id);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        experience user=singleSnapshot.getValue(experience.class);
                      designation.setText(user.getDesignation());
                      locationorg.setText("at "+user.getOrganization());
                        //setimage later IA
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDataFromPersonalInfoResume(String id){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_personalinfo_Resume))
                .orderByKey().equalTo(id);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Personalinfo user=singleSnapshot.getValue(Personalinfo.class);
                        marriage.setText(user.getMaritalstatus());
                        //setimage later IA
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDataFromEducationResume(String id){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_education_Resume))
                .child(id).limitToFirst(1);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Academic user=singleSnapshot.getValue(Academic.class);
                        educationcourse.setText(user.getName());
                        college.setText(user.getInstituition());
                        //setimage later IA
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getDataFromSkillsResume(String id){
 final ArrayList<String> input=new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child("skills_Resume").child(id).limitToFirst(5);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        SkillsCV user=singleSnapshot.getValue(SkillsCV.class);
                        input.add(user.getSkillName());
                        //setimage later IA
                    }
                occupationskill.setText(input.toString());
                }else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnviewfullcv:
                Intent ii=new Intent(UserProfileView2.this, ViewFullCV.class);
                ii.putExtra("intent_viewFullCV",cv_id);
                startActivity(ii);
                break;
        }
    }
}
