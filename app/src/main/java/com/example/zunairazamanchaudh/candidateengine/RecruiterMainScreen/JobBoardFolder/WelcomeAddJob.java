package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobPostVacancyActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeAddJob extends Fragment implements View.OnClickListener{

    Button btnl;
    public WelcomeAddJob() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_welcome_add_job, container, false);
        btnl=(Button)view.findViewById(R.id.addJobposting);
        btnl.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addJobposting:
                Intent i=new Intent(getActivity(), JobPostVacancyActivity.class);
                startActivity(i);
                break;
        }
    }
}
