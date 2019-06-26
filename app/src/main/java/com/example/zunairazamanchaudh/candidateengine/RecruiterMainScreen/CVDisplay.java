package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.Products;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVs;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentCvdisplayBinding;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentSearchBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CVDisplay extends Fragment {
private static final String TAG="CVDisplay";

    public CVDisplay() {
        // Required empty public constructor
    }
FragmentCvdisplayBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   //     return inflater.inflate(R.layout.fragment_cvdisplay, container, false);
        mBinding = FragmentCvdisplayBinding.inflate(inflater);
        //  mBinding.swipeRefreshLayout2.setOnRefreshListener(this);
        setupCVsList();
        return mBinding.getRoot();

    }
    private void setupCVsList(){
        CVs cv = new CVs();
        List<CVsview> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(cv.CVS));
        mBinding.setCvs(productList);
    }
}
