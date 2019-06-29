package com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.ProjectDetails;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.ResumeManager.ViewFullCV;

import java.util.List;

public class cvviewprojectAdapter extends RecyclerView.Adapter<cvviewprojectAdapter.ViewHolderCVProject>{
    Context context;
    List<ProjectDetails> edu;
    ViewFullCV viewFullCV;
    public cvviewprojectAdapter(Context context,List<ProjectDetails> edu) {
        this.context = context;
        this.edu = edu;
    }
    public cvviewprojectAdapter(List<ProjectDetails> edu) {
      //  this.context = context;
        this.edu = edu;
    }

    @NonNull
    @Override
    public cvviewprojectAdapter.ViewHolderCVProject onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_view_project,
                viewGroup, false);
        cvviewprojectAdapter.ViewHolderCVProject viewHolder = new cvviewprojectAdapter.ViewHolderCVProject(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull cvviewprojectAdapter.ViewHolderCVProject holder, int i) {
        final ProjectDetails job = edu.get(i);
        holder.vProject.setText(job.getTitle());
//        holder.vRole.setText("");
        holder.vdescription.setText(job.getDescription());
        }

    @Override
    public int getItemCount() {
        return edu.size();
    }

    public class ViewHolderCVProject extends RecyclerView.ViewHolder {
        public TextView vProject;
        public TextView vRole;
        public TextView vdescription;
        View mView;

        public ViewHolderCVProject(final View itemView) {

            super(itemView);
            mView = itemView;
            vProject = (TextView) itemView.findViewById(R.id.vProject);
            vRole = (TextView) itemView.findViewById(R.id.vRole);
            vdescription = (TextView) itemView.findViewById(R.id.vDescription);
            }

    }
}
