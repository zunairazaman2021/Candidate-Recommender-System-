package com.example.zunairazamanchaudh.candidateengine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.Adapters.Fraginsidetab;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsFragment extends Fragment {
    private List<Fragment> fragmentList=new ArrayList<>();
    private  List<String> titleList=new ArrayList<>();
    public ViewPager viewPager;
    public TabLayout tabLayout;
    LayoutInflater inflatee;
    public ApplicationsFragment() {
        // Required empty public constructor
        prepareDataSource();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_applications, container, false);
        viewPager=(ViewPager)view.findViewById(R.id.viewPagertabbed);
        tabLayout=(TabLayout)view.findViewById(R.id.tabbedapplication);


        Fraginsidetab adapter=new Fraginsidetab(getChildFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //  setTabsIcons();
        return view;

    }

    public void initialize(){

    }
    public void prepareDataSource(){
        fragmentList.add(new OpenJobsApplied());
        titleList.add("Open Jobs");
        fragmentList.add(new ClosingJobFrag());
        titleList.add("Closed Jobs");
    }


}
