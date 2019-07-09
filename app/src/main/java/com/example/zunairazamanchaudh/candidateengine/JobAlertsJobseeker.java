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

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.jobalerts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.TextUtils.isEmpty;

public class JobAlertsJobseeker extends AppCompatActivity implements View.OnClickListener{

    EditText jaName,jaKeywords,jaCity,jaRole;
    Spinner jaCountry;
    Button saveJobAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_alerts_jobseeker);
        jaName=(EditText)findViewById(R.id.jaName);
        jaKeywords=(EditText)findViewById(R.id.jakeywords);
        jaCountry=(Spinner) findViewById(R.id.jacountry);
        jaCity=(EditText)findViewById(R.id.jacity);
        jaRole=(EditText)findViewById(R.id.jarole);
        saveJobAlert=(Button)findViewById(R.id.saveJobAlert);
        saveJobAlert.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveJobAlert:
                if(isEmpty(jaName.getText().toString().trim())){
                    jaName.setError("Field cannot be empty");
                }
                if(isEmpty(jaKeywords.getText().toString().trim())){
                    jaKeywords.setError("Field cannot be empty");
                }
                if(isEmpty(jaCity.getText().toString().trim())){
                    jaCity.setError("Field cannot be empty");
                }
                if(isEmpty(jaRole.getText().toString().trim())){
                    jaRole.setError("Field cannot be empty");
                }
                if(jaCountry.getSelectedItem().toString()=="Select Country"){
                    Toast.makeText(JobAlertsJobseeker.this,"Please Select a Country",Toast.LENGTH_SHORT).show();
                }
                if((!isEmpty(jaName.getText().toString())) && (!isEmpty(jaKeywords.getText().toString()))
                        && (!isEmpty(jaCity.getText().toString()))  && (!isEmpty(jaRole.getText().toString()))
                        && (jaCountry.getSelectedItem().toString()!="Select Country")
                        )
                { saveJobAlertindatabase(jaName.getText().toString(), jaKeywords.getText().toString(),
                        jaCountry.getSelectedItem().toString(),
                        jaCity.getText().toString(),
                        jaRole.getText().toString());
                    Intent i=new Intent(JobAlertsJobseeker.this,WelcomeCandidate.class);
                    startActivity(i);
                    finish();
                }
                break;
        }
    }

    public void saveJobAlertindatabase(String s, String s1, String c,String s2, String s3) {
        jobalerts jauser=new jobalerts();
        jauser.setAlertname(s);
        jauser.setKeywords(s1);
        jauser.setCountry(c);
        jauser.setCity(s2);
        jauser.setJobrole(s3);
        jauser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseDatabase.getInstance().getReference()
                .child("jobalerts")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(jauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   redirectjbpreferenceFragment();
                Toast.makeText(JobAlertsJobseeker.this,"Job Alert for user id:  "+id+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(JobAlertsJobseeker.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
