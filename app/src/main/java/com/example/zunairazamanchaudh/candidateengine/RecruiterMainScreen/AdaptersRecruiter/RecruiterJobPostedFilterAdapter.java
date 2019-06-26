package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.BR;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.IMainActivityRecruiter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.IMainActivityPostedJob;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.JobPostedview;
import com.example.zunairazamanchaudh.candidateengine.databinding.FiltercvdetailBinding;
import com.example.zunairazamanchaudh.candidateengine.databinding.FilterjobslistBinding;
import com.example.zunairazamanchaudh.candidateengine.databinding.RecruiterViewpostedjobsListBinding;

import java.util.ArrayList;
import java.util.List;

public class RecruiterJobPostedFilterAdapter extends RecyclerView.Adapter<RecruiterJobPostedFilterAdapter.BindingHolderJobsposted>{
    private static final String TAG = "RecruiterJobPostedFilterAdapter";

    private List<JobPostedview> mJobs = new ArrayList<>();
    private Context mContext;

    public RecruiterJobPostedFilterAdapter(Context mContext, List<JobPostedview> mJobs) {
        this.mJobs = mJobs;
        this.mContext = mContext;
    }
    public void refreshList(List<JobPostedview> products){
        mJobs.clear();
        mJobs.addAll(products);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BindingHolderJobsposted onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecruiterViewpostedjobsListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.recruiter_viewpostedjobs_list, viewGroup, false);

        return new RecruiterJobPostedFilterAdapter.BindingHolderJobsposted(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolderJobsposted bindingHolderJobsposted, int i) {
        JobPostedview job = mJobs.get(i);
       bindingHolderJobsposted.binding.setJobpost(job);

        bindingHolderJobsposted.binding.setIMainActivityPostedJob((IMainActivityPostedJob)mContext);
      // bindingHolderJobsposted.binding.setIMainActivityRecruiter((IMainActivityRecruiter) mContext);
        bindingHolderJobsposted.binding.setVariable(BR.cvs, job);
        bindingHolderJobsposted.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }


    public class BindingHolderJobsposted extends RecyclerView.ViewHolder{

        RecruiterViewpostedjobsListBinding binding;
        public BindingHolderJobsposted(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
