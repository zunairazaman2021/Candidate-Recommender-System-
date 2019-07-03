package com.example.zunairazamanchaudh.candidateengine;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.Adapters.FilterAdapter;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentRecommendedJobsBinding;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentSearchBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    public SearchFragment() {
        // Required empty public constructor
    }
    FragmentSearchBinding mBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentSearchBinding.inflate(inflater);
      //  mBinding.swipeRefreshLayout2.setOnRefreshListener(this);

        setupProductsList();
        return mBinding.getRoot();
    }

    private void setupProductsList(){
        Products products = new Products();
        List<JobView> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));
        mBinding.setJobs(productList);
    }
/*
    @Override
    public void onRefresh() {
        Products products = new Products();
        List<JobView> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));

        ((FilterAdapter)mBinding.filterjobRecycler.getAdapter()).refreshList(productList);
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        (mBinding.filterjobRecycler.getAdapter()).notifyDataSetChanged();
        mBinding.swipeRefreshLayout2.setRefreshing(false);}
*/
}
