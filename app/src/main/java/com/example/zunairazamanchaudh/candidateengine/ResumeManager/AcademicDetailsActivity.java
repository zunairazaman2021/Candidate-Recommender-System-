package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.ArrayList;

public class AcademicDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnsaveacademic;
    private EditText editmark, editpassingyear,course,school;
    private RadioButton chkPercentage, chkGrade, chkgraduate, chkpursuing;
    ArrayList<Courses> courses = new ArrayList<Courses>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_details);
        course=(EditText)findViewById(R.id.adCourse);
        school=(EditText)findViewById(R.id.adschool);
        editmark = (EditText) findViewById(R.id.adMark);
        editpassingyear = (EditText) findViewById(R.id.adYear);
        chkPercentage = (RadioButton) findViewById(R.id.chkper);
        chkGrade = (RadioButton) findViewById(R.id.chkgrade);
        chkgraduate = (RadioButton) findViewById(R.id.chkgraduated);
        chkpursuing = (RadioButton) findViewById(R.id.chkpursuing);
        btnsaveacademic = (Button) findViewById(R.id.saveAcademicDetails);
        btnsaveacademic.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ArrayList<Courses> coursesArrayList = new ArrayList<Courses>();
        coursesArrayList.add(new Courses("BSSE", "UOG", "A+", 2019));
        switch (v.getId()) {
            case R.id.viewAcademicDetails:
                Intent i=new Intent(AcademicDetailsActivity.this,ListOfAcademicDetailsActivity.class);
             //   coursesArrayList.add(new Courses("BSSE", "UOG", "A+", 2019));
             //   i.putExtra("coursearray",coursesArrayList);
                startActivity(i);
            case R.id.saveAcademicDetails:
                Intent iacademic=new Intent();
                iacademic.putExtra("KeyCourse",course.getText().toString());
                iacademic.putExtra("KeySchool",school.getText().toString());
                iacademic.putExtra("KeyGrade",editmark.getText().toString());
                iacademic.putExtra("KeyPassYear",editpassingyear.getText().toString());
                setResult(RESULT_OK,iacademic);
                finish();
                break;
        }
    }
}