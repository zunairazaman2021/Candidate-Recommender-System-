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

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Awards;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Strengths;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AwardsActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnaddaward;
    Button btnsaveaward;
    TextInputLayout t1,t2,t3,t4,t5,t6,t7;
    Context sContext;
    EditText edit1,edit2,edit3,edit4,edit5,edit6,edit7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);
        edit1=(EditText)findViewById(R.id.award1);
        edit2=(EditText)findViewById(R.id.award2);
        edit3=(EditText)findViewById(R.id.award3);
        edit4=(EditText)findViewById(R.id.award4);
        edit5=(EditText)findViewById(R.id.award5);
        t1=(TextInputLayout)findViewById(R.id.award1Wrapper);
        t2=(TextInputLayout)findViewById(R.id.award2Wrapper);
        t3=(TextInputLayout)findViewById(R.id.award3Wrapper);
        t4=(TextInputLayout)findViewById(R.id.award4Wrapper);
        t5=(TextInputLayout)findViewById(R.id.award5Wrapper);
        btnaddaward=(Button)findViewById(R.id.addaward);
        btnsaveaward=(Button)findViewById(R.id.saveaward);
        btnsaveaward.setOnClickListener(this);
        btnaddaward.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addaward:
                int b;
                if (edit2.getVisibility() == View.VISIBLE && edit3.getVisibility() == View.INVISIBLE) {
                    edit3.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    saveAwardDetailsCV(edit2.getText().toString());
                    Toast.makeText(this, "Add new award", Toast.LENGTH_SHORT).show();
                } else if (edit3.getVisibility() == View.VISIBLE && edit4.getVisibility() == View.INVISIBLE) {
                    edit4.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    saveAwardDetailsCV(edit3.getText().toString());
                    Toast.makeText(this, "Add new award", Toast.LENGTH_SHORT).show();
                } else if (edit4.getVisibility() == View.VISIBLE && edit5.getVisibility() == View.INVISIBLE) {
                    edit5.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    saveAwardDetailsCV(edit4.getText().toString());
                    Toast.makeText(this, "Add new award", Toast.LENGTH_SHORT).show();
                } else if (edit2.getVisibility() == View.INVISIBLE) {
                    edit2.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    saveAwardDetailsCV(edit1.getText().toString());
                    Toast.makeText(this, "Add new award", Toast.LENGTH_SHORT).show();
                } else {
                    saveAwardDetailsCV(edit5.getText().toString());
                    Toast.makeText(this, "Sufficeint awards have been added already", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.saveaward:
                Intent i = new Intent();
                i.putExtra("Keyaward1", edit1.getText().toString());
                i.putExtra("Keyaward2", edit2.getText().toString());
                i.putExtra("Keyaward3", edit3.getText().toString());
                i.putExtra("Keyaward4", edit4.getText().toString());
                i.putExtra("Keyaward5", edit5.getText().toString());
                setResult(RESULT_OK, i);
                finish();
                break;

        }
    }
    private void saveAwardDetailsCV(final String skillname){
        final Awards sauser=new Awards();
        sauser.setAward_name(skillname);
        sauser.setCv_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        String key=mDatabase.child("JobSeekerAwards").push().getKey();

        mDatabase.child("JobSeekerAwards")
                .child(key)
                .setValue(sauser);

        FirebaseDatabase.getInstance().getReference()
                .child("awards_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(key)
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(AwardsActivity.this,"Award: "+skillname+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AwardsActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
