package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.SearchFragment;
import com.example.zunairazamanchaudh.candidateengine.ViewRecommendJobDetail;
import com.example.zunairazamanchaudh.candidateengine.databinding.ActivityFilterCvsBinding;

public class FilterCVsActivity extends AppCompatActivity implements IMainActivityRecruiter {
ActivityFilterCvsBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_filter_cvs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarfilterCV);
        setSupportActionBar(toolbar);
        Button btn=(Button)findViewById(R.id.filterCVbtn);
        Fragment fragment=new CVDisplay();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.somefilterCVcontent, fragment);
        ft.commit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void inflateViewCVFragment(CVsview cvView) {
        UserProfileView fragment = new UserProfileView();
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.intent_cv_user), cvView);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.somefilterCVcontent, fragment, getString(R.string.fragment_filter_cv));
        transaction.addToBackStack(getString(R.string.fragment_filter_cv));
        transaction.commit();

    }
}
