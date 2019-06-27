package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Personalinfo;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ExperienceActivity extends AppCompatActivity implements View.OnClickListener {
     DatePickerDialog fromDatePickerDialog;
     DatePickerDialog toDatePickerDialog;
     EditText Datefrom, Dateto,weOrg,weDesignation,adRole;
     RadioButton chkEmployee,chkCurrentEmp;
     Button btnSaveExp, btnviewlist;
     SimpleDateFormat dateFormatter;
    final Calendar newCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnviewlist = (Button) findViewById(R.id.ViewExpDetails);
        btnviewlist.setOnClickListener(this);
        btnSaveExp = (Button) findViewById(R.id.saveExpDetails);
        btnSaveExp.setOnClickListener(this);
        Datefrom = (EditText) findViewById(R.id.weDate);
        Datefrom.setInputType(InputType.TYPE_NULL);
        Dateto = (EditText) findViewById(R.id.weDate2);
        Dateto.setInputType(InputType.TYPE_NULL);
        weOrg=(EditText)findViewById(R.id.weOrg);
        weDesignation=(EditText)findViewById(R.id.weDesignation);
        adRole=(EditText)findViewById(R.id.expRole);
        chkEmployee=(RadioButton)findViewById(R.id.chkEmp);
        chkCurrentEmp=(RadioButton)findViewById(R.id.chkCurrEmp);
        getexperiencedetails();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            //    myCalendar = Calendar.getInstance();
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                newCalendar.set(Calendar.YEAR, year);
                newCalendar.set(Calendar.MONTH, monthOfYear);
                newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MMM dd, yyyy"; //In which you need put here
                SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.US);
                Datefrom.setText(sdformat.format(newCalendar.getTime()));
            }
        };
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            //    myCalendar = Calendar.getInstance();
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                newCalendar.set(Calendar.YEAR, year);
                newCalendar.set(Calendar.MONTH, monthOfYear);
                newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MMM dd, yyyy"; //In which you need put here
                SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.US);
                Dateto.setText(sdformat.format(newCalendar.getTime()));
            }
        };

        Datefrom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Context context = ExperienceActivity.this;
                new DatePickerDialog(context, date, newCalendar
                        .get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Dateto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = ExperienceActivity.this;
                new DatePickerDialog(context, date2, newCalendar
                        .get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }

    public void updateLabel() {
        String myFormat = "MMM dd, yyyy"; //In which you need put here
        SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.US);
        Datefrom.setText(sdformat.format(newCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ViewExpDetails:
                Intent ee=new Intent(ExperienceActivity.this,ListOfExperienceActivity.class);
                startActivity(ee);
         break;

            case R.id.saveExpDetails:
                saveexpDetailsCV();
                Intent e=new Intent();
                e.putExtra("expOrg",weOrg.getText().toString());
                e.putExtra("expDesignation",weDesignation.getText().toString());
                e.putExtra("expRole",adRole.getText().toString());
                String a;
                if(chkEmployee.isChecked()){
                    e.putExtra("expEmpStatus",chkEmployee.getText().toString());
                }else{
                    e.putExtra("expEmpStatus",chkCurrentEmp.getText().toString());
                }
                setResult(RESULT_OK,e);
                finish();
                break;

        }
    }




    private void getexperiencedetails(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_experience_Resume))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .limitToFirst(1);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        workExperienceCV user=singleSnapshot.getValue(workExperienceCV.class);
                        weDesignation.setText(user.getDesignation());
                        weOrg.setText(user.getOrganization());
                        adRole.setText(user.getRole());
                        Datefrom.setText(user.getFromExp());
                        Dateto.setText(user.getToExp());
                        Boolean status=user.isEmploaymentStatus();
                        if(status==false){
                            chkEmployee.setChecked(true);
                        }else {
                            chkCurrentEmp.setChecked(true);
                        }

                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void saveexpDetailsCV(){
        final workExperienceCV sauser=new workExperienceCV();
        sauser.setDesignation(weDesignation.getText().toString());
        if(chkEmployee.isChecked()){
            sauser.setEmploaymentStatus(false);
        }else{
            sauser.setEmploaymentStatus(true);
        }
        sauser.setOrganization(weOrg.getText().toString());
        sauser.setRole(adRole.getText().toString());
        sauser.setFromExp(Datefrom.getText().toString());
        sauser.setToExp(Dateto.getText().toString());
        sauser.setCv_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
        String key=mDatabase.child("JobSeekerexperiences").push().getKey();

        mDatabase.child("JobSeekerexperiences")
                .child(key)
                .setValue(sauser);

        FirebaseDatabase.getInstance().getReference()
                .child("experience_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(key)
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(ExperienceActivity.this,"Experience details saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(ExperienceActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}


