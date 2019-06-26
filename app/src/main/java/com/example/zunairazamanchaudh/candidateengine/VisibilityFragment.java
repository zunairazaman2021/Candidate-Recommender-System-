package com.example.zunairazamanchaudh.candidateengine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.Adapters.Fraginsidevisibility;

import java.util.ArrayList;
import java.util.List;

public class VisibilityFragment extends Fragment {
    private List<Fragment> fragmentList=new ArrayList<>();
    private  List<String> titleList=new ArrayList<>();
    public ViewPager viewPager;
    public TabLayout tabLayout;
    LayoutInflater inflatee;


    public VisibilityFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_visibility, container, false);
        viewPager=(ViewPager)view.findViewById(R.id.viewPagervisibility);

        tabLayout=(TabLayout)view.findViewById(R.id.tabbedvisibility);
        prepareDataSourceVisibility();

        Fraginsidevisibility adapter=new Fraginsidevisibility(getChildFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //  setTabsIcons();
        return view;


    }
    public void prepareDataSourceVisibility(){

        fragmentList.add(new ProfileViewes());
        titleList.add("Who Viewed My Profile");
        fragmentList.add(new ProfileViewes());
        titleList.add("Charts");

    }


}
