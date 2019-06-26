package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zunairazamanchaudh.candidateengine.R;

public class ObjectiveActivity extends AppCompatActivity implements View.OnClickListener{
EditText eObj,eDate,ePlace;
Button btnSaveObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objective);
        eObj=(EditText)findViewById(R.id.ObjDescription);
        eDate=(EditText)findViewById(R.id.ObjDate);
        ePlace=(EditText)findViewById(R.id.Objplace);
        btnSaveObj=(Button)findViewById(R.id.saveObjective);
        btnSaveObj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveObjective:
                Intent objintent=new Intent();
                objintent.putExtra("KeyObjective",eObj.getText().toString());
                objintent.putExtra("KeyDate",eDate.getText().toString());
                objintent.putExtra("KeyPlace",ePlace.getText().toString());
                setResult(RESULT_OK,objintent);
                finish();
                break;
        }
    }
}
