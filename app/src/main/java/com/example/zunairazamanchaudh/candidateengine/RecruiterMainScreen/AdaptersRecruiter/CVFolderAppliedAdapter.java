package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.CandidatesApplicationManagement;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.AppliedCandidateProfileView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CVFolderAppliedAdapter extends RecyclerView.Adapter<CVFolderAppliedAdapter.ViewHolderApplied>{

    Context context;
    List<JobApplication> maincvs;
    public CVFolderAppliedAdapter(Context context, List<JobApplication> maincvs) {
        this.context = context;
        this.maincvs = maincvs;
    }

    @NonNull
    @Override
    public CVFolderAppliedAdapter.ViewHolderApplied onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filter_candidates_applied,
                viewGroup, false);
        CVFolderAppliedAdapter.ViewHolderApplied viewHolder = new CVFolderAppliedAdapter.ViewHolderApplied(view);
        return viewHolder;
    }
    String id;
    @Override
    public void onBindViewHolder(@NonNull final CVFolderAppliedAdapter.ViewHolderApplied holder, int i) {
        final JobApplication job = maincvs.get(i);
        holder.ownerName2.setText(job.getFirstname()+" "+job.getLastname());
        holder.ownercity2.setText(job.getCity()+", "+job.getCountry());
        holder.ownerExpfrom2.setText("Applied on : "+job.getApplicationDate());
        holder.ownerDesignation2.setText("Application Status: "+job.getApplicationstatus());
        holder.owneruserid.setText(job.getCv_id());
        holder.appid.setText(job.getApplication_id());
        holder.jobid.setText(job.getJob_id());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i=new Intent(v.getContext(),AppliedCandidateProfileView.class);
                i.putExtra("applied_applicationid",holder.appid.getText().toString());
                i.putExtra("applied_cvid",holder.owneruserid.getText().toString());
                i.putExtra("applied_jobid",holder.jobid.getText().toString());
                i.putExtra("applied_creator", FirebaseAuth.getInstance().getCurrentUser().getUid());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return maincvs.size();
    }

    class ViewHolderApplied extends RecyclerView.ViewHolder {
         public CircleImageView image2owner;
        public TextView ownerName2; //jobname
        public TextView ownerDesignation2;
        public TextView ownercity2;   //jobid
        public TextView ownerExpfrom2;
        public TextView owneruserid;
        public TextView jobid;
        public TextView appid;
        public RelativeLayout relativeLayout;
        View mView;
        public ViewHolderApplied(final View itemView) {

            super(itemView);
            mView = itemView;
            image2owner = (CircleImageView) itemView.findViewById(R.id.image2Owner);
            ownercity2 = (TextView) itemView.findViewById(R.id.ownerCity2);
            ownerName2= (TextView) itemView.findViewById(R.id.ownerName2);
            ownerDesignation2= (TextView) itemView.findViewById(R.id.ownerDesignation2);
            ownerExpfrom2= (TextView) itemView.findViewById(R.id.ownerExperienceFrom2);
            owneruserid= (TextView) itemView.findViewById(R.id.userid);
            jobid=(TextView)itemView.findViewById(R.id.jobid);
            appid=(TextView)itemView.findViewById(R.id.applicationid);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.reltiveClick);
        }
    }
}