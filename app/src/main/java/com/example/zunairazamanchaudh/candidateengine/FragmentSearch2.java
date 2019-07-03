package com.example.zunairazamanchaudh.candidateengine;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.FilterAdapter2;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.Post;
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


public class FragmentSearch2 extends Fragment {

RecyclerView filterjobRecycler2;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    List<Post> list = new ArrayList<>();
     FilterAdapter2 adapter;

    public FragmentSearch2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_search2, container, false);
        filterjobRecycler2=(RecyclerView)view.findViewById(R.id.filterjobRecycler2);
        filterjobRecycler2.setHasFixedSize(true);
//
        filterjobRecycler2.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressDialog = new ProgressDialog(getActivity());

       progressDialog.setMessage("Loading Jobs...");

        progressDialog.show();
        showjobsInit();

        return  view;
    }
private void showjobsInit(){
    databaseReference = FirebaseDatabase.getInstance().getReference()
            .child("Posts");
    databaseReference.addValueEventListener(new ValueEventListener() {
     @Override
       public void onDataChange(DataSnapshot snapshot) {

     for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

     Post mjobs = dataSnapshot.getValue(Post.class);

     list.add(mjobs);
     }

     adapter = new FilterAdapter2(getActivity(), list);

     filterjobRecycler2.setAdapter(adapter);

     progressDialog.dismiss();
        }

        //@Override
        public void onCancelled(DatabaseError databaseError) {

          progressDialog.dismiss();

        }
    });
}
}
