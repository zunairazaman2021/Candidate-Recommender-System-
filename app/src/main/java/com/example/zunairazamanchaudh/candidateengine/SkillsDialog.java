package com.example.zunairazamanchaudh.candidateengine;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.jobalerts;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.skills;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SkillsDialog extends DialogFragment
        implements View.OnClickListener{

EditText jaskill;
Spinner jaskilllevel;
Button jasavesskilld;
    public SkillsDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_skills_dialog, container, false);
        jaskill=(EditText)view.findViewById(R.id.jaskill);
        jaskilllevel=(Spinner)view.findViewById(R.id.jaskilllevel);
        jasavesskilld=(Button)view.findViewById(R.id.saveskilld);
        jasavesskilld.setOnClickListener(this);
    return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveskilld:
                    saveskillsjobseeker(jaskill.getText().toString(), jaskilllevel.getSelectedItem().toString());
                    getDialog().dismiss();

                break;
              default:
                  break;
        }
    }

    public void saveskillsjobseeker(String s, String s1) {
        skills sauser=new skills();
        sauser.setSkillname(s);
         sauser.setSkillLevel(s1);
        sauser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseDatabase.getInstance().getReference()
                .child("skills")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   redirectjbpreferenceFragment();
                Toast.makeText(getActivity(),"Skill for user id:  "+id+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
