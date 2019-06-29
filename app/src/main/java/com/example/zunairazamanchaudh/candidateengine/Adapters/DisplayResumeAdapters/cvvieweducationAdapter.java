package com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.SearchCV2Adapter;

import java.util.ArrayList;
import java.util.List;

public class cvvieweducationAdapter extends RecyclerView.Adapter<cvvieweducationAdapter.ViewHolderCVEdu> {
    Context context;
    List<Academic> edu;

    public cvvieweducationAdapter( List<Academic> edu) {
        this.edu = edu;
    }

    @NonNull
    @Override
    public ViewHolderCVEdu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_view_education,
                viewGroup, false);
        cvvieweducationAdapter.ViewHolderCVEdu viewHolder = new cvvieweducationAdapter.ViewHolderCVEdu(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCVEdu holder, int i) {
        final Academic job = edu.get(i);
        holder.vCourse.setText(job.getName());
        holder.vInstitute.setText(job.getInstituition());
        holder.vGrade.setText(job.getGrade());
        holder.vPassingYear.setText(String.valueOf(job.getPassingYear()));
    }

    @Override
    public int getItemCount() {
        return edu.size();
    }

   public class ViewHolderCVEdu extends RecyclerView.ViewHolder {
        public TextView vCourse;
        public TextView vInstitute;
        public TextView vGrade;
        public TextView vPassingYear;
        View mView;

        public ViewHolderCVEdu(final View itemView) {

            super(itemView);
            mView = itemView;
            vCourse = (TextView) itemView.findViewById(R.id.vCourse);
            vInstitute = (TextView) itemView.findViewById(R.id.vInstitute);
            vGrade = (TextView) itemView.findViewById(R.id.vGrade);
            vPassingYear = (TextView) itemView.findViewById(R.id.vPassingYear);
        }

    }
}