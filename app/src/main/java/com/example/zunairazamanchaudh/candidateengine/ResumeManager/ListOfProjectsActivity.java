package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.ProjectDetails;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfProjectsActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<ProjectDetails> projects=new ArrayList<ProjectDetails>();
    private ListView listProject;
    private Button btnAddProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_projects);
        btnAddProject=(Button)findViewById(R.id.btnaddProjects);
        listProject=(ListView)findViewById(R.id.listProjectDetails);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child(String.valueOf(R.string.dbnode_projects_Resume))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    ProjectDetails mExp = dataSnapshot.getValue(ProjectDetails.class);

                    projects.add(mExp);
                }

                //WorkExperienceAdapter workExperienceAdapter = new WorkExperienceAdapter(workExperiences, ListOfExperienceActivity.this);
                //listexp.setAdapter(workExperienceAdapter);
                ProjectsAdapter adapter=new ProjectsAdapter(projects,ListOfProjectsActivity.this);
                listProject.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

//                progressDialog.dismiss();

            }
        });


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
