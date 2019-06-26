package com.example.zunairazamanchaudh.candidateengine.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class Fraginsidetab extends FragmentPagerAdapter {
    private List<Fragment> fragmentList1;
    private List<String> titleList1;
    public Fraginsidetab(FragmentManager fm, List<Fragment> fragmentList1, List<String> titleList1){
        super(fm);
        this.fragmentList1=fragmentList1;
        this.titleList1=titleList1;

    }
    @Override
    public Fragment getItem(int i) {
        return fragmentList1.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList1.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  titleList1.get(position);
    }
}
