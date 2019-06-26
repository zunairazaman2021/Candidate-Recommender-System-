package com.example.zunairazamanchaudh.candidateengine.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.ArrayList;

public class ShowJobsOpen extends RecyclerView.Adapter<ShowJobsOpen.ViewHolder>{

       private ArrayList<String> j1=new ArrayList<>();
        private ArrayList<String> j2=new ArrayList<>();
        public Context mContext;
        public ShowJobsOpen(ArrayList<String> j1, ArrayList<String> j2, Context mContext){
            this.j1=j1;
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
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.t1.setText(j1.get(i));
            viewHolder.t2.setText(j2.get(i));
        }


        @Override
        public int getItemCount() {
            return j1.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView t1;
            public TextView t2;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                t1 = (TextView)itemView.findViewById(R.id.txt1);
                t2 = (TextView)itemView.findViewById(R.id.txt2);
            }
        }

    }



