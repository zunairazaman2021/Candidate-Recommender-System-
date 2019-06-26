package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zunairazamanchaudh.candidateengine.R;

public class ProjectsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnproject,btnpdf;
    private static final int STORAGE_CODE=1000;
    private EditText editTitle,editProjectDesc,editDuration,editrole,editteamsize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        btnproject=(Button)findViewById(R.id.saveProjectDetails);
        editTitle=(EditText)findViewById(R.id.pdtitle);
        editProjectDesc=(EditText)findViewById(R.id.pdDescription);
        editDuration=(EditText)findViewById(R.id.pdDuration);
        editrole=(EditText)findViewById(R.id.projectRole);
        editteamsize=(EditText)findViewById(R.id.adteam);
        btnproject.setOnClickListener(this);
        btnpdf=(Button)findViewById(R.id.savePdfDetails);
        btnpdf.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.savePdfDetails:
                Intent i=new Intent(this,ListOfProjectsActivity.class);
                startActivity(i);
                break;
            case R.id.saveProjectDetails:
                Intent projectIntent=new Intent();
                String t=editTitle.getText().toString();
                projectIntent.putExtra("ptitle",t);
                String e=editProjectDesc.getText().toString();
                projectIntent.putExtra("pDescription",e);
                projectIntent.putExtra("pDuration",editDuration.getText().toString());
                projectIntent.putExtra("pRole",editrole.getText().toString());
                projectIntent.putExtra("pteam",editteamsize.getText().toString());
                setResult(RESULT_OK, projectIntent);
                finish();
                break;
        }
    }
}
