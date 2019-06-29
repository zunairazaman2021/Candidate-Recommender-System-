package com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.workExperienceCV;
import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.List;

public class cvviewexperienceAdapter extends RecyclerView.Adapter<cvviewexperienceAdapter.ViewHolderCVExp> {
    Context context;
    List<workExperienceCV> edu;

    public cvviewexperienceAdapter(List<workExperienceCV> edu) {
           this.edu = edu;
    }

    @NonNull
    @Override
    public ViewHolderCVExp onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_view_experience,
                viewGroup, false);
        cvviewexperienceAdapter.ViewHolderCVExp viewHolder = new cvviewexperienceAdapter.ViewHolderCVExp(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCVExp holder, int i) {
        final workExperienceCV job = edu.get(i);
        holder.vDesignation.setText(job.getDesignation());
        holder.vOrganizaion.setText(job.getOrganization());
        holder.vRole.setText(job.getRole());
        holder.vExperience.setText("From :"+job.getFromExp()+" to "+job.getToExp());
    }

    @Override
    public int getItemCount() {
        return edu.size();
    }

   public class ViewHolderCVExp extends RecyclerView.ViewHolder {
        public TextView vDesignation;
        public TextView vOrganizaion;
        public TextView vRole;
        public TextView vExperience;
        View mView;

        public ViewHolderCVExp(final View itemView) {

            super(itemView);
            mView = itemView;
            vDesignation = (TextView) itemView.findViewById(R.id.vDesignation);
            vOrganizaion = (TextView) itemView.findViewById(R.id.vOrganization);
            vRole = (TextView) itemView.findViewById(R.id.vRole);
            vExperience = (TextView) itemView.findViewById(R.id.vExperience);
        }

    }
}
