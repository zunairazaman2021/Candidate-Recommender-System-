package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.CVsFilterationAdapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.SearchCV2Adapter;
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

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class CVDisplay2 extends Fragment implements View.OnClickListener {
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    List<CVTitle> list = new ArrayList<>();

MaterialSpinner spincv2;
Switch swwCV2;
RecyclerView filtercv2recycler;
    private static final int NUM_COLUMNS=1;
    public CVDisplay2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cvdisplay2, container, false);
        spincv2=(MaterialSpinner)view.findViewById(R.id.spinCV2);
        swwCV2=(Switch)view.findViewById(R.id.swwCV2);
        filtercv2recycler=(RecyclerView)view.findViewById(R.id.filtercv2Recycler);
       // filtercv2recycler.setHasFixedSize(true);

       // filtercv2recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.LayoutManager layoutManager=filtercv2recycler.getLayoutManager();
        if(layoutManager==null){
            filtercv2recycler.setLayoutManager(new GridLayoutManager(filtercv2recycler.getContext(),NUM_COLUMNS));
        }

        progressDialog = new ProgressDialog(getActivity());

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("MyResume");
        Query query=databaseReference.orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CVTitle mjobs = dataSnapshot.getValue(CVTitle.class);
                    list.add(mjobs);
                }
                SearchCV2Adapter adapter=(SearchCV2Adapter) filtercv2recycler.getAdapter();
                adapter = new SearchCV2Adapter(getActivity(), list);
                filtercv2recycler.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
