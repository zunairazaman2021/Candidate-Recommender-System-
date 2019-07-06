package com.example.zunairazamanchaudh.candidateengine;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.Adapters.ShowJobsOpen;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.detailjobposted2Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
class OpenJobsApplied extends android.support.v4.app.Fragment {

    public OpenJobsApplied() {
        // Required empty public constructor
    }

    ArrayList<String> a1=new ArrayList<>();
    ArrayList<String> a2=new ArrayList<>();

    public void data2(){
        a1.add("iOS and Android app developer");
        a1.add("Programmer(Job location:Lahore)");
        a1.add("Engineer");
        a1.add("Software Engineer");
        a1.add("Junior Integration Engineer");
        a2.add("MFM Events");
        a2.add("Resettlement solution");
        a2.add("Executive solutions");
        a2.add("Quest search and selection");
        a2.add("I-talent");
        initRecyclerView3();
    }
    public void initRecyclerView3() {
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recylcer1.setLayoutManager(linearLayoutManager2);
        //  RecyclercountryAdapter adapter= new RecyclercountryAdapter(mNames,mimageurls,getContext());
     //   ShowJobsOpen ad=new ShowJobsOpen(a1,a2,getContext());
        //recylcer1.setAdapter(ad);


    }

    RecyclerView recylcer1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_open_jobs_applied,container,false);
        recylcer1=(RecyclerView)view.findViewById(R.id.rec11);
        //data2();
       showjobsapplied();
        return view;
    }
    List<JobApplication> listapp=new ArrayList<>();
    String jobid;
    private void showjobsapplied(){

        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        recylcer1.setLayoutManager(linearLayoutManager2);
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("AllApplications");
        Query query=databaseReference.orderByChild("cv_id")
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    JobApplication mjobs = dataSnapshot.getValue(JobApplication.class);
                    String s=mjobs.getStatus();
                       listapp.add(mjobs);

                }
                ShowJobsOpen adapter = new ShowJobsOpen(listapp,getContext());
                recylcer1.setAdapter(adapter);
        //        adapter.notifyDataSetChanged();
        //        progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

          //      progressDialog.dismiss();

            }
        });
    }
    List<JobPost> listopen=new ArrayList<>();
    private void checkopenJobs(){
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("JobPost");
        Query query=databaseReference.orderByChild("status")
                .equalTo("true");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    JobApplication mjobs = dataSnapshot.getValue(JobApplication.class);

                }


                //        progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //      progressDialog.dismiss();

            }
        });

    }
}

