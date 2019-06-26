package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class spalsh extends AppCompatActivity {
 private ProgressBar progressBar;
 private static int TIMEOUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_spalsh);
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent i=new Intent(spalsh.this,LoginUserType.class);
        startActivity(i);
        finish();
        }
    },TIMEOUT);
    }
}
