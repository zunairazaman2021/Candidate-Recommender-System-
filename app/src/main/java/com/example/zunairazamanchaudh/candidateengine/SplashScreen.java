package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tomer.fadingtextview.FadingTextView;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener{
 private Button btnsearch,btnsignup,btnlogin;
    FadingTextView FTV;
    String[] texts = {"Make a difference with your ONLINE RESUME"," at CRS today"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        btnsearch=findViewById(R.id.searchstart);
        btnsignup=findViewById(R.id.button);
        btnlogin=findViewById(R.id.button2);
        btnsearch.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        btnsignup.setOnClickListener(this);
          FTV  = (FadingTextView) findViewById(R.id.textView);
        FTV.setTexts(texts);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchstart:
                 break;
            case R.id.button:
                Intent i=new Intent(SplashScreen.this,signup.class);
                startActivity(i);

                break;

            case R.id.button2:
                Intent e=new Intent(SplashScreen.this, LoginJobSeeker.class);
                startActivity(e);
                break;

            default:
                break;
        }
    }
}
