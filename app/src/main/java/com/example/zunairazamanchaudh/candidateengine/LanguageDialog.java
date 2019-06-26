package com.example.zunairazamanchaudh.candidateengine;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.languages;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.skills;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageDialog extends DialogFragment implements View.OnClickListener {


    EditText jalang;
    Spinner jalanglevel;
    Button saveslangd,cancellangd;
    public LanguageDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_language_dialog, container, false);
        jalang=(EditText)view.findViewById(R.id.jalang);
        jalanglevel=(Spinner)view.findViewById(R.id.jalanglevel);
        saveslangd=(Button)view.findViewById(R.id.saveslangd);
        cancellangd=(Button)view.findViewById(R.id.cancellangd);
        saveslangd.setOnClickListener(this);
        cancellangd.setOnClickListener(this);
    return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveslangd:
                if(jalang.getText().toString().trim()!="") {
                    saveLanguagesjobseeker(jalang.getText().toString(), jalanglevel.getSelectedItem().toString());
                    getDialog().dismiss();
                }else{
                    jalang.setError("Field is required");
                }
                break;
            case R.id.cancellangd:
                getDialog().dismiss();
                break;
        }
    }

    public void saveLanguagesjobseeker(String s, String s1) {
        languages sauser=new languages();
        sauser.setLanguage(s);
        sauser.setLanguagelevel(s1);
        sauser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseDatabase.getInstance().getReference()
                .child("languages")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   redirectjbpreferenceFragment();
                Toast.makeText(getActivity(),"Language for user id:  "+id+" saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
