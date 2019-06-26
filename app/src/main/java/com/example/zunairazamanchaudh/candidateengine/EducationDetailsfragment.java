package com.example.zunairazamanchaudh.candidateengine;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.education;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class EducationDetailsfragment extends Fragment implements View.OnClickListener {

    private Button btnsaveacademic,btnback;
    private EditText editmark, editpassingyear,course,school;
    private RadioButton chkPercentage, chkGrade, chkgraduate, chkpursuing;

    public EducationDetailsfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_education_detailsfragment, container, false);
        course=(EditText)view.findViewById(R.id.adCourseReg);
        school=(EditText)view.findViewById(R.id.adschoolReg);
        editmark = (EditText) view.findViewById(R.id.adMarkReg);
        editpassingyear = (EditText) view.findViewById(R.id.adYearReg);
        chkPercentage = (RadioButton) view.findViewById(R.id.chkperReg);
        chkGrade = (RadioButton) view.findViewById(R.id.chkgradeReg);
        chkgraduate = (RadioButton) view.findViewById(R.id.chkgraduatedReg);
        chkpursuing = (RadioButton)view. findViewById(R.id.chkpursuingReg);
        btnback=(Button)view.findViewById(R.id.backReg);
        btnback.setOnClickListener(this);
        btnsaveacademic = (Button)view. findViewById(R.id.saveEduReg);
        btnsaveacademic.setOnClickListener(this);

    return view;
    }


    public void redirectjbpreferenceFragment(){

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, new JobPreferencefrag())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

    }
public void saveeducationdetails(){
    education eduuser=new education();
    eduuser.setCourse(course.getText().toString());
    eduuser.setInstitute(school.getText().toString());
    eduuser.setGrade(editmark.getText().toString());
    String status;
    if(chkgraduate.isChecked()){
        status="graduated";
    }else {
        status="pursuing";
    }
    eduuser.setStatus(status);
    String year=editpassingyear.getText().toString();
    eduuser.setPassyear(Integer.valueOf(year));
    eduuser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
    FirebaseDatabase.getInstance().getReference()
            .child("education")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .setValue(eduuser).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            redirectjbpreferenceFragment();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {

            Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_SHORT).show();
        }
    });

}
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backReg:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new ExperienceDetail())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
            case R.id.saveEduReg:
                saveeducationdetails();

                break;
        }
    }
}
