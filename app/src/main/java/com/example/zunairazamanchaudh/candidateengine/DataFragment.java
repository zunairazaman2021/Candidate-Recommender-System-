package com.example.zunairazamanchaudh.candidateengine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends Fragment {
    View view;
    private List<Fragment> fragmentList=new ArrayList<>();
    private  List<String> titleList=new ArrayList<>();
    public ViewPager viewPager;
    public TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tabbedsample, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        prepareDataSource();
        TextTabsAdapter adapter=new TextTabsAdapter(getActivity().getSupportFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        setTabsIcons();


        return view;
    }

    public void prepareDataSource(){

        fragmentList.add(new Jobs());
        titleList.add("Jobs");
        fragmentList.add(new ApplicationsFragment());
        titleList.add("Applications");
        fragmentList.add(new VisibilityFragment());
        titleList.add("Visibility");
        fragmentList.add(new ProfileFragment());
        titleList.add("My Profile");

    }
    public void setTabsIcons(){
        tabLayout.getTabAt(0).setIcon(R.drawable.briefcase);
        tabLayout.getTabAt(1).setIcon(R.drawable.nearmeapplications);
        tabLayout.getTabAt(2).setIcon(R.drawable.visibilitypolyline);
        tabLayout.getTabAt(3).setIcon(R.drawable.myprofile);
    }
    public class TextTabsAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private List<String> titleList;
        public TextTabsAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList ) {
            super(fm);
            this.fragmentList=fragmentList;
            this.titleList=titleList;
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return  titleList.get(position);
        }

    }

}
