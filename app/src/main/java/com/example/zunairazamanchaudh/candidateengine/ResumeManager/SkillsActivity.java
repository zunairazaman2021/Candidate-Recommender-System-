package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.R;

public class SkillsActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnaddaskill;
    Button btnsaveskill;
     TextInputLayout t1,t2,t3,t4,t5,t6,t7;
    Context sContext;
     EditText edit1,edit2,edit3,edit4,edit5,edit6,edit7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        edit1=(EditText)findViewById(R.id.skSkill_1);
        edit2=(EditText)findViewById(R.id.skSkill_2);
        edit3=(EditText)findViewById(R.id.skSkill_3);
        edit4=(EditText)findViewById(R.id.skSkill_4);
        edit5=(EditText)findViewById(R.id.skSkill_5);
        edit6=(EditText)findViewById(R.id.skSkill_6);
        edit7=(EditText)findViewById(R.id.skSkill_7);
        t1=(TextInputLayout)findViewById(R.id.skill1Wrapper);
        t2=(TextInputLayout)findViewById(R.id.skill2Wrapper);
        t3=(TextInputLayout)findViewById(R.id.skill3Wrapper);
        t4=(TextInputLayout)findViewById(R.id.skill4Wrapper);
        t5=(TextInputLayout)findViewById(R.id.skill5Wrapper);
        t6=(TextInputLayout)findViewById(R.id.skill6Wrapper);
        t7=(TextInputLayout)findViewById(R.id.skill7Wrapper);
        btnaddaskill=(Button)findViewById(R.id.addSkill);
        btnsaveskill=(Button)findViewById(R.id.saveSkill);
        btnsaveskill.setOnClickListener(this);
        btnaddaskill.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addSkill:
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
                }else if(edit5.getVisibility()==View.VISIBLE && edit6.getVisibility()==View.INVISIBLE){
                    edit6.setVisibility(View.VISIBLE);
                    t6.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else if(edit6.getVisibility()==View.VISIBLE && edit7.getVisibility()==View.INVISIBLE){
                    edit7.setVisibility(View.VISIBLE);
                    t7.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else if(edit2.getVisibility()==View.INVISIBLE){
                    edit2.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"Add new Skill",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Sufficeint skills have been added already",Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.saveSkill:
                Intent i=new Intent();
                i.putExtra("KeySkill1",edit1.getText().toString());
                i.putExtra("KeySkill2",edit2.getText().toString());
                i.putExtra("KeySkill3",edit3.getText().toString());
                i.putExtra("KeySkill4",edit4.getText().toString());
                i.putExtra("KeySkill5",edit5.getText().toString());
                i.putExtra("KeySkill6",edit6.getText().toString());
                i.putExtra("KeySkill7",edit7.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }

    }
}
