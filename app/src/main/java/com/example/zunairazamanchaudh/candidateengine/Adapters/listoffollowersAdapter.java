package com.example.zunairazamanchaudh.candidateengine.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterFollowers;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.detailjobposted2Adapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.UserProfileView2;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class listoffollowersAdapter extends RecyclerView.Adapter<listoffollowersAdapter.ViewHolderFollowers>{
    Context context;
    List<RecruiterFollowers> mainjobs;

    public listoffollowersAdapter(Context context, List<RecruiterFollowers> mainjobs) {
        this.context = context;
        this.mainjobs = mainjobs;
    }

    @NonNull
    @Override
    public listoffollowersAdapter.ViewHolderFollowers onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_of_followers,
                viewGroup, false);

        listoffollowersAdapter.ViewHolderFollowers viewHolder = new listoffollowersAdapter.ViewHolderFollowers(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final listoffollowersAdapter.ViewHolderFollowers holder, int i) {

        RecruiterFollowers job = mainjobs.get(i);

        holder.name.setText(job.getFirstname()+" " +job.getLastname());
        holder.location.setText(job.getCity()+", "+job.getCountry());
        holder.followeddate.setText(job.getFolloweddate());
        holder.userid.setText(job.getUser_id());
        holder.relativeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),UserProfileView2.class);
                i.putExtra("intent_cvid",holder.userid.getText().toString());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainjobs.size();
    }

    class ViewHolderFollowers extends RecyclerView.ViewHolder {
        public CircleImageView imageViewowner;
        public TextView name;
        public TextView location;
        public TextView followeddate;
        public TextView userid;
        public RelativeLayout relativeClick;

        public ViewHolderFollowers(View itemView) {

            super(itemView);
            imageViewowner=(CircleImageView)itemView.findViewById(R.id.image2Owner);
            name= (TextView) itemView.findViewById(R.id.ownerName2);
            location = (TextView) itemView.findViewById(R.id.ownerCity2);
            followeddate = (TextView) itemView.findViewById(R.id.ownerExperienceFrom2);
            userid=(TextView)itemView.findViewById(R.id.userid);
            relativeClick=(RelativeLayout)itemView.findViewById(R.id.reltiveClick);
        }
    }

}
