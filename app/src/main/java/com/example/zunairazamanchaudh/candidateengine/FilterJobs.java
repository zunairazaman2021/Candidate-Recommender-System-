package com.example.zunairazamanchaudh.candidateengine;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.FilterAdapter2;
import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.cvvieweducationAdapter;
import com.example.zunairazamanchaudh.candidateengine.Adapters.FilterAdapter;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.Post;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.UserProfileView;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FilterJobs extends AppCompatActivity  {
/*
    @Override
    public void inflateViewProductFragment(JobView jobView) {
        ViewRecommendJobDetail fragment = new ViewRecommendJobDetail();

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.intent_product), jobView);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.somefiltercontent, fragment, getString(R.string.fragment_filter));
        transaction.addToBackStack(getString(R.string.fragment_filter));
        transaction.commit();
    }
*/

    private static final int SEARCH_ACTIVITY_REQUEST_CODE = 0;
RecyclerView recyclerView;
String skills,area;
Button btn,keyboardbackmove;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_filter_jobs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarfilterjob);
        setSupportActionBar(toolbar);
        btn=(Button)findViewById(R.id.filterbtn);
        recyclerView=(RecyclerView)findViewById(R.id.mysearchjob);
        skills=getIntent().getStringExtra("jobskill");
        area=getIntent().getStringExtra("editreqion");
        Toast.makeText(this,area+ skills,Toast.LENGTH_LONG).show();
        if(area!=""|| skills!="") {
          getInitSearch();
        }
        //FragmentSearch2 fragment=new FragmentSearch2();
        //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.replace(R.id.somefiltercontent, fragment);
        //ft.commit();
        //UserProfileView fragment = new UserProfileView();
       //Bundle bundle = new Bundle();
       //bundle.putParcelable(getString(R.string.intent_cv_user), cvView);
       //fragment.setArguments(bundle);

        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.somefilteration, fragment, "fragmentSearch2");
        //transaction.addToBackStack("fragmentSearch2");
        //transaction.commit();
        keyboardbackmove=(Button)findViewById(R.id.key1);
        keyboardbackmove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(FilterJobs.this,WelcomeCandidate.class);
                startActivity(ii);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //getInitSearch();
                Intent i=new Intent(FilterJobs.this,AdvancedSearchJobActivity.class);
                startActivityForResult(i, SEARCH_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    ProgressDialog progressDialog;
    private void getInitSearch(){

        final List<Post> list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading ...");

        progressDialog.show();
        final DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference()
                .child("Posts");
      Query query=dref.orderByChild("country").startAt(area);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
               if(snapshot==null){
                   DatabaseReference ref2=FirebaseDatabase.getInstance().getReference().child("Posts");
                   Query query1=ref2.orderByChild("JobTitle").endAt(skills);
                   query1.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {

                               Post mjobs = dataSnapshot2.getValue(Post.class);
                               list.add(mjobs);
                           }

                           FilterAdapter2 adapter=(FilterAdapter2) recyclerView.getAdapter();

                           adapter = new FilterAdapter2(getApplicationContext(), list);

                           recyclerView.setAdapter(adapter);

                           progressDialog.dismiss();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {
                         progressDialog.dismiss();
                       }
                   });
               }
               else{
                   for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                       Post mjobs = dataSnapshot.getValue(Post.class);
                       if ((mjobs.getJobtitle().startsWith(skills))
                               || (mjobs.getCompanyName().startsWith(skills))
                               || (mjobs.getSkills().startsWith(skills))
                               ) {
                           list.add(mjobs);
                       } else {
                           // list.add(mjobs);
                       }
                   }
                   FilterAdapter2 adapter = (FilterAdapter2) recyclerView.getAdapter();

                   adapter = new FilterAdapter2(getApplicationContext(), list);

                   recyclerView.setAdapter(adapter);

                   progressDialog.dismiss();

               }
               }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                         progressDialog.dismiss();

            }
        });
    }
    String i_job,i_city,i_company,i_salary,i_industry,i_exp,i_career,i_jobtype,i_gender;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SEARCH_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                String returnString = data.getStringExtra("keyName");
              i_job=data.getStringExtra("i_job");
              i_city=data.getStringExtra("i_city");
              i_company=data.getStringExtra("i_company");
              i_salary=data.getStringExtra("i_salary");
              i_industry=data.getStringExtra("i_industry");
              i_exp=data.getStringExtra("i_exp");
              i_career=data.getStringExtra("i_career");
              i_jobtype=data.getStringExtra("i_jobtype");
Log.d("FilterJobsTest",i_job+i_career+i_salary);
             showdata();

                // Set text view with string
              //  TextView textView = (TextView) findViewById(R.id.textView);
               // textView.setText(returnString);
            }
        }
    }

    Query query;


    public void showdata(){

        final List<Post> list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading ...");

        progressDialog.show();
        final DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference()
                .child("Posts");
        if(i_job!=null) {
           query = dref.orderByChild("jobtitle").startAt(i_job);
        }else if(i_city!=null){
            query = dref.orderByChild("city").startAt(i_city);
        }else if(i_company!=null){
            query = dref.orderByChild("companyName").startAt(i_company);
        }else if(i_jobtype!="Select job type"){
            query = dref.orderByChild("jobtype").startAt(i_jobtype);
        }else if(i_career!="Select Career level"){
            query = dref.orderByChild("jobdescription").startAt(i_jobtype);
        }else  if(i_exp!="Select experience level required"){
            query = dref.orderByChild("experience").startAt(i_jobtype);
        }else if(i_industry!="Select Industry"){
            query = dref.orderByChild("candidateDescription").endAt(i_industry);
        }else if(i_salary!="Select Salary"){
            query = dref.orderByChild("salary").startAt(i_jobtype);
        }else if(i_gender!="Select Gender"){
            query = dref.orderByChild("genderPreference").startAt(i_jobtype);
        }

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post mjobs = dataSnapshot.getValue(Post.class);
                    if ((i_job != null) && (i_city != null) && (i_company != null) &&
                            (i_career != "Select Career level") && (i_exp != "Select experience level required")
                            && (i_industry != "Select Industry") && (i_salary !=null)
                            && (i_gender != "Select Gender")
                            && (i_jobtype != "Select job type")
                            ) {
              //          int s = Integer.parseInt(i_salary);
                        if ((mjobs.getJobtitle().startsWith(i_job))
                                || (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))
                                || (mjobs.getJobdescription().contains(i_industry))
                                || (mjobs.getCandidateDescription().contains(i_career))
                                || (mjobs.getJobtype().startsWith(i_jobtype))
                //                || (mjobs.getSalary() == s)
                                || (mjobs.getGenderPreference().equals(i_gender))
                                || (mjobs.getExperience().equals(i_exp))
                                ) {
                            list.add(mjobs);

                        }
                    }
                    else  if ((i_job == null) && (i_city == null) && (i_company == null) &&
                            (i_career == "Select Career level") && (i_exp == "Select experience level required")
                            && (i_industry == "Select Industry") && (i_salary==null)
                            && (i_gender == "Select Gender")
                            && (i_jobtype == "Select job type")
                            ) {
                        // list.add(mjobs);
                    }
                   else if ((i_job != null) && (i_city == null) && (i_company == null) &&
                            (i_career == "Select Career level")
                            && (i_exp == "Select experience level required")
                            && (i_industry == "Select Industry") && (i_salary ==null)
                            && (i_gender == "Select Gender")
                            && (i_jobtype == "Select job type")
                            ) {
                        if(mjobs.getJobtitle().startsWith(i_job)){
                            list.add(mjobs);
                        }
                    }


                    else if ((i_job != null) && (i_city != null) && (i_company == null) &&
                            (i_career == "Select Career level")
                            && (i_exp == "Select experience level required")
                            && (i_industry == "Select Industry") && (i_salary ==null)
                            && (i_gender == "Select Gender")
                            && (i_jobtype == "Select job type")
                            ){
                        if(mjobs.getJobtitle().startsWith(i_job) ||
                                (mjobs.getCity().startsWith(i_city))){
                            list.add(mjobs);
                        }
                    }

                    else if ((i_job != null) && (i_city != null) && (i_company != null) &&
                            (i_career == "Select Career level")
                            && (i_exp == "Select experience level required")
                            && (i_industry == "Select Industry") && (i_salary ==null)
                            && (i_gender == "Select Gender")
                            && (i_jobtype == "Select job type")
                            ){
                        if(mjobs.getJobtitle().startsWith(i_job) ||
                                (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))){
                            list.add(mjobs);
                        }
                    }

                    else if ((i_job != null) && (i_city != null) && (i_company != null) &&
                            (i_career != "Select Career level")
                            && (i_exp == "Select experience level required")
                            && (i_industry == "Select Industry") && (i_salary==null)
                            && (i_gender == "Select Gender")
                            && (i_jobtype == "Select job type")
                            ){
                        if(mjobs.getJobtitle().startsWith(i_job) ||
                                (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))
                                || (mjobs.getJobdescription().contains(i_career))){
                            list.add(mjobs);
                        }
                    }

                    else if ((i_job != null) && (i_city != null) && (i_company != null) &&
                            (i_career != "Select Career level")
                            && (i_exp != "Select experience level required")
                            && (i_industry == "Select Industry") && (i_salary==null)
                            && (i_gender == "Select Gender")
                            && (i_jobtype == "Select job type")
                            ){
                        if(mjobs.getJobtitle().startsWith(i_job) ||
                                (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))
                                || (mjobs.getJobdescription().contains(i_career))
                                || (mjobs.getExperience().startsWith(i_exp))){
                            list.add(mjobs);
                        }
                    }

                    else if ((i_job != null) && (i_city != null) && (i_company != null) &&
                            (i_career != "Select Career level")
                            && (i_exp != "Select experience level required")
                            && (i_industry == "Select Industry") && (i_salary==null)
                            && (i_gender == "Select Gender")
                            && (i_jobtype == "Select job type")
                            ){
                        if(mjobs.getJobtitle().startsWith(i_job) ||
                                (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))
                                || (mjobs.getJobdescription().contains(i_industry))
                                || (mjobs.getExperience().startsWith(i_exp))){
                            list.add(mjobs);
                        }
                    }
                    else if ((i_job != null) && (i_city != null) && (i_company != null) &&
                            (i_career != "Select Career level")
                            && (i_exp != "Select experience level required")
                            && (i_industry != "Select Industry") && (i_salary==null)
                            && (i_gender == "Select Gender")
                            && (i_jobtype == "Select job type")
                            ){
                        if(mjobs.getJobtitle().startsWith(i_job)
                                || (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))
                                || (mjobs.getJobdescription().contains(i_career))
                                || (mjobs.getExperience().startsWith(i_exp))
                                || (mjobs.getJobdescription().contains(i_industry))
                                ){
                            list.add(mjobs);
                        }
                    }

                    else if ((i_job != null) && (i_city != null) && (i_company != null) &&
                            (i_career != "Select Career level")
                            && (i_exp != "Select experience level required")
                            && (i_industry != "Select Industry") && (i_salary != null)
                            && (i_gender == "Select Gender")
                            && (i_jobtype == "Select job type")
                            ){
                  //      int ss=Integer.valueOf(i_salary);
                        if(mjobs.getJobtitle().startsWith(i_job)
                                || (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))
                                || (mjobs.getJobdescription().contains(i_career))
                                || (mjobs.getExperience().startsWith(i_exp))
                                || (mjobs.getJobdescription().contains(i_industry))
                    //            || (mjobs.getSalary()==ss)
                                ){
                            list.add(mjobs);
                        }
                    }

                    else if ((i_job != null) && (i_city != null) && (i_company != null) &&
                            (i_career != "Select Career level")
                            && (i_exp != "Select experience level required")
                            && (i_industry != "Select Industry") && (i_salary != null)
                            && (i_gender != "Select Gender")
                            && (i_jobtype == "Select job type")
                            ){
                      //  int ss=Integer.valueOf(i_salary);
                        if(mjobs.getJobtitle().startsWith(i_job)
                                || (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))
                                || (mjobs.getJobdescription().contains(i_career))
                                || (mjobs.getExperience().startsWith(i_exp))
                                || (mjobs.getJobdescription().contains(i_industry))
                        //        || (mjobs.getSalary()==ss)
                                || (mjobs.getGenderPreference().equals(i_gender))
                                ){
                            list.add(mjobs);
                        }
                    }

                    else if ((i_job != null) && (i_city != null) && (i_company != null) &&
                            (i_career != "Select Career level")
                            && (i_exp != "Select experience level required")
                            && (i_industry != "Select Industry") && (i_salary != null)
                            && (i_gender != "Select Gender")
                            && (i_jobtype != "Select job type")){
                        int ss=Integer.valueOf(i_salary);
                        if(mjobs.getJobtitle().startsWith(i_job)
                                || (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))
                                || (mjobs.getJobdescription().contains(i_career))
                                || (mjobs.getExperience().startsWith(i_exp))
                                || (mjobs.getJobdescription().contains(i_industry))
                                || (mjobs.getSalary()==ss)
                                || (mjobs.getGenderPreference().equals(i_gender))
                                || (mjobs.getJobtype().equals(i_jobtype))
                                ){
                            list.add(mjobs);
                        }
                    }
                    else if((i_job != null) || (i_city != null) || (i_company != null) ||
                            (i_career != "Select Career level")
                            ||(i_exp != "Select experience level required")
                            || (i_industry != "Select Industry") ||(i_salary != null)
                            || (i_gender != "Select Gender")
                            || (i_jobtype != "Select job type")){
                        //int ii=Integer.valueOf(i_salary);
               //         int ii=Integer.parseInt(i_salary);
                        if(mjobs.getJobtitle().startsWith(i_job)
                                || (mjobs.getCity().startsWith(i_city))
                                || (mjobs.getCompanyName().startsWith(i_company))
                                || (mjobs.getJobdescription().contains(i_career))
                                || (mjobs.getExperience().startsWith(i_exp))
                                || (mjobs.getJobdescription().contains(i_industry))
                 //               || (mjobs.getSalary()==ii)
                                || (mjobs.getGenderPreference().equals(i_gender))
                                || (mjobs.getJobtype().equals(i_jobtype))
                                ){
                            list.add(mjobs);
                        }
                    }
                }
                FilterAdapter2 adapter=(FilterAdapter2) recyclerView.getAdapter();

                adapter = new FilterAdapter2( list);

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
