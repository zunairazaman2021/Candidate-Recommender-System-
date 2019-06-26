package com.example.zunairazamanchaudh.candidateengine;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.experience;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExperienceDetail extends Fragment implements View.OnClickListener {
    DatePickerDialog fromDatePickerDialog;
    DatePickerDialog toDatePickerDialog;
    EditText Datefrom, Dateto,weOrg,weDesignation,adRole;
    RadioButton chkEmployee,chkCurrentEmp;
    Button btnSaveExp, btnviewlist;
    SimpleDateFormat dateFormatter;
    final Calendar newCalendar = Calendar.getInstance();


    public ExperienceDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment_experience_detail, container, false);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnviewlist = (Button) view.findViewById(R.id.saveexpReg);
        btnviewlist.setOnClickListener(this);
        btnSaveExp = (Button)view. findViewById(R.id.backexpReg);
        btnSaveExp.setOnClickListener(this);
        Datefrom = (EditText)view. findViewById(R.id.weDateReg);
        Datefrom.setInputType(InputType.TYPE_NULL);
        Dateto = (EditText) view.findViewById(R.id.weDate2Reg);
        Dateto.setInputType(InputType.TYPE_NULL);
        weOrg=(EditText)view.findViewById(R.id.weOrgReg);
        weDesignation=(EditText)view.findViewById(R.id.weDesignationReg);
        adRole=(EditText)view.findViewById(R.id.expRoleReg);
        chkEmployee=(RadioButton)view.findViewById(R.id.chkEmpReg);
        chkCurrentEmp=(RadioButton)view.findViewById(R.id.chkCurrEmpReg);
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
                Context context = getActivity();
                new DatePickerDialog(context, date, newCalendar
                        .get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Dateto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity();
                new DatePickerDialog(context, date2, newCalendar
                        .get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });



        return view;
    }
    public void updateLabel() {
        String myFormat = "MMM dd, yyyy"; //In which you need put here
        SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.US);
        Datefrom.setText(sdformat.format(newCalendar.getTime()));
    }
public void redirectEducationFragment(){

    getActivity().getSupportFragmentManager().beginTransaction()
            .add(android.R.id.content, new EducationDetailsfragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit();

}
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveexpReg:
                String empstatus;
                if(chkCurrentEmp.isChecked()){
                    empstatus="Employed";
                }else{
                    empstatus="Retired";
                }
                experience expuser=new experience();
                expuser.setOrganization(weOrg.getText().toString());
                expuser.setDesignation(weDesignation.getText().toString());
                expuser.setRole(adRole.getText().toString());
                expuser.setExpFrom(Datefrom.getText().toString());
                expuser.setExpTo(Dateto.getText().toString());
                expuser.setEmpStatus(empstatus);
                expuser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
                FirebaseDatabase.getInstance().getReference()
                        .child("experience")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(expuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // FirebaseAuth.getInstance().signOut();
                       redirectEducationFragment();
                        //  redirectLoginScreen();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                 //       FirebaseAuth.getInstance().signOut();
                     //   redirectLoginJobseekerScreen();
                        Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_SHORT).show();
                    }
                });
               /* getActivity().getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new EducationDetailsfragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
               */
                break;
            case R.id.backexpReg:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new Workdetails())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
        }
    }
}
