package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zunairazamanchaudh.candidateengine.R;

public class ContacsDetailsActivity extends AppCompatActivity implements View.OnClickListener {
EditText ciName,ciAddress,ciEmail,ciPhone;
Button saveContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacs_details);
        ciName=(EditText)findViewById(R.id.ciName);
        ciAddress=(EditText)findViewById(R.id.ciAddress);
        ciEmail=(EditText)findViewById(R.id.ciEmail);
        ciPhone=(EditText)findViewById(R.id.ciPhone);
        saveContact=(Button)findViewById(R.id.savecontactdetails);
        saveContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.savecontactdetails:
                Intent i=new Intent();
                i.putExtra("KeyName",ciName.getText().toString());
                i.putExtra("KeyAdress",ciAddress.getText().toString());
                i.putExtra("KeyEmail",ciEmail.getText().toString());
                i.putExtra("KeyPhone",ciPhone.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }
}
