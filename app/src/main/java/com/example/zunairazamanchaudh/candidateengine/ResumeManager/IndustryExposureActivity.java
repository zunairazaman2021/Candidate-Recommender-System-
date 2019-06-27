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

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Industry;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Strengths;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IndustryExposureActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnaddIndustry;
    Button btnsaveIndustry;
    TextInputLayout t1,t2,t3;
    EditText edit1,edit2,edit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry_exposure);
        edit1=(EditText)findViewById(R.id.industry1);
        edit2=(EditText)findViewById(R.id.industry2);
        edit3=(EditText)findViewById(R.id.industry3);
        t1=(TextInputLayout)findViewById(R.id.industry1Wrapper);
        t2=(TextInputLayout)findViewById(R.id.industry2Wrapper);
        t3=(TextInputLayout)findViewById(R.id.industry3Wrapper);
        btnaddIndustry=(Button)findViewById(R.id.btnaddIndustry);
        btnsaveIndustry=(Button)findViewById(R.id.btnsaveIndustry);
        btnsaveIndustry.setOnClickListener(this);
        btnaddIndustry.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnaddIndustry:
                if(edit2.getVisibility()==View.VISIBLE && edit3.getVisibility()==View.INVISIBLE) {
                    edit3.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    saveIndustryDetailsCV(edit2.getText().toString());
                    Toast.makeText(this,"Add new Industry",Toast.LENGTH_SHORT).show();
                }else if(edit2.getVisibility()==View.INVISIBLE){
                    edit2.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    saveIndustryDetailsCV(edit1.getText().toString());
                    Toast.makeText(this,"Add new Industry",Toast.LENGTH_SHORT).show();
                }else{
                    saveIndustryDetailsCV(edit3.getText().toString());
                    Toast.makeText(this,"Sufficeint Industries have been added already",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnsaveIndustry:
                Intent i=new Intent();
                i.putExtra("KeyIndustry1",edit1.getText().toString());
                i.putExtra("KeyIndustry2",edit2.getText().toString());
                i.putExtra("KeyIndustry3",edit3.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }
    private void saveIndustryDetailsCV(final String skillname){
        final Industry sauser=new Industry();
        sauser.setIndustry_name(skillname);
        sauser.setCv_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        String key=mDatabase.child("JobSeekerIndustries").push().getKey();

        mDatabase.child("JobSeekerIndustries")
                .child(key)
                .setValue(sauser);

        FirebaseDatabase.getInstance().getReference()
                .child("industries_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(key)
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(IndustryExposureActivity.this,"Industry: "+skillname+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(IndustryExposureActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
