package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.darabindingRecruiter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zunairazamanchaudh.candidateengine.Adapters.FilterAdapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.CVsFilterationAdapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.List;

public class CVFilterationBindingAdapter {
    private static final int NUM_COLUMNS=1;
    @BindingAdapter("cvsList")
    public static void setCVsList(RecyclerView view, List<CVsview> products){
        if(products==null){
            return;
        }
        RecyclerView.LayoutManager layoutManager=view.getLayoutManager();
        if(layoutManager==null){
            view.setLayoutManager(new GridLayoutManager(view.getContext(),NUM_COLUMNS));
        }
        CVsFilterationAdapter adapter=(CVsFilterationAdapter) view.getAdapter();
        if(adapter==null){
            adapter=new CVsFilterationAdapter(view.getContext(),products);
            view.setAdapter(adapter);
        }
    }

}
