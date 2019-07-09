package com.example.zunairazamanchaudh.candidateengine.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.WebJobs;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.weightjobs;
import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.ArrayList;
import java.util.List;

public class weightedfiltrationjob extends RecyclerView.Adapter<weightedfiltrationjob.ViewHolderWebW>{
    private List<weightjobs> j2=new ArrayList<>();
    public Context mContext;
    public weightedfiltrationjob( List<weightjobs> j2, Context mContext){
        this.j2=j2;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public weightedfiltrationjob.ViewHolderWebW onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater minflater=LayoutInflater.from(mContext);
        View view=minflater.inflate(R.layout.weightedfiltration,viewGroup,false);
        return new weightedfiltrationjob.ViewHolderWebW(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final weightedfiltrationjob.ViewHolderWebW viewHolder, int i) {
        final weightjobs w=j2.get(i);
        viewHolder.webTitle.setText(w.getJob_name());
        viewHolder.webtime.setText(w.getPosted_on_date());
        viewHolder.weburl.setText(w.getJob_url());
        viewHolder.webDesc.setText(w.getJob_descriptions());
        viewHolder.weblocation.setText(w.getJob_location());
        viewHolder.webweight.setText("Matching Maturity: "+w.getWeight());
        viewHolder.webgotourl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(viewHolder.weburl.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return j2.size();
    }

    public class ViewHolderWebW extends RecyclerView.ViewHolder {

        public TextView webTitle;
        public TextView webDesc;
        public TextView weburl;
        public TextView webtime;
        public TextView weblocation;
        public Button webgotourl;
        public TextView webweight;
        public ViewHolderWebW(@NonNull View itemView) {
            super(itemView);
            webTitle = (TextView)itemView.findViewById(R.id.webtitle);
            weblocation= (TextView)itemView.findViewById(R.id.weblocation);
            webDesc=(TextView)itemView.findViewById(R.id.webdescription);
            weburl=(TextView)itemView.findViewById(R.id.webURL);
            webtime=(TextView)itemView.findViewById(R.id.webtime);
            webgotourl=(Button) itemView.findViewById(R.id.webgotoURL);
            webweight=(TextView) itemView.findViewById(R.id.webweight);
        }
    }
}
