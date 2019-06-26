package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.R;

public class IndustryExposureActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnaddIndustry;
    Button btnsaveIndustry;
    TextInputLayout t1,t2,t3;
    EditText edit1,edit2,edit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry_exposure);
        edit1=(EditText)findViewById(R.id.industry1);
        edit2=(EditText)findViewById(R.id.industry2);
        edit3=(EditText)findViewById(R.id.industry3);
        t1=(TextInputLayout)findViewById(R.id.industry1Wrapper);
        t2=(TextInputLayout)findViewById(R.id.industry2Wrapper);
        t3=(TextInputLayout)findViewById(R.id.industry3Wrapper);
        btnaddIndustry=(Button)findViewById(R.id.btnaddIndustry);
        btnsaveIndustry=(Button)findViewById(R.id.btnsaveIndustry);
        btnsaveIndustry.setOnClickListener(this);
        btnaddIndustry.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnaddIndustry:
                if(edit2.getVisibility()==View.VISIBLE && edit3.getVisibility()==View.INVISIBLE) {
                    edit3.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Industry",Toast.LENGTH_SHORT).show();
                }else if(edit2.getVisibility()==View.INVISIBLE){
                    edit2.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Industry",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Sufficeint industries have been added already",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnsaveIndustry:
                Intent i=new Intent();
                i.putExtra("KeyIndustry1",edit1.getText().toString());
                i.putExtra("KeyIndustry2",edit2.getText().toString());
                i.putExtra("KeyIndustry3",edit3.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }
}
