package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.zunairazamanchaudh.candidateengine.R;

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
                ArrayList<WorkExperience> experienceArrayList = new ArrayList<WorkExperience>();
                SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date1 = dateformat3.parse("17/7/2000");
                    Date date2 = dateformat3.parse("17/12/2001");
                    experienceArrayList.add(new WorkExperience("Tesco", "Marketing Manager", "Served as marketing manager at Tesco companies", true, date1, date2));
                    experienceArrayList.add(new WorkExperience("Tesco", "Marketing Manager", "Served as marketing manager at Tesco companies", true, date1, date2));
                    experienceArrayList.add(new WorkExperience("Tesco", "Marketing Manager", "Served as marketing manager at Tesco companies", true, date1, date2));
                   /* getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new ListWorkExperienceDetails(getActivity(), experienceArrayList))
                            .commit();
                    //    fragment=new ListofAcademicDetailsFragment(getActivity().getApplicationContext());
*/
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            case R.id.saveExpDetails:
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
}


