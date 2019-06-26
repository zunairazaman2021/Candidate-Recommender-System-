package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.LoginRecruiter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.RecruiterUserHead;
import com.tomer.fadingtextview.FadingTextView;

public class LoginUserType extends AppCompatActivity implements View.OnClickListener{


    private Button btnsearch,btnjobseeker,btnrecruiter;
    FadingTextView FTV;
    String[] texts = {"Register "," at CRS today","as Jobseeker ","or recruiter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_type);
        btnsearch=findViewById(R.id.searchstarttype);
        btnjobseeker=findViewById(R.id.buttonJobseeker);
        btnrecruiter=findViewById(R.id.buttonRecruiter);
        btnsearch.setOnClickListener(this);
        btnrecruiter.setOnClickListener(this);
        btnjobseeker.setOnClickListener(this);
        FTV  = (FadingTextView) findViewById(R.id.textViewtype);
        FTV.setTexts(texts);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchstarttype:
                break;
            case R.id.buttonJobseeker:
                Intent i=new Intent(LoginUserType.this,SplashScreen.class);
                startActivity(i);

                break;

            case R.id.buttonRecruiter:
                Intent e=new Intent(LoginUserType.this, RecruiterUserHead.class);
                startActivity(e);
                break;

            default:
                break;
        }
    }
}
