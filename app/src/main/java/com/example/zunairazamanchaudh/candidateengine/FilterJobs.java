package com.example.zunairazamanchaudh.candidateengine;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.zunairazamanchaudh.candidateengine.Adapters.FilterAdapter;
import com.example.zunairazamanchaudh.candidateengine.databinding.ActivityFilterJobsBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FilterJobs extends AppCompatActivity implements IMainActivity {

    @Override
    public void inflateViewProductFragment(JobView jobView) {
        ViewRecommendJobDetail fragment = new ViewRecommendJobDetail();

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.intent_product), jobView);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.somefiltercontent, fragment, getString(R.string.fragment_filter));
        transaction.addToBackStack(getString(R.string.fragment_filter));
        transaction.commit();
    }

    ActivityFilterJobsBinding mBinding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_filter_jobs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarfilterjob);
        setSupportActionBar(toolbar);
        Button btn=(Button)findViewById(R.id.filterbtn);
        Fragment fragment=new SearchFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.somefiltercontent, fragment);
        ft.commit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             }
        });
    }
    }
