package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.SkillsCV;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.workExperienceCV;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SkillsActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnaddaskill;
    Button btnsaveskill;
     TextInputLayout t1,t2,t3,t4,t5,t6,t7;
    Context sContext;
     EditText edit1,edit2,edit3,edit4,edit5,edit6,edit7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        edit1=(EditText)findViewById(R.id.skSkill_1);
        edit2=(EditText)findViewById(R.id.skSkill_2);
        edit3=(EditText)findViewById(R.id.skSkill_3);
        edit4=(EditText)findViewById(R.id.skSkill_4);
        edit5=(EditText)findViewById(R.id.skSkill_5);
        edit6=(EditText)findViewById(R.id.skSkill_6);
        edit7=(EditText)findViewById(R.id.skSkill_7);
        t1=(TextInputLayout)findViewById(R.id.skill1Wrapper);
        t2=(TextInputLayout)findViewById(R.id.skill2Wrapper);
        t3=(TextInputLayout)findViewById(R.id.skill3Wrapper);
        t4=(TextInputLayout)findViewById(R.id.skill4Wrapper);
        t5=(TextInputLayout)findViewById(R.id.skill5Wrapper);
        t6=(TextInputLayout)findViewById(R.id.skill6Wrapper);
        t7=(TextInputLayout)findViewById(R.id.skill7Wrapper);
        btnaddaskill=(Button)findViewById(R.id.addSkill);
        btnsaveskill=(Button)findViewById(R.id.saveSkill);
        btnsaveskill.setOnClickListener(this);
        btnaddaskill.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addSkill:
                int b;
                if(edit2.getVisibility()==View.VISIBLE && edit3.getVisibility()==View.INVISIBLE) {
                    edit3.setVisibility(View.VISIBLE);
                    saveSkillDetailsCV(edit2.getText().toString());
                    t3.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                } else if(edit3.getVisibility()==View.VISIBLE && edit4.getVisibility()==View.INVISIBLE){
                    edit4.setVisibility(View.VISIBLE);
                    saveSkillDetailsCV(edit3.getText().toString());
                    t4.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else if(edit4.getVisibility()==View.VISIBLE && edit5.getVisibility()==View.INVISIBLE){
                    edit5.setVisibility(View.VISIBLE);
                    saveSkillDetailsCV(edit4.getText().toString());
                    t5.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else if(edit5.getVisibility()==View.VISIBLE && edit6.getVisibility()==View.INVISIBLE){
                    edit6.setVisibility(View.VISIBLE);
                    saveSkillDetailsCV(edit5.getText().toString());
                    t6.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else if(edit6.getVisibility()==View.VISIBLE && edit7.getVisibility()==View.INVISIBLE){
                    edit7.setVisibility(View.VISIBLE);
                    saveSkillDetailsCV(edit6.getText().toString());
                    t7.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else if(edit2.getVisibility()==View.INVISIBLE){
                    edit2.setVisibility(View.VISIBLE);
                    saveSkillDetailsCV(edit1.getText().toString());
                    t2.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else{
                    saveSkillDetailsCV(edit7.getText().toString());
                    Toast.makeText(this,"Sufficeint skills have been added already",Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.saveSkill:
                Intent i=new Intent();
                i.putExtra("KeySkill1",edit1.getText().toString());
                i.putExtra("KeySkill2",edit2.getText().toString());
                i.putExtra("KeySkill3",edit3.getText().toString());
                i.putExtra("KeySkill4",edit4.getText().toString());
                i.putExtra("KeySkill5",edit5.getText().toString());
                i.putExtra("KeySkill6",edit6.getText().toString());
                i.putExtra("KeySkill7",edit7.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }

    }
    private void saveSkillDetailsCV(final String skillname){
        final SkillsCV sauser=new SkillsCV();
        sauser.setSkillName(skillname);
        sauser.setCv_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        String key=mDatabase.child("JobSeekerSkills").push().getKey();

        mDatabase.child("JobSeekerSkills")
                .child(key)
                .setValue(sauser);

        FirebaseDatabase.getInstance().getReference()
                .child("skills_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(key)
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(SkillsActivity.this,"Skill: "+skillname+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(SkillsActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
