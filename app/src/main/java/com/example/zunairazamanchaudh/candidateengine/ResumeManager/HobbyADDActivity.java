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

public class HobbyADDActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnaddHobby;
    Button btnsaveHobby;
    TextInputLayout t1,t2,t3,t4,t5;
    EditText edit1,edit2,edit3,edit4,edit5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby_add);
        edit1=(EditText)findViewById(R.id.hobby1);
        edit2=(EditText)findViewById(R.id.hobby2);
        edit3=(EditText)findViewById(R.id.hobby3);
        edit4=(EditText)findViewById(R.id.hobby4);
        edit5=(EditText)findViewById(R.id.hobby5);
        t1=(TextInputLayout)findViewById(R.id.hobby1Wrapper);
        t2=(TextInputLayout)findViewById(R.id.hobby2Wrapper);
        t3=(TextInputLayout)findViewById(R.id.hobby3Wrapper);
        t4=(TextInputLayout)findViewById(R.id.hobby4Wrapper);
        t5=(TextInputLayout)findViewById(R.id.hobby5Wrapper);
        btnaddHobby=(Button)findViewById(R.id.addHobby);
        btnsaveHobby=(Button)findViewById(R.id.saveHobby);
        btnsaveHobby.setOnClickListener(this);
        btnaddHobby.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addHobby:
                int b;
                if(edit2.getVisibility()==View.VISIBLE && edit3.getVisibility()==View.INVISIBLE) {
                    edit3.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                } else if(edit3.getVisibility()==View.VISIBLE && edit4.getVisibility()==View.INVISIBLE){
                    edit4.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else if(edit4.getVisibility()==View.VISIBLE && edit5.getVisibility()==View.INVISIBLE){
                    edit5.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else if(edit2.getVisibility()==View.INVISIBLE){
                    edit2.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Sufficeint skills have been added already",Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.saveHobby:
                Intent i=new Intent();
                i.putExtra("KeyHobby1",edit1.getText().toString());
                i.putExtra("KeyHobby2",edit2.getText().toString());
                i.putExtra("KeyHobby3",edit3.getText().toString());
                i.putExtra("KeyHobby4",edit4.getText().toString());
                i.putExtra("KeyHobby5",edit5.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;

        }

    }
}
