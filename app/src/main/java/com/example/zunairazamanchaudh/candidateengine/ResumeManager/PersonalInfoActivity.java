package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zunairazamanchaudh.candidateengine.R;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {
EditText editnation,editmaritalstatus,editdob,editCustom;
Button btnsavePI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        editdob=(EditText)findViewById(R.id.dobPI);
        editmaritalstatus=(EditText)findViewById(R.id.maritalstatusPI);
        editnation=(EditText)findViewById(R.id.nationalityPI);
        editCustom=(EditText)findViewById(R.id.customPI);
        btnsavePI=(Button) findViewById(R.id.savePIdetails);
        btnsavePI.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.savePIdetails:
                Intent i=new Intent();
                i.putExtra("KeyNationality",editnation.getText().toString());
                i.putExtra("KeyMarital",editmaritalstatus.getText().toString());
                i.putExtra("KeyDOB",editdob.getText().toString());
                i.putExtra("KeyCustom",editCustom.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }
}
