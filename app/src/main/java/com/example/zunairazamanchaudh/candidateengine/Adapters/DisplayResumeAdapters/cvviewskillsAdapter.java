package com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.SkillsCV;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.ResumeManager.ViewFullCV;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.List;

public class cvviewskillsAdapter extends RecyclerView.Adapter<cvviewskillsAdapter.ViewHolderCVSkill>{
    Context context;
    List<SkillsCV> edu;

    public cvviewskillsAdapter(List<SkillsCV> edu) {
        this.edu = edu;
    }

    public cvviewskillsAdapter(ViewFullCV viewFullCV, List<SkillsCV> list) {
    edu=list;
    }

    @NonNull
    @Override
    public ViewHolderCVSkill onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_view_skills,
                viewGroup, false);
        cvviewskillsAdapter.ViewHolderCVSkill viewHolder = new cvviewskillsAdapter.ViewHolderCVSkill(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCVSkill holder, int i) {
        final SkillsCV job = edu.get(i);
        holder.vSkill.setText(job.getSkillName());

    }

    @Override
    public int getItemCount() {
        return edu.size();
    }

   public class ViewHolderCVSkill extends RecyclerView.ViewHolder {
        public TextView vSkill;
        View mView;

        public ViewHolderCVSkill(final View itemView) {

            super(itemView);
            mView = itemView;
            vSkill = (TextView) itemView.findViewById(R.id.vSkill);
             }

    }
}
