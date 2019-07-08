package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.Adapters.Fraginsidetab;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.CVFolderAppliedAdapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.SearchCV2Adapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.PostJobVacancy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CandidatesApplicationManagement extends Activity implements View.OnClickListener {
    public ViewPager viewPager;
    public TabLayout tabLayout;
    LayoutInflater inflatee;

    //Attributes
    TextView pjdTitle,pjdCity,pjdViewJobDetail,pjdPostSimilarJob;
    Spinner pjdspinJobStatus;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    //intents
    String jobid,creatorid;

    public CandidatesApplicationManagement() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates_application_management);
        jobid= getIntent().getStringExtra("app_jobid");
        creatorid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        pjdTitle=(TextView)findViewById(R.id.pjdTitle);
        pjdCity =(TextView)findViewById(R.id.pjdCity);
        pjdViewJobDetail=(TextView)findViewById(R.id.pjdViewJobDetail);
        pjdViewJobDetail.setOnClickListener(this);
        pjdPostSimilarJob=(TextView)findViewById(R.id.pjdPostSimilarJob);
        pjdPostSimilarJob.setOnClickListener(this);
        pjdspinJobStatus=(Spinner)findViewById(R.id.pjdspinJobStatus);
        pjdspinJobStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    displayonspin("submitted");
                    break;
                case 1:
                    displayonspin("Hired");
                    break;
                case 2:
                    displayonspin("Shortlisted");
                    break;
                case 3:
                    displayonspin("Under Evaluation");
                    break;
                case 4:
                    displayonspin("Not Interested");
                    break;
            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
              displayonspin("submitted");
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recycleJobApplicants);
        displayjobinfo();
        //displayAppliedCandidates();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pjdPostSimilarJob:
                Intent i=new Intent(CandidatesApplicationManagement.this,
                        JobPostVacancyActivity.class);
                startActivity(i);
                break;
            case R.id.pjdViewJobDetail:
                Intent e=new Intent(CandidatesApplicationManagement.this,
                        UpdatePostedJobVacancy.class);
                e.putExtra("edit_jobid",jobid);
                startActivity(e);
                break;
        }
    }
    List<JobApplication> list=new ArrayList<>();
    private void displayAppliedCandidates(){
        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
        if(layoutManager==null){
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),1));
        }

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading Data ...");

        progressDialog.show();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("jobApplication");
        Query query=databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(jobid).orderByChild("status").equalTo("true");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    JobApplication mjobs = dataSnapshot.getValue(JobApplication.class);
                    if(mjobs.getApplicationstatus().equals("submitted")) {
                        list.add(mjobs);
                    }
                }
                CVFolderAppliedAdapter adapter=(CVFolderAppliedAdapter) recyclerView.getAdapter();
                adapter = new CVFolderAppliedAdapter(CandidatesApplicationManagement.this, list);
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });


    }
    private void displayonspin(final String application){
        list.clear();
        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
        if(layoutManager==null){
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),1));
        }

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading Data ...");

        progressDialog.show();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("jobApplication");
        Query query=databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(jobid).orderByChild("status").equalTo("true");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    JobApplication mjobs = dataSnapshot.getValue(JobApplication.class);
                    if(mjobs.getApplicationstatus().equals(application)) {
                        list.add(mjobs);
                    }
                }
                CVFolderAppliedAdapter adapter=(CVFolderAppliedAdapter) recyclerView.getAdapter();
                adapter = new CVFolderAppliedAdapter(CandidatesApplicationManagement.this, list);
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });


    }

    private void displayjobinfo(){
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("JobPost");
        Query query=databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .orderByChild("jobpost_id").equalTo(jobid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    JobPost mjobs = dataSnapshot.getValue(JobPost.class);
                    pjdTitle.setText(mjobs.getJobtitle());
                    pjdCity.setText(mjobs.getCity()+", "+mjobs.getCountry());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
