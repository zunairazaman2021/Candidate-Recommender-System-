package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.darabindingRecruiter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.CVsFilterationAdapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.RecruiterJobPostedFilterAdapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.JobPostedview;

import java.util.List;

public class RecruiterJobPostedFilterBindingAdapter {
    private static final int NUM_COLUMNS=1;
    @BindingAdapter("jobPostedList")
    public static void setpostedJobsList(RecyclerView view, List<JobPostedview> jobs){
        if(jobs==null){
            return;
        }
        RecyclerView.LayoutManager layoutManager=view.getLayoutManager();
        if(layoutManager==null){
            view.setLayoutManager(new GridLayoutManager(view.getContext(),NUM_COLUMNS));
        }
        RecruiterJobPostedFilterAdapter adapter=(RecruiterJobPostedFilterAdapter) view.getAdapter();
        if(adapter==null){
            adapter=new RecruiterJobPostedFilterAdapter(view.getContext(),jobs);
            view.setAdapter(adapter);
        }
    }
}