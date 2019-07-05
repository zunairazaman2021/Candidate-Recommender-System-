
package com.example.zunairazamanchaudh.candidateengine;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zunairazamanchaudh.candidateengine.Adapters.listoffollowersAdapter;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterFollowers;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.detailjobposted2Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewRecruiterFollowersList extends AppCompatActivity {
String recruiterid;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recruiter_followers_list);
        recruiterid=getIntent().getStringExtra("intent_recruiterid");
        recyclerView=(RecyclerView)findViewById(R.id.viewfollowersRecycler);
        showfollowerslist();
    }
    ProgressDialog progressDialog;
    List<RecruiterFollowers> list=new ArrayList<>();
    private void showfollowerslist(){
        DatabaseReference databaseReference;
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading...");

        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("RecruiterFollowers").child(recruiterid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    RecruiterFollowers mjobs = dataSnapshot.getValue(RecruiterFollowers.class);

                    list.add(mjobs);
                }
                listoffollowersAdapter adapter = new listoffollowersAdapter(getApplicationContext(), list);

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
