package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.Adapters.Fraginsidetab;
import com.example.zunairazamanchaudh.candidateengine.ClosingJobFrag;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.SearchJobs;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostedJobVacancyDetailView extends Fragment {

    private List<Fragment> fragmentList=new ArrayList<>();
    private  List<String> titleList=new ArrayList<>();
    public ViewPager viewPager;
    public TabLayout tabLayout;
    LayoutInflater inflatee;

    public PostedJobVacancyDetailView() {
        // Required empty public constructor
        prepareJobSource();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_posted_job_vacancy_detail_view, container, false);
        viewPager=(ViewPager)view.findViewById(R.id.viewPagertabbedJob);
        tabLayout=(TabLayout)view.findViewById(R.id.tabbedapplicationJob);


        Fraginsidetab adapter=new Fraginsidetab(getChildFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public void prepareJobSource(){
        fragmentList.add(new AllCandidatesAppliedJobVacancy());
        titleList.add("ALL CANDIDATES");
        fragmentList.add(new AllCandidatesAppliedJobVacancy());
        titleList.add("INTERESTED");
        fragmentList.add(new AllCandidatesAppliedJobVacancy());
        titleList.add("MAYBE");
    }

}
