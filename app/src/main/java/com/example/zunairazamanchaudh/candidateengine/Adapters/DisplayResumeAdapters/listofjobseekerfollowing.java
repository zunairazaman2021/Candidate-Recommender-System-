package com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.Adapters.listoffollowersAdapter;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterFollowers;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterUser;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.UserProfileView2;
import com.example.zunairazamanchaudh.candidateengine.RecruiterProfileUI;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class listofjobseekerfollowing extends RecyclerView.Adapter<listofjobseekerfollowing.ViewHolderFollowerJ>{
    Context context;
    List<RecruiterFollowers> mainjobs;

    public listofjobseekerfollowing(Context context, List<RecruiterFollowers> mainjobs) {
        this.context = context;
        this.mainjobs = mainjobs;
    }

    @NonNull
    @Override
    public listofjobseekerfollowing.ViewHolderFollowerJ onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jobseekerfollowing,
                viewGroup, false);

        listofjobseekerfollowing.ViewHolderFollowerJ viewHolder = new listofjobseekerfollowing.ViewHolderFollowerJ(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final listofjobseekerfollowing.ViewHolderFollowerJ holder, int i) {

        RecruiterFollowers job = mainjobs.get(i);

        holder.name.setText(job.getRfirstname()+" "+job.getRlastname());
        holder.loc.setText(job.getRphone());
        holder.email.setText(job.getRemail());
        holder.recruiterid.setText(job.getRecruiterid());
        holder.relativeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(v.getContext(),RecruiterProfileUI.class);
                i.putExtra("intent_vr",holder.recruiterid.getText().toString());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainjobs.size();
    }

    class ViewHolderFollowerJ extends RecyclerView.ViewHolder {
        public CircleImageView image;
        public TextView name;
        public TextView loc;
        public TextView email;
        public TextView recruiterid;
        public RelativeLayout relativeClick;

        public ViewHolderFollowerJ(View itemView) {

            super(itemView);
            image=(CircleImageView)itemView.findViewById(R.id.image);
            name= (TextView) itemView.findViewById(R.id.name);
            loc = (TextView) itemView.findViewById(R.id.loc);
            email = (TextView) itemView.findViewById(R.id.email);
            recruiterid=(TextView)itemView.findViewById(R.id.recruiterid);
            relativeClick=(RelativeLayout)itemView.findViewById(R.id.viewfollowing);
        }
    }

}
