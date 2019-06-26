package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.Adapters.RecommendAdapter;
import com.example.zunairazamanchaudh.candidateengine.Products;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.RecruiterJobPostedFilterAdapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.IMainActivityPostedJob;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobPostVacancyActivity;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.JobPostedData;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.JobPostedview;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentPostedJobsBinding;
import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentRecommendedJobsBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;
import com.google.android.gms.plus.PlusOneButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A fragment with a Google +1 button.
 */
public class PostedJobsFragment extends Fragment  implements View.OnClickListener{

    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    ImageButton btnmenu;
    private static final String TAG = "PostedJobsFragment";

    // Data binding
    FragmentPostedJobsBinding mBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_posted_jobs, container, false);
       mBinding = FragmentPostedJobsBinding.inflate(inflater);
       View view=mBinding.getRoot();
       FloatingActionButton fabplus=(FloatingActionButton)view.findViewById(R.id.plus_job);
        fabplus.setOnClickListener(this);
       //        btnmenu=(ImageButton)view.findViewById(R.id.option_job);
//        btnmenu.setOnClickListener(this);
        //  imageButton.setOnClickListener(this);
  //      setupPostedJobsList();
        return mBinding.getRoot();

        //Find the +1 button
        //btnmenu=(ImageButton)view.findViewById(R.id.optionsJob);
     //   btnmenu.setOnClickListener(this);

        //return view;
    }

    private void setupPostedJobsList(){
        JobPostedData products = new JobPostedData();
        List<JobPostedview> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.jpv));
        mBinding.setJobposts(productList);
        // mBinding.setProducts(productList);

//        mBinding.setJobpost(productList);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.option_job:
        /*        PopupMenu popup = new PopupMenu(getActivity(), btnmenu);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.jobstatusmenu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                getActivity(),
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
*/
                break;
            case R.id.plus_job:
                Intent i=new Intent(getActivity(),JobPostVacancyActivity.class);
                startActivity(i);
                break;
 }
    }
}
