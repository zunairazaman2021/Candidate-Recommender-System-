package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.ProjectDetails;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        getProjectDetails();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.savePdfDetails:
                Intent i=new Intent(this,ListOfProjectsActivity.class);
                startActivity(i);
                break;
            case R.id.saveProjectDetails:
                saveProjectDetailsCV();
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

    private void getProjectDetails(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_projects_Resume))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .orderByKey()
                .limitToFirst(1);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        ProjectDetails user=singleSnapshot.getValue(ProjectDetails.class);
                        editTitle.setText(user.getTitle());
                        editDuration.setText(user.getDescription());
                        editProjectDesc.setText(user.getDescription());
                        editrole.setText(user.getRole());
                        editteamsize.setText(Integer.toString(user.getTeamsize()));
                        }
                } else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void saveProjectDetailsCV(){
        final ProjectDetails sauser=new ProjectDetails();
        sauser.setTitle(editTitle.getText().toString());
        sauser.setDescription(editProjectDesc.getText().toString());
        sauser.setDuration(editDuration.getText().toString());
        sauser.setRole(editrole.getText().toString());
        sauser.setTeamsize(Integer.valueOf(editteamsize.getText().toString()));
        sauser.setCv_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
        String key=mDatabase.child("JobSeekersProjects").push().getKey();

        mDatabase.child("JobSeekersProjects")
                .child(key)
                .setValue(sauser);

        FirebaseDatabase.getInstance().getReference()
                .child("projects_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(key)
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(ProjectsActivity.this,"Project details saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(ProjectsActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }



}
