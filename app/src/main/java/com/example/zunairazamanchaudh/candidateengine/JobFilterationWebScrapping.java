package com.example.zunairazamanchaudh.candidateengine;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zunairazamanchaudh.candidateengine.Adapters.weightedfiltrationjob;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.weightjobs;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.CVFolderAdapter2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobFilterationWebScrapping extends AppCompatActivity {
RecyclerView recyclerfilterweb;
String jobid;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    List<weightjobs> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_filteration_web_scrapping);
        recyclerfilterweb=(RecyclerView)findViewById(R.id.recyclerfilterweb);
        jobid=getIntent().getStringExtra("web_jid");
        ////////////////////////////////////

        recyclerfilterweb.setHasFixedSize(true);

        recyclerfilterweb.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading Data...");

        progressDialog.show();
        databaseReference= FirebaseDatabase.getInstance().getReference()
                .child("JobFilterationWebScrapping");
        Query q=databaseReference.orderByChild("jobsearchqueryid").equalTo(jobid);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    weightjobs mjobs = dataSnapshot1.getValue(weightjobs.class);
                    list.add(mjobs);
                }
                 weightedfiltrationjob adapter = new weightedfiltrationjob(list,JobFilterationWebScrapping.this);

                recyclerfilterweb.setAdapter(adapter);

                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        ////////////////////////////////////
    }
}
