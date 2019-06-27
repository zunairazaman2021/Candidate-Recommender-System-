package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.workExperienceCV;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AcademicDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnsaveacademic;
    private EditText editmark, editpassingyear,course,school;
    private RadioButton chkPercentage, chkGrade, chkgraduate, chkpursuing;

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
        getAcademyDetails();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewAcademicDetails:
                Intent i=new Intent(AcademicDetailsActivity.this,ListOfAcademicDetailsActivity.class);
                startActivity(i);
            case R.id.saveAcademicDetails:
                saveAcademyDetailsCV();
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


    private void getAcademyDetails(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_education_Resume))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .limitToFirst(1);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Academic user=singleSnapshot.getValue(Academic.class);
                        course.setText(user.getName());
                        school.setText(user.getInstituition());
                        editmark.setText(user.getGrade());
                        editpassingyear.setText(Integer.toString(user.getPassingYear()));
                        if(user.isStatuscompletion()==true){
                            chkgraduate.setChecked(true);
                        }else {
                            chkpursuing.setChecked(false);
                        }
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void saveAcademyDetailsCV(){
        final Academic sauser=new Academic();
        sauser.setName(course.getText().toString());
        sauser.setInstituition(school.getText().toString());
        sauser.setGrade(editmark.getText().toString());
        sauser.setPassingYear(Integer.valueOf(editpassingyear.getText().toString()));
        if(chkpursuing.isChecked()) {
            sauser.setStatuscompletion(false);
        }else {
            sauser.setStatuscompletion(true);
        }
        sauser.setCv_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
        String key=mDatabase.child("JobSeekersEducation").push().getKey();

        mDatabase.child("JobSeekersEducation")
                .child(key)
                .setValue(sauser);

        FirebaseDatabase.getInstance().getReference()
                .child("education_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(key)
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(AcademicDetailsActivity.this,"Education details saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AcademicDetailsActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}