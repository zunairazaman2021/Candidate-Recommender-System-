package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobSearchQuery;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SearchWebScrappedJobs extends AppCompatActivity {
Spinner filtergender;
TextInputEditText filtercareer,filtercompany,filtereexp,filtercity,
        filterindustry,filtertitle,filtertype,filtersalary;
Button filtersearchjob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_web_scrapped_jobs);
    filtercareer=(TextInputEditText)findViewById(R.id.filtercareer);
    filtercompany=(TextInputEditText)findViewById(R.id.filtercompany);

        filtereexp=(TextInputEditText)findViewById(R.id.filtereexp);
        filtercity=(TextInputEditText)findViewById(R.id.filtercity);
        filterindustry=(TextInputEditText)findViewById(R.id.filterindustry);
        filtertitle=(TextInputEditText)findViewById(R.id.filtertitle);
        filtertype=(TextInputEditText)findViewById(R.id.filtertype);
        filtersalary=(TextInputEditText)findViewById(R.id.filtersalary);
        filtergender=(Spinner)findViewById(R.id.filtergender);
        filtersearchjob=(Button)findViewById(R.id.filtersearchjob);
        filtersearchjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(filtercareer.getText().toString().equals("")) ||
                        !(filtercompany.getText().toString().equals("")) ||
                        !(filtereexp.getText().toString().equals(""))||
                        !(filtercity.getText().toString().equals("")) ||
                        !(filterindustry.getText().toString().equals("")) ||
                !(filtertitle.getText().toString().equals(""))||
                        !(filtertype.getText().toString().equals(""))||
                        !(filtersalary.getText().toString().equals(""))
                        ){
                    JobSearchQuery ja=new JobSearchQuery();
                    ja.setCareer_level(filtercareer.getText().toString());
                    ja.setCompany(filtercompany.getText().toString());
                    ja.setExperience_level_required(filtereexp.getText().toString());
                    ja.setJob_city(filtercity.getText().toString());
                    ja.setJob_industry(filterindustry.getText().toString());
                    ja.setJob_title(filtertitle.getText().toString());
                    ja.setJob_type(filtertype.getText().toString());
                    int s=Integer.parseInt(filtersalary.getText().toString());
                    ja.setGender(filtergender.getSelectedItem().toString());
                    ja.setSalary(s);
                    DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
                    String key=mDatabase.child("JobSearchQuery").push().getKey();
                    mDatabase.child("JobSearchQuery")
                            .child(key)
                            .setValue(ja);
                    Intent i=new Intent(SearchWebScrappedJobs.this,JobFilterationWebScrapping.class);
                    i.putExtra("web_jid",key);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(SearchWebScrappedJobs.this,"Fields cannot have empty values",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
