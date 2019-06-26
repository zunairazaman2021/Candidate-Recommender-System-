package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zunairazamanchaudh.candidateengine.R;

public class ReferenceActivity extends AppCompatActivity implements View.OnClickListener{
EditText rName,rDesignation,refOrg,refEmail,refMobile;
Button saveRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        rName=(EditText)findViewById(R.id.refName);
        rDesignation=(EditText)findViewById(R.id.refDesignation);
        refOrg=(EditText)findViewById(R.id.refOrg);
        refEmail=(EditText)findViewById(R.id.refEmail);
        refMobile=(EditText)findViewById(R.id.refMobile);
        saveRef=(Button)findViewById(R.id.saveReference);
        saveRef.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveReference:
                Intent refIntent=new Intent();
                refIntent.putExtra("rName",rName.getText().toString());
                refIntent.putExtra("rDesignation",rDesignation.getText().toString());
                refIntent.putExtra("rOrganization",refOrg.getText().toString());
                refIntent.putExtra("rEmail",refEmail.getText().toString());
                refIntent.putExtra("rMobile",refMobile.getText().toString());
                setResult(RESULT_OK,refIntent);
                finish();
                break;
        }
    }
}
