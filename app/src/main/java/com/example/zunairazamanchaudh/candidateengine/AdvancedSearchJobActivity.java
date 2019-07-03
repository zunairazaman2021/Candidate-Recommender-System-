package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import fr.ganfra.materialspinner.MaterialSpinner;

public class AdvancedSearchJobActivity extends AppCompatActivity implements View.OnClickListener {
MaterialSpinner filtercareer,filterindustry,filterjobtype,filterexp,filtergender;
   TextInputEditText filtersalary;
TextInputEditText filtername,filtercity,filtercompany;
Button backtofilter,filtersearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search_job);
        filtercareer=(MaterialSpinner)findViewById(R.id.filtercareer);
        filterindustry=(MaterialSpinner)findViewById(R.id.filterindustry);
        filterjobtype=(MaterialSpinner)findViewById(R.id.filterjobtype);
        filterexp=(MaterialSpinner)findViewById(R.id.filterexperience);
        filtergender=(MaterialSpinner)findViewById(R.id.filtergender);
        filtersalary=(TextInputEditText) findViewById(R.id.filtersalary);
        filtername=(TextInputEditText)findViewById(R.id.filtername);
        filtercity=(TextInputEditText)findViewById(R.id.filtercity);
        filtercompany=(TextInputEditText)findViewById(R.id.filtercompany);
        backtofilter=(Button)findViewById(R.id.backToFilterJob);
        filtersearch=(Button)findViewById(R.id.filtersearchjob);
        backtofilter.setOnClickListener(this);
        filtersearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backToFilterJob:
                Intent b=new Intent(this,FilterJobs.class);
                startActivity(b);
                finish();
                break;
            case R.id.filtersearchjob:
                Intent d=new Intent();
                d.putExtra("i_job",filtername.getText().toString());
                d.putExtra("i_city",filtercity.getText().toString());
                d.putExtra("i_company",filtercompany.getText().toString());
                d.putExtra("i_salary",filtersalary.getText().toString());
                d.putExtra("i_gender",filtergender.getSelectedItem().toString());
                d.putExtra("i_exp",filterexp.getSelectedItem().toString());
                d.putExtra("i_jobtype",filterjobtype.getSelectedItem().toString());
                d.putExtra("i_industry",filterindustry.getSelectedItem().toString());
                d.putExtra("i_career",filtercareer.getSelectedItem().toString());
                setResult(RESULT_OK, d);
                finish();
                break;
        }
    }
}
