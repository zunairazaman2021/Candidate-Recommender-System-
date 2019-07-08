package com.example.zunairazamanchaudh.candidateengine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.zunairazamanchaudh.candidateengine.Adapters.webjobsAdapter;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.WebJobs;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.detailjobposted2Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobsOnWeb extends AppCompatActivity {
RecyclerView recyclerView;
Button backpresskey;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    List<WebJobs> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_on_web);
        backpresskey=(Button)findViewById(R.id.key1);
        backpresskey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(JobsOnWeb.this,WelcomeCandidate.class);
                startActivity(i);
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.mywebjob);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading Data...");

        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Jobs");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    WebJobs mjobs = dataSnapshot.getValue(WebJobs.class);

                    list.add(mjobs);
                }

              webjobsAdapter adapter = new webjobsAdapter(list,JobsOnWeb.this);

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
