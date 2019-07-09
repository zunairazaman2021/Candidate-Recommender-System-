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

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.languages;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class languagesKnow extends AppCompatActivity implements View.OnClickListener{

    EditText jalang;
    Spinner jalanglevel;
    Button saveslangd,cancellangd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages_know);
        jalang=(EditText)findViewById(R.id.jalang);
        jalanglevel=(Spinner)findViewById(R.id.jalanglevel);
        saveslangd=(Button)findViewById(R.id.saveslangd);
        cancellangd=(Button)findViewById(R.id.cancellangd);
        saveslangd.setOnClickListener(this);
        cancellangd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveslangd:
                if(jalang.getText().toString().trim()!="") {
                    saveLanguagesjobseeker(jalang.getText().toString(), jalanglevel.getSelectedItem().toString());
                    Intent i=new Intent(languagesKnow.this,WelcomeCandidate.class);
                    startActivity(i);
                }else{
                    jalang.setError("Field is required");
                }
                break;
            case R.id.cancellangd:
                Intent i=new Intent(languagesKnow.this,WelcomeCandidate.class);
                startActivity(i);

                break;
        }
    }
    public void saveLanguagesjobseeker(String s, String s1) {
        languages sauser=new languages();
        sauser.setLanguage(s);
        sauser.setLanguagelevel(s1);
        sauser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseDatabase.getInstance().getReference()
                .child("languages")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   redirectjbpreferenceFragment();
                Toast.makeText(languagesKnow.this,"Language for user id:  "+id+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(languagesKnow.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
