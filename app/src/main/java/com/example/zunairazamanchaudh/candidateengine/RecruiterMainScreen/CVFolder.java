package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVs;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentAllCandidatesAppliedJobVacancyBinding;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentCvfolderBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CVFolder extends Fragment {
    FragmentCvfolderBinding fragmentCvfolderBinding;

    public CVFolder() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCvfolderBinding= FragmentCvfolderBinding.inflate(inflater);
        setupCVsListt();
        return fragmentCvfolderBinding.getRoot();
     //   return inflater.inflate(R.layout.fragment_cvfolder, container, false);
    }
    private void setupCVsListt(){
        CVs cv = new CVs();
        List<CVsview> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(cv.CVS));
        fragmentCvfolderBinding.setCvs(productList);
    }
}
