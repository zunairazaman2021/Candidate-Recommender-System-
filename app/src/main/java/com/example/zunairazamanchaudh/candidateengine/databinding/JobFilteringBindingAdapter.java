package com.example.zunairazamanchaudh.candidateengine.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zunairazamanchaudh.candidateengine.Adapters.FilterAdapter;
import com.example.zunairazamanchaudh.candidateengine.Adapters.RecommendAdapter;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.List;

public class JobFilteringBindingAdapter {
    private static final int NUM_COLUMNS=1;
    @BindingAdapter("JobsList")
    public static void setJobsList(RecyclerView view, List<JobView> products){
        if(products==null){
            return;
        }
        RecyclerView.LayoutManager layoutManager=view.getLayoutManager();
        if(layoutManager==null){
            view.setLayoutManager(new GridLayoutManager(view.getContext(),NUM_COLUMNS));
        }
        FilterAdapter adapter=(FilterAdapter) view.getAdapter();
        if(adapter==null){
            adapter=new FilterAdapter(view.getContext(),products);
            view.setAdapter(adapter);
        }
    }

}
