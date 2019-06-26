package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.List;

public class detailjobposted2Adapter extends RecyclerView.Adapter<detailjobposted2Adapter.ViewHolderJobs> {
    Context context;
    List<JobPost> mainjobs;

    public detailjobposted2Adapter(Context context, List<JobPost> mainjobs) {
        this.context = context;
        this.mainjobs = mainjobs;
    }

    @NonNull
    @Override
    public ViewHolderJobs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detailpostedjobs2layout,
                viewGroup, false);

        ViewHolderJobs viewHolder = new ViewHolderJobs(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderJobs holder, int i) {

        JobPost job = mainjobs.get(i);

        holder.txtJobTitle.setText(job.getJobtitle());
        holder.txtcity.setText(job.getCity());
        holder.txtsalary.setText(String.valueOf(job.getSalary())+ " budget per month");
        holder.txtnoOfvacancies.setText(String.valueOf(job.getNoOfvacancie())+" no of vacancies");

    }

    @Override
    public int getItemCount() {
        return mainjobs.size();
    }

    class ViewHolderJobs extends RecyclerView.ViewHolder {

        public TextView txtJobTitle;
        public TextView txtsalary;
        public TextView txtnoOfvacancies;
        public  TextView txtcity;

        public ViewHolderJobs(View itemView) {

            super(itemView);

            txtcity= (TextView) itemView.findViewById(R.id.pj2City);
            txtJobTitle = (TextView) itemView.findViewById(R.id.pj2Title);
            txtsalary = (TextView) itemView.findViewById(R.id.pj2money);
            txtnoOfvacancies = (TextView) itemView.findViewById(R.id.pj2vacancy);

        }
    }
}
