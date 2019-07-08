package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.detailjobposted2Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostedJobs2Fragment extends Fragment {

    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    List<JobPost> list = new ArrayList<>();

    RecyclerView recyclerView;

    detailjobposted2Adapter adapter ;
    public PostedJobs2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_posted_jobs2, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_postedview);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressDialog = new ProgressDialog(getActivity());

        progressDialog.setMessage("Loading Data from Firebase Database");

//        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("JobPost").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    JobPost mjobs = dataSnapshot.getValue(JobPost.class);

                    list.add(mjobs);
                }

                adapter = new detailjobposted2Adapter(getActivity(), list);

                recyclerView.setAdapter(adapter);

  //              progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

    //            progressDialog.dismiss();

            }
        });

        return view;
    }

}
