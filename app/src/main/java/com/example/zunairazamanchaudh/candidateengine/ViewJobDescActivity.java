package com.example.zunairazamanchaudh.candidateengine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.FilterAdapter2;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewJobDescActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    String jobid,creatorid;
    TextView descText,jobdescription;
    ImageButton show, hide,show1,hide1;
    Button keybackview,applyhere,viewRecruiter;
    TextView titletext2,companytxt2,experience2,vacancytxt2,contrytxt2,mm2,nationaltxt2,edutxt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_desc);
        jobid=getIntent().getStringExtra("z_jobid");
        creatorid=getIntent().getStringExtra("z_createrid");

        viewRecruiter=(Button)findViewById(R.id.viewRecruiter);
        viewRecruiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vr=new Intent(ViewJobDescActivity.this,RecruiterProfileUI.class);
                vr.putExtra("intent_vr",creatorid);
                startActivity(vr);
            }
        });
        keybackview=(Button)findViewById(R.id.keybackview);
        keybackview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        applyhere=(Button)findViewById(R.id.applyhere);
        applyhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        titletext2=(TextView)findViewById(R.id.titletext22);
        companytxt2=(TextView)findViewById(R.id.companytxt22);
        experience2 =(TextView)findViewById(R.id.experience22);
        vacancytxt2=(TextView)findViewById(R.id.vacancytxt22);
        contrytxt2=(TextView)findViewById(R.id.contrytxt22);
        mm2=(TextView)findViewById(R.id.mm22);
        nationaltxt2=(TextView)findViewById(R.id.nationaltxt22);
        edutxt2  =(TextView)findViewById(R.id.edutxt22);
        descText = (TextView)findViewById(R.id.description_text22);
        show = (ImageButton) findViewById(R.id.showv2);
        jobdescription=(TextView)findViewById(R.id.textdescripv2);
        show1 = (ImageButton) findViewById(R.id.show2v2);
        show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show1.setVisibility(View.INVISIBLE);
                hide1.setVisibility(View.VISIBLE);
                jobdescription.setMaxLines(Integer.MAX_VALUE);
            }
        });
        hide1= (ImageButton) findViewById(R.id.hide2v2);
        hide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide1.setVisibility(View.INVISIBLE);
                show1.setVisibility(View.VISIBLE);
                jobdescription.setMaxLines(5);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);
            }
        });
        hide = (ImageButton) findViewById(R.id.hidev2);
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);
            }
        });
        Toast.makeText(this,"jobid :"+jobid,Toast.LENGTH_LONG).show();
        showJobDescription(jobid);
    }
    private void showJobDescription(String jobid){
        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading ...");

        progressDialog.show();
        final DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference()
                .child("Posts");
        //Query query=dref.orderByKey().equalTo("Li1ky71Op4jvgAtH6nb");
        Query query=dref.orderByChild("jobpost_id").equalTo(jobid);
          dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()){
                    JobPost mjobs = dataSnapshot2.getValue(JobPost.class);
                  titletext2.setText(mjobs.getJobtitle());
                   companytxt2.setText(mjobs.getCompanyName());
                   experience2.setText(mjobs.getExperience());
                   String no=String.valueOf(mjobs.getNoOfvacancie());
                  vacancytxt2.setText(no + " no of vacancies available");
                  contrytxt2.setText(mjobs.getCity() + ", " + mjobs.getCountry());
                  String salary = String.valueOf(mjobs.getSalary());
                   mm2.setText(salary + " budget per month");
                   nationaltxt2.setText(mjobs.getNationality());
                   edutxt2.setText(mjobs.getMaxDegree() + " to " + mjobs.getMinDegree());
                   descText.setText(mjobs.getCandidateDescription());
                   jobdescription.setText(mjobs.getJobdescription());
               }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}
