package com.example.zunairazamanchaudh.candidateengine;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.Adapters.RecommendAdapter;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentRecommendedJobsBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendedJobs extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private static final String TAG = "RecommendedJobs";

    // Data binding
    FragmentRecommendedJobsBinding mBinding;


@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentRecommendedJobsBinding.inflate(inflater);
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);

        setupProductsList();
        return mBinding.getRoot();
    }

    private void setupProductsList(){
        Products products = new Products();
        List<JobView> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));
         mBinding.setProducts(productList);
    }
    @Override
    public void onRefresh() {
        Products products = new Products();
        List<JobView> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));
        ((RecommendAdapter)mBinding.recyclervView.getAdapter()).refreshList(productList);
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {

        (mBinding.recyclervView.getAdapter()).notifyDataSetChanged();
        mBinding.swipeRefreshLayout.setRefreshing(false);}
}