package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.ArrayList;

public class ListOfProjectsActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Projects> projects=new ArrayList<Projects>();
    private ListView listProject;
    private Button btnAddProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_projects);
        btnAddProject=(Button)findViewById(R.id.btnaddProjects);
        listProject=(ListView)findViewById(R.id.listProjectDetails);
        projects.add(new Projects("Candidate Recommender System", "UOG", "1 year", "Project Manager", 3));
        ProjectsAdapter projectsAdapter=new ProjectsAdapter(projects,this);
        listProject.setAdapter(projectsAdapter);
        btnAddProject.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnaddProjects:
                Intent i=new Intent(this,ProjectsActivity.class);
                startActivity(i);
                break;

        }
    }
}
