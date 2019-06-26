package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zunairazamanchaudh.candidateengine.LoginJobSeeker;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.SplashScreen;
import com.example.zunairazamanchaudh.candidateengine.signup;
import com.tomer.fadingtextview.FadingTextView;

public class RecruiterUserHead extends AppCompatActivity implements View.OnClickListener{

    private Button btnsearch,btnsignup,btnlogin;
    FadingTextView FTV;
    String[] texts = {"Hire the best talent"," at CRS today"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_user_head);
        btnsearch=findViewById(R.id.searchstartR);
        btnsignup=findViewById(R.id.buttonR);
        btnlogin=findViewById(R.id.button2R);
        btnsearch.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        btnsignup.setOnClickListener(this);
        FTV  = (FadingTextView) findViewById(R.id.textViewR);
        FTV.setTexts(texts);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchstartR:
                break;
            case R.id.buttonR:
                Intent i=new Intent(RecruiterUserHead.this,RegisterRecruiterActivity.class);
                startActivity(i);

                break;

            case R.id.button2R:
                Intent e=new Intent(RecruiterUserHead.this, LoginRecruiter.class);
                startActivity(e);
                break;

            default:
                break;
        }
    }

}
