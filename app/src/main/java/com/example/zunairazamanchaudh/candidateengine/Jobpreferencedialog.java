package com.example.zunairazamanchaudh.candidateengine;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static android.text.TextUtils.isEmpty;

public class Jobpreferencedialog extends DialogFragment implements View.OnClickListener {
  EditText aa1,aa7;
  Spinner aa2,aa3,aa4,aa5,aa6;
  Button savepreference;

    public Jobpreferencedialog() {

    }
    public static Jobpreferencedialog newInstance(String param1, String param2) {
        Jobpreferencedialog fragment = new Jobpreferencedialog();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_jobpreferencedialog, container, false);
        aa1=(EditText)view.findViewById(R.id.aa1);
        aa7=(EditText)view.findViewById(R.id.aa7);
        aa2=(Spinner)view.findViewById(R.id.aa2);
        aa3=(Spinner)view.findViewById(R.id.aa3);
        aa4=(Spinner)view.findViewById(R.id.aa4);
        aa5=(Spinner)view.findViewById(R.id.aa5);
        aa6=(Spinner)view.findViewById(R.id.aa6);
        savepreference=(Button)view.findViewById(R.id.savepreferenceDB);
        savepreference.setOnClickListener(this);
    return  view;
    }

    @Override
    public void onClick(View v) {
        if (isEmpty(aa1.getText().toString().trim())) {
            aa1.setError("Field cannot be empty");
        }
        if (isEmpty(aa7.getText().toString().trim())) {
            aa7.setError("Field cannot be empty");
        }
        if (!isEmpty(aa1.getText().toString()) && !isEmpty(aa7.getText().toString())) {
            savejobpreferences(aa1.getText().toString(), aa2.getSelectedItem().toString(), aa3.getSelectedItem().toString(),
                    aa4.getSelectedItem().toString(), aa4.getSelectedItem().toString(), aa5.getSelectedItem().toString(),
                    aa6.getSelectedItem().toString(), aa7.getText().toString()
            );
            getDialog().dismiss();
        }
    }

    private void savejobpreferences(String s, String s1, String s2, String s3,
                                    String s4, String s5, String s6, String s7) {


    }
}
