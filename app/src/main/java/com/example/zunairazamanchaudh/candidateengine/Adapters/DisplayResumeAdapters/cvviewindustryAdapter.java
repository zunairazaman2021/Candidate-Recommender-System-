package com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Industry;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.SkillsCV;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.ResumeManager.ViewFullCV;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.List;

public class cvviewindustryAdapter extends RecyclerView.Adapter<cvviewindustryAdapter.ViewHolderCVIndustry> {
    ViewFullCV viewFullCV;
    List<Industry> edu;
Context context;
    public cvviewindustryAdapter( List<Industry> edu) {
      //  this.context = context
        this.edu = edu;
    }


    @NonNull
    @Override
    public cvviewindustryAdapter.ViewHolderCVIndustry onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_view_industry,
                viewGroup, false);
        cvviewindustryAdapter.ViewHolderCVIndustry viewHolder = new cvviewindustryAdapter.ViewHolderCVIndustry(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull cvviewindustryAdapter.ViewHolderCVIndustry holder, int i) {
        final Industry job = edu.get(i);
        holder.vIndustry.setText(job.getIndustry_name());
    }
    @Override
    public int getItemCount() {

        Log.d("DataZZ: "+edu.size(),"industry");

        return edu.size();
    }

public class ViewHolderCVIndustry extends RecyclerView.ViewHolder {
        public TextView vIndustry;
        View mView;

        public ViewHolderCVIndustry(final View itemView) {

            super(itemView);
            mView = itemView;
            vIndustry = (TextView) itemView.findViewById(R.id.vIndustry);
        }

    }

}
