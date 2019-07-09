package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.skills;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SkillsEdit extends AppCompatActivity  implements View.OnClickListener{

    EditText jaskill;
    Spinner jaskilllevel;
    Button jasavesskilld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_edit);
        jaskill=(EditText)findViewById(R.id.jaskill);
        jaskilllevel=(Spinner)findViewById(R.id.jaskilllevel);
        jasavesskilld=(Button)findViewById(R.id.saveskilld);
        jasavesskilld.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveskilld:
                saveskillsjobseeker(jaskill.getText().toString(), jaskilllevel.getSelectedItem().toString());
                Intent i=new Intent(SkillsEdit.this,WelcomeCandidate.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    public void saveskillsjobseeker(String s, String s1) {
        skills sauser=new skills();
        sauser.setSkillname(s);
        sauser.setSkillLevel(s1);
        sauser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseDatabase.getInstance().getReference()
                .child("skills")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   redirectjbpreferenceFragment();
                Toast.makeText(SkillsEdit.this,"Skill for user id:  "+id+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(SkillsEdit.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
