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

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Strengths;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InterestsActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnaddaskill;
    Button btnsaveskill;
    TextInputLayout t1,t2,t3,t4,t5,t6,t7;
    Context sContext;
    EditText edit1,edit2,edit3,edit4,edit5,edit6,edit7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        edit1=(EditText)findViewById(R.id.foi1);
        edit2=(EditText)findViewById(R.id.foi2);
        edit3=(EditText)findViewById(R.id.foi3);
        edit4=(EditText)findViewById(R.id.foi4);
        edit5=(EditText)findViewById(R.id.foi5);
        edit6=(EditText)findViewById(R.id.foi6);
        edit7=(EditText)findViewById(R.id.foi7);
        t1=(TextInputLayout)findViewById(R.id.interest1Wrapper);
        t2=(TextInputLayout)findViewById(R.id.interest2Wrapper);
        t3=(TextInputLayout)findViewById(R.id.interest3Wrapper);
        t4=(TextInputLayout)findViewById(R.id.interest4Wrapper);
        t5=(TextInputLayout)findViewById(R.id.interest5Wrapper);
        t6=(TextInputLayout)findViewById(R.id.interest6Wrapper);
        t7=(TextInputLayout)findViewById(R.id.interest6Wrapper);
        btnaddaskill=(Button)findViewById(R.id.addInterest);
        btnsaveskill=(Button)findViewById(R.id.saveInterest);
        btnsaveskill.setOnClickListener(this);
        btnaddaskill.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addInterest:
                int b;
                if(edit2.getVisibility()==View.VISIBLE && edit3.getVisibility()==View.INVISIBLE) {
                    edit3.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    saveInterestsDetailsCV(edit2.getText().toString());
                    Toast.makeText(this,"Add new interest",Toast.LENGTH_SHORT).show();
                } else if(edit3.getVisibility()==View.VISIBLE && edit4.getVisibility()==View.INVISIBLE){
                    edit4.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    saveInterestsDetailsCV(edit3.getText().toString());
                    Toast.makeText(this,"Add new interest",Toast.LENGTH_SHORT).show();
                }else if(edit4.getVisibility()==View.VISIBLE && edit5.getVisibility()==View.INVISIBLE){
                    edit5.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    saveInterestsDetailsCV(edit4.getText().toString());
                    Toast.makeText(this,"Add new interest",Toast.LENGTH_SHORT).show();
                }else if(edit5.getVisibility()==View.VISIBLE && edit6.getVisibility()==View.INVISIBLE){
                    edit6.setVisibility(View.VISIBLE);
                    t6.setVisibility(View.VISIBLE);
                    saveInterestsDetailsCV(edit5.getText().toString());
                    Toast.makeText(this,"Add new interest",Toast.LENGTH_SHORT).show();
                }else if(edit6.getVisibility()==View.VISIBLE && edit7.getVisibility()==View.INVISIBLE){
                    edit7.setVisibility(View.VISIBLE);
                    t7.setVisibility(View.VISIBLE);
                    saveInterestsDetailsCV(edit6.getText().toString());
                    Toast.makeText(this,"Add new interest",Toast.LENGTH_SHORT).show();
                }else if(edit2.getVisibility()==View.INVISIBLE){
                    edit2.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    saveInterestsDetailsCV(edit1.getText().toString());
                    Toast.makeText(this,"Add new interest",Toast.LENGTH_SHORT).show();
                }else{
                    saveInterestsDetailsCV(edit7.getText().toString());
                    Toast.makeText(this,"Sufficeint attributes have been added already",Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.saveInterest:
                Intent i=new Intent();
                i.putExtra("KeyInterest1",edit1.getText().toString());
                i.putExtra("KeyInterest2",edit2.getText().toString());
                i.putExtra("KeyInterest3",edit3.getText().toString());
                i.putExtra("KeyInterest4",edit4.getText().toString());
                i.putExtra("KeyInterest5",edit5.getText().toString());
                i.putExtra("KeyInterest6",edit6.getText().toString());
                i.putExtra("KeyInterest7",edit7.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }

    }
    private void saveInterestsDetailsCV(final String skillname){
        final Strengths sauser=new Strengths();
        sauser.setStrength_name(skillname);
        sauser.setCv_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        String key=mDatabase.child("JobSeekerInterests").push().getKey();

        mDatabase.child("JobSeekerInterests")
                .child(key)
                .setValue(sauser);

        FirebaseDatabase.getInstance().getReference()
                .child("interests_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(key)
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(InterestsActivity.this,"Interest: "+skillname+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(InterestsActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
