package com.example.zunairazamanchaudh.candidateengine;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Workdetails extends Fragment  implements View.OnClickListener{

Button btnexp;
Button btnfresher;
    public Workdetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_workdetails, container, false);
        Toolbar tb =view.findViewById(R.id.toolbar);
              btnexp=view.findViewById(R.id.btnexperience);
              btnfresher=view.findViewById(R.id.btnfresher);
              btnexp.setOnClickListener(this);
              btnfresher.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnexperience:




                getActivity().getSupportFragmentManager().beginTransaction()

                        .replace(android.R.id.content, new ExperienceDetail())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                break;
            case R.id.btnfresher:

                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new EducationDetailsfragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                break;
                default:
                    break;
        }
    }
}
