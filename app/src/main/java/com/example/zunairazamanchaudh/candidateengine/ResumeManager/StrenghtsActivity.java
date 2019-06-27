package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

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
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Strengths;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StrenghtsActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnaddStr;
    Button btnsaveStr;
    TextInputLayout t1,t2,t3,t4,t5;
    EditText edit1,edit2,edit3,edit4,edit5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strenghts);
        edit1=(EditText)findViewById(R.id.strength1);
        edit2=(EditText)findViewById(R.id.strength2);
        edit3=(EditText)findViewById(R.id.strength3);
        edit4=(EditText)findViewById(R.id.strength4);
        edit5=(EditText)findViewById(R.id.strength5);
        t1=(TextInputLayout)findViewById(R.id.strength1Wrapper);
        t2=(TextInputLayout)findViewById(R.id.strength2Wrapper);
        t3=(TextInputLayout)findViewById(R.id.strength3Wrapper);
        t4=(TextInputLayout)findViewById(R.id.strength4Wrapper);
        t5=(TextInputLayout)findViewById(R.id.strength5Wrapper);
        btnaddStr=(Button)findViewById(R.id.addstrength);
        btnsaveStr=(Button)findViewById(R.id.savestrength);
        btnsaveStr.setOnClickListener(this);
        btnaddStr.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addstrength:
                int b;
                if(edit2.getVisibility()==View.VISIBLE && edit3.getVisibility()==View.INVISIBLE) {
                    edit3.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    saveStrengthsDetailsCV(edit2.getText().toString());
                    Toast.makeText(this,"Add new Strength",Toast.LENGTH_SHORT).show();
                } else if(edit3.getVisibility()==View.VISIBLE && edit4.getVisibility()==View.INVISIBLE){
                    edit4.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    saveStrengthsDetailsCV(edit3.getText().toString());
                    Toast.makeText(this,"Add new Strength",Toast.LENGTH_SHORT).show();
                }else if(edit4.getVisibility()==View.VISIBLE && edit5.getVisibility()==View.INVISIBLE){
                    edit5.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    saveStrengthsDetailsCV(edit4.getText().toString());
                    Toast.makeText(this,"Add new Strength",Toast.LENGTH_SHORT).show();
                }else if(edit2.getVisibility()==View.INVISIBLE){
                    edit2.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    saveStrengthsDetailsCV(edit1.getText().toString());
                    Toast.makeText(this,"Add new Strength",Toast.LENGTH_SHORT).show();
                }else{
                    saveStrengthsDetailsCV(edit5.getText().toString());
                    Toast.makeText(this,"Sufficeint Strengths have been added already",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.savestrength:
                Intent i=new Intent();
                i.putExtra("KeyStrength1",edit1.getText().toString());
                i.putExtra("KeyStrength2",edit2.getText().toString());
                i.putExtra("KeyStrength3",edit3.getText().toString());
                i.putExtra("KeyStrength4",edit4.getText().toString());
                i.putExtra("KeyStrength5",edit5.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }

    private void saveStrengthsDetailsCV(final String skillname){
        final Strengths sauser=new Strengths();
        sauser.setStrength_name(skillname);
        sauser.setCv_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        String key=mDatabase.child("JobSeekerStrengths").push().getKey();

        mDatabase.child("JobSeekerStrengths")
                .child(key)
                .setValue(sauser);

        FirebaseDatabase.getInstance().getReference()
                .child("strengths_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(key)
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(StrenghtsActivity.this,"Strength: "+skillname+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(StrenghtsActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
