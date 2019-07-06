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
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.AdressJobseeker;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.Post;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ViewJobDescActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    String jobid,creatorid;
    TextView descText,jobdescription;
    ImageButton show, hide,show1,hide1;
    Button keybackview,applyhere,viewRecruiter;
    TextView titletext2,companytxt2,experience2,vacancytxt2,contrytxt2,mm2,nationaltxt2,edutxt2;


    //jobapplication user

    private String firstname;
    private String lastname;
    private String profile_image;
    private String security_level;
    private String dob;
    private String phone;
    private String email;
    private String nationality;
    //user address
    private String city;
    private String country;
    private String state;
    private String zipcode;
    //jobapplication jobPost
    private String companyName,yourName,jcity,jcountry,status;
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
         //  alreadyapplied();
          applyForJob();
    //      String s=applyhere.getText().toString();

          /* if(s.equals("Apply Here")){
              Intent i=new Intent(ViewJobDescActivity.this,CoverLetterActivity.class);
              jobid=getIntent().getStringExtra("z_jobid");
              creatorid=getIntent().getStringExtra("z_createrid");
              i.putExtra("ah_jobid",jobid);
              i.putExtra("ah_createrid",creatorid);
              startActivity(i);
              finish();
          }else{
              Toast.makeText(ViewJobDescActivity.this,"You have already applied zunaira",Toast.LENGTH_SHORT).show();
          }*/
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
        //Query query=dref.child(creatorid).child(jobid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
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
String key;


    //////////////////////////////////////////////////////



    /* DatabaseReference rr=FirebaseDatabase.getInstance().getReference().child("jobApplication");
     rr.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                 JobApplication jj=dataSnapshot1.getValue(JobApplication.class);
                 String jjr=jj.getRecruiter_id();
                 if(jjr!=null && jjr.equals(creatorid)){
                     if(jj.getJob_id().equals(jobid)){
                     if(jj.getCv_id().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                     applyhere.setText("Applied");
                     applyhere.setClickable(false);
                     }else{
                         Intent i=new Intent(ViewJobDescActivity.this,CoverLetterActivity.class);
                         jobid=getIntent().getStringExtra("z_jobid");
                         creatorid=getIntent().getStringExtra("z_createrid");
                         i.putExtra("ah_jobid",jobid);
                         i.putExtra("ah_createrid",creatorid);
                         startActivity(i);
                         finish();

                     }
                     }else{
                         Intent i=new Intent(ViewJobDescActivity.this,CoverLetterActivity.class);
                         jobid=getIntent().getStringExtra("z_jobid");
                         creatorid=getIntent().getStringExtra("z_createrid");
                         i.putExtra("ah_jobid",jobid);
                         i.putExtra("ah_createrid",creatorid);
                         startActivity(i);
                         finish();

                     }
                 }else{
                     Intent i=new Intent(ViewJobDescActivity.this,CoverLetterActivity.class);
                     jobid=getIntent().getStringExtra("z_jobid");
                     creatorid=getIntent().getStringExtra("z_createrid");
                     i.putExtra("ah_jobid",jobid);
                     i.putExtra("ah_createrid",creatorid);
                     startActivity(i);
                     finish();

                 }
             }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });*/
    private void applyForJob(){

        DatabaseReference mdb=FirebaseDatabase.getInstance().getReference()
                .child("jobApplication").child(creatorid).child(jobid);
        DatabaseReference mdbb=FirebaseDatabase.getInstance().getReference()
                .child("jobApplication").child(creatorid).child(jobid);
        final Query querymdb= mdb.orderByChild("cv_id")
                .equalTo("123");

       mdbb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        JobApplication j=dataSnapshot1.getValue(JobApplication.class);
                        String cv=j.getCv_id().toString();
                        if(cv.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                            applyhere.setText("Applied");
                            applyhere.setClickable(false);
                            Toast.makeText(ViewJobDescActivity.this,"You already applied for this job",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ViewJobDescActivity.this,"You apply here congo",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(ViewJobDescActivity.this,ApplyHere.class);
                            jobid=getIntent().getStringExtra("z_jobid");
                            creatorid=getIntent().getStringExtra("z_createrid");
                            i.putExtra("ah_jobid",jobid);
                            i.putExtra("ah_createrid",creatorid);
                            startActivity(i);
                            finish();
                        }
                        }
                  //  Toast.makeText(ViewJobDescActivity.this,"You already applied for this job",Toast.LENGTH_SHORT).show();
                    //applyForJob();

                }
                //  Toast.makeText(ViewJobDescActivity.this,"You have already applied for this job vacancy",Toast.LENGTH_SHORT).show();
        //        done.countDown();
             nay=1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //try {
          //  done.await(); //it will wait till the response is received from firebase.
        //} catch(InterruptedException e) {
          //  e.printStackTrace();
       // }
        // Toast.makeText(ViewJobDescActivity.this,"You have already applied for this job vacancy",Toast.LENGTH_SHORT).show();

         }
    int nay=0;
    ProgressDialog progressDialogz;
    private void alreadyapplied(){
        progressDialogz = new ProgressDialog(this);

        progressDialogz.setMessage("Loading ...");

        progressDialogz.show();

        DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
        //Query q=mDatabase.child("jobApplication").child(creatorid).child(jobid).orderByChild("cv_id")
          //      .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query q=mDatabase.child("AllApplications").orderByChild("job_id").equalTo(jobid);

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                   for(DataSnapshot d: dataSnapshot.getChildren()){
                       JobApplication ja=d.getValue(JobApplication.class);
                       if(ja.getCv_id().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                           nay=1;
                           Toast.makeText(ViewJobDescActivity.this, "Id exists " + ja.getCv_id(), Toast.LENGTH_LONG).show();
                       }else{
                           Toast.makeText(ViewJobDescActivity.this,"false you can apply ",Toast.LENGTH_LONG).show();
                       }
                   }
                }else if(dataSnapshot==null){
                    Toast.makeText(ViewJobDescActivity.this,"jobid does not exists ",Toast.LENGTH_LONG).show();
                    //applyForJob();
                }
                progressDialogz.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialogz.dismiss();
                Toast.makeText(ViewJobDescActivity.this,"There is"+databaseError,Toast.LENGTH_LONG).show();
            }
        });
        if(nay==0){
            Toast.makeText(ViewJobDescActivity.this,"0 you can apply ",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ViewJobDescActivity.this,"1  cannot apply ",Toast.LENGTH_LONG).show();
        }
    }
}
