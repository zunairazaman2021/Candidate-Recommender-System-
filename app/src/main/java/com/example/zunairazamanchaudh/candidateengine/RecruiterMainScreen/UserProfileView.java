package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVViewModel;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVs;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentUserProfileViewBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobViewModel;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileView extends Fragment {
    TextView txtdob;


    private static final String TAG = "UserProfileView";

    // Data binding
    FragmentUserProfileViewBinding mBinding;

    //vars
    private CVsview mCV;



    public UserProfileView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mCV = bundle.getParcelable(getString(R.string.intent_cv_user));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // View view=inflater.inflate(R.layout.fragment_user_profile_view, container, false);
        mBinding=FragmentUserProfileViewBinding.inflate(inflater);
        CVViewModel viewProductModel = new CVViewModel();
        viewProductModel.setCv(mCV);
        mBinding.setCVViewm(viewProductModel);
        return mBinding.getRoot();

    }

}
