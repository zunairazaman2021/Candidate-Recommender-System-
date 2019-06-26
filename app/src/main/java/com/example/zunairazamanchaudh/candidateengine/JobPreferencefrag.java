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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class JobPreferencefrag extends Fragment implements View.OnClickListener{
    public JobPreferencefrag() {
        // Required empty public constructor
    }

    Button btn,btn1;
    EditText a1,city;
    Spinner a2,a3,a4,a5,a6,a7;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_job_preferencefrag, container, false);
        a1=(EditText)view.findViewById(R.id.a1);
        city=(EditText)view.findViewById(R.id.cityjb);
        a7=(Spinner) view.findViewById(R.id.a7);
        a2=(Spinner)view.findViewById(R.id.a2);
        a3=(Spinner)view.findViewById(R.id.a3);
        a4=(Spinner)view.findViewById(R.id.a4);
        a5=(Spinner)view.findViewById(R.id.a5);
        a6=(Spinner)view.findViewById(R.id.a6);
        btn=(Button)view.findViewById(R.id.btnprev3);
        btn1=(Button)view.findViewById(R.id.btnnext3);
        btn1.setOnClickListener(this);
        btn.setOnClickListener(this);
        return view;
    }
 public void saveuserJOBpreference(){
     JobPreference jobPrefuser=new JobPreference();
     jobPrefuser.setDesignation(a1.getText().toString());
     jobPrefuser.setCountry(a3.getSelectedItem().toString());
     jobPrefuser.setCity(city.getText().toString());
     jobPrefuser.setSalary(a7.getSelectedItem().toString());
     jobPrefuser.setIndustry(a4.getSelectedItem().toString());
     jobPrefuser.setEmploymenttype(a5.getSelectedItem().toString());
     jobPrefuser.setCareerlevel(a2.getSelectedItem().toString());
     jobPrefuser.setJobrole(a6.getSelectedItem().toString());
     jobPrefuser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
     FirebaseDatabase.getInstance().getReference()
             .child("jobpreference")
             .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
             .setValue(jobPrefuser).addOnCompleteListener(new OnCompleteListener<Void>() {
         @Override
         public void onComplete(@NonNull Task<Void> task) {
              FirebaseAuth.getInstance().signOut();
             Intent i=new Intent(getActivity(),LoginJobSeeker.class);
             startActivity(i);
              //  redirectLoginScreen();
         }
     }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             //    FirebaseAuth.getInstance().signOut();
             //   redirectLoginJobseekerScreen();
             Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_SHORT).show();
         }
     });
 }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnnext3:
                saveuserJOBpreference();
                break;
            case R.id.btnprev3:
             getActivity().getSupportFragmentManager().beginTransaction()

                        .replace(android.R.id.content, new EducationDetailsfragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .commit();
                break;
            default:
                break;
        }
    }
}
