package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.CandidatesApplicationManagement;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.UserProfileView2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CVFolderAdapter2  extends RecyclerView.Adapter<CVFolderAdapter2.ViewHolderCVFold>{
    Context context;
    List<JobPost> maincvs;
    public CVFolderAdapter2(Context context, List<JobPost> maincvs) {
        this.context = context;
        this.maincvs = maincvs;
    }

    @NonNull
    @Override
    public ViewHolderCVFold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.showcvfolders2,
                viewGroup, false);
        CVFolderAdapter2.ViewHolderCVFold viewHolder = new CVFolderAdapter2.ViewHolderCVFold(view);
        return viewHolder;
    }
String id;
    @Override
    public void onBindViewHolder(@NonNull final ViewHolderCVFold holder, int i) {
        final JobPost job = maincvs.get(i);
        holder.cvfoldername2.setText(job.getJobtitle());
        holder.noofcv.setText("Posted Date: "+job.getCreatedOn());
        holder.cvfolderid.setText(job.getJobpost_id());
        id=holder.cvfolderid.getText().toString();
         holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),CandidatesApplicationManagement.class);
                i.putExtra("app_jobid",holder.cvfolderid.getText().toString());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return maincvs.size();
    }


    class ViewHolderCVFold extends RecyclerView.ViewHolder {
    //    public CircleImageView image2owner;
        public TextView cvfoldername2; //jobname
        public TextView noofcv;
        public TextView cvfolderid;   //jobid
        public RelativeLayout relativeLayout;
        View mView;
        public ViewHolderCVFold(final View itemView) {

            super(itemView);
            mView = itemView;
      //      image2owner = (CircleImageView) itemView.findViewById(R.id.image2Owner);
            cvfoldername2 = (TextView) itemView.findViewById(R.id.cvfolderTitle2);
            noofcv = (TextView) itemView.findViewById(R.id.cvfolderNumberofCVs2);
            cvfolderid = (TextView) itemView.findViewById(R.id.jobid);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.relivefolder);
        }
    }

}
