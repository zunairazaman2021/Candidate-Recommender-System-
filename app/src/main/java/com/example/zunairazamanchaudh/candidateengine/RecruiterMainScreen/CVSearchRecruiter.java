package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zunairazamanchaudh.candidateengine.R;

public class CVSearchRecruiter extends Fragment implements View.OnClickListener{
Button searchgoRecruiter;

    public CVSearchRecruiter() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cvsearch_recruiter, container, false);
        searchgoRecruiter=(Button)view.findViewById(R.id.searchgoRecruiter);
        searchgoRecruiter.setOnClickListener(this);
        return  view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchgoRecruiter:
                Intent ii=new Intent(getActivity(),FilterCVsActivity.class);
                startActivity(ii);
                break;
        }
    }
}
