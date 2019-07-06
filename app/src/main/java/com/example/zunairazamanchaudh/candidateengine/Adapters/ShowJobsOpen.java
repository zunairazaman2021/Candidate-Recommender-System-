package com.example.zunairazamanchaudh.candidateengine.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.Post;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.ViewJobDescActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ShowJobsOpen extends RecyclerView.Adapter<ShowJobsOpen.ViewHolder>{

        private List<JobApplication> j2=new ArrayList<>();
        public Context mContext;
        public ShowJobsOpen( List<JobApplication> j2, Context mContext){
            this.j2=j2;
            this.mContext=mContext;
        }

        @NonNull
        @Override
        public ShowJobsOpen.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

           LayoutInflater minflater=LayoutInflater.from(mContext);
            View view=minflater.inflate(R.layout.jobapply,viewGroup,false);
            return new ShowJobsOpen.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
            final JobApplication application=j2.get(i);
            viewHolder.txt1jobname.setText(application.getYourName());
            viewHolder.txt2place.setText(application.getJcity()+", "+application.getJcountry());
            viewHolder.txt3date.setText("Date applied : "+application.getApplicationDate());
            viewHolder.txt5status.setText("Status : "+application.getApplicationstatus());
            viewHolder.txt4rank.setText(application.getJob_id());
            viewHolder.txtcreator.setText(application.getRecruiter_id());
            viewHolder.txtbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(), ViewJobDescActivity.class);
                    i.putExtra("z_jobid",viewHolder.txt4rank.getText().toString());
                    i.putExtra("z_createrid",viewHolder.txtcreator.getText().toString());
                    mContext.startActivity(i);
                }
            });
        }


        @Override
        public int getItemCount() {
            return j2.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txt1jobname;
            public TextView txt2place;
            public TextView txt3date;
            public TextView txt5status;
            public TextView txt4rank,txtcreator;
            public TextView txtbtn;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txt1jobname = (TextView)itemView.findViewById(R.id.txt1);
                txt2place = (TextView)itemView.findViewById(R.id.txt2);
                txt3date=(TextView)itemView.findViewById(R.id.txt3);
                txt5status=(TextView)itemView.findViewById(R.id.txt5);
                txt4rank=(TextView)itemView.findViewById(R.id.txt4);
                txtcreator=(TextView)itemView.findViewById(R.id.creatorid);
                txtbtn=(TextView)itemView.findViewById(R.id.moredetailbtn);
            }
        }
    }



