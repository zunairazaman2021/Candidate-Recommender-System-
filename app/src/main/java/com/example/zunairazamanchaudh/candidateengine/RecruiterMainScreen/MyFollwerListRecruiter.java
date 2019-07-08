package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.listofjobseekerfollowing;
import com.example.zunairazamanchaudh.candidateengine.Adapters.listoffollowersAdapter;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterFollowers;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterUser;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyFollwerListRecruiter extends AppCompatActivity {
    String cvid;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_follwer_list_recruiter);
        cvid=getIntent().getStringExtra("intent_id");
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
                .child("Followers");
               Query q=databaseReference.orderByChild("user_id").equalTo(cvid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    RecruiterFollowers mjobs = dataSnapshot.getValue(RecruiterFollowers.class);
                    list.add(mjobs);
                }
                listofjobseekerfollowing adapter = new listofjobseekerfollowing(getApplicationContext(), list);

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
