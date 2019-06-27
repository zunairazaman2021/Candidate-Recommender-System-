package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.UserProfileView2;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.UserProfileView;
import com.example.zunairazamanchaudh.candidateengine.ResumeManager.AcademicAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchCV2Adapter extends RecyclerView.Adapter<SearchCV2Adapter.ViewHolderCV2>{
    Context context;
    List<CVTitle> maincvs;
    List<users> users;
    public String usernextid ;
    public SearchCV2Adapter(Context context, List<CVTitle> maincvs) {
        this.context = context;
        this.maincvs = maincvs;
    }

    @NonNull
    @Override
    public ViewHolderCV2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filtercv2detail,
                viewGroup, false);
        SearchCV2Adapter.ViewHolderCV2 viewHolder = new SearchCV2Adapter.ViewHolderCV2(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCV2 holder, int i) {
        CVTitle job = maincvs.get(i);
        holder.ownerName2.setText(job.getFirstname()+" "+job.getLastname());
        holder.designation2.setText(job.getCv_title());
        holder.citycountry.setText(job.getCity()+", "+job.getCountry());
        holder.expfrom.setText(String.valueOf(job.getCreatedon()));
        usernextid=job.getCv_id();
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),UserProfileView.class);
                i.putExtra("intent_cvid",usernextid);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return maincvs.size();
    }

    class ViewHolderCV2 extends RecyclerView.ViewHolder {
        public CircleImageView image2owner;
        public TextView ownerName2;
        public TextView designation2;
        public TextView citycountry;
        public TextView expfrom;
        View mView;
        public ViewHolderCV2(final View itemView) {

            super(itemView);
            mView = itemView;
            image2owner = (CircleImageView) itemView.findViewById(R.id.image2Owner);
            ownerName2 = (TextView) itemView.findViewById(R.id.ownerName2);
            designation2 = (TextView) itemView.findViewById(R.id.ownerDesignation2);
            citycountry = (TextView) itemView.findViewById(R.id.ownerCity2);
            expfrom = (TextView) itemView.findViewById(R.id.ownerExperienceFrom2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   mclickListener.onItemClick(v,getAdapterPosition());
                Intent i=new Intent(v.getContext(),UserProfileView.class);
                i.putExtra("intent_cvid",usernextid);

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    mclickListener.onItemLongClick(v,getAdapterPosition());
                    return false;
                }
            });

        }


    }


}