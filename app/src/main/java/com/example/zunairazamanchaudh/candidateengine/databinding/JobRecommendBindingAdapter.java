package com.example.zunairazamanchaudh.candidateengine.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zunairazamanchaudh.candidateengine.Adapters.RecommendAdapter;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.List;

public class JobRecommendBindingAdapter {
    private static final int NUM_COLUMNS=2;
    @BindingAdapter("productsList")
    public static void setProductsList(RecyclerView view, List<JobView> products){
        if(products==null){
            return;
        }
        RecyclerView.LayoutManager layoutManager=view.getLayoutManager();
        if(layoutManager==null){
            view.setLayoutManager(new GridLayoutManager(view.getContext(),NUM_COLUMNS));
        }
        RecommendAdapter adapter=(RecommendAdapter) view.getAdapter();
        if(adapter==null){
            adapter=new RecommendAdapter(view.getContext(),products);
            view.setAdapter(adapter);
        }
    }
}
