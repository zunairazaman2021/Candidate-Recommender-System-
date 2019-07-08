package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.CVFolderAdapter2;
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
public class CVFolder2 extends Fragment {

RecyclerView recyclerView;
    public CVFolder2() {
        // Required empty public constructor
    }
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    List<JobPost> list = new ArrayList<>();

    List<JobApplication> removelist = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cvfolder2, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleCVFolder);

     //////////////////////////////////////////////////////////////////////////////////////////////

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressDialog = new ProgressDialog(getActivity());

        progressDialog.setMessage("Loading Data...");

        progressDialog.show();
        databaseReference=FirebaseDatabase.getInstance().getReference()
                .child("JobPost").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query q=databaseReference.orderByChild("creator_id")
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    JobPost mjobs = dataSnapshot1.getValue(JobPost.class);
                    list.add(mjobs);
                }
                CVFolderAdapter2 adapter = new CVFolderAdapter2(getActivity(), list);

                    recyclerView.setAdapter(adapter);

                    progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            progressDialog.dismiss();
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////
        return view;
    }
}
