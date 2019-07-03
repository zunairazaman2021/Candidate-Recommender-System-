package com.example.zunairazamanchaudh.candidateengine;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterCompany;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterUser;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.detailjobposted2Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecruiterProfileUI extends AppCompatActivity {
ImageView imageView;
TextView textViewName,textView2place;
TextView textView3posts,textView4answers;
TextView textView7profession,textView9company,textView11phone;
RecyclerView recyclerView;
ProgressDialog progressDialog;
List<JobPost> list = new ArrayList<>();
String creatorid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_profile_ui);
        creatorid=getIntent().getStringExtra("intent_vr");
        imageView=(ImageView)findViewById(R.id.imageView);
        textViewName=(TextView)findViewById(R.id.textView);
        textView2place=(TextView)findViewById(R.id.textView2);
        textView3posts=(TextView)findViewById(R.id.textView3);
        textView4answers=(TextView)findViewById(R.id.textView4);
        textView7profession=(TextView)findViewById(R.id.textView7);
        textView9company=(TextView)findViewById(R.id.textView9);
        textView11phone=(TextView)findViewById(R.id.textView11);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclePostedJobs);

        showSpecificJobs();
        showsimpleDetails();

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
}
