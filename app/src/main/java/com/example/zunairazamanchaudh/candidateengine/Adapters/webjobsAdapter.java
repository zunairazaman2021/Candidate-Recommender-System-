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

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.WebJobs;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.ViewJobDescActivity;

import java.util.ArrayList;
import java.util.List;

public class webjobsAdapter extends RecyclerView.Adapter<webjobsAdapter.ViewHolderWeb>{

    private List<WebJobs> j2=new ArrayList<>();
    public Context mContext;
    public webjobsAdapter( List<WebJobs> j2, Context mContext){
        this.j2=j2;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public webjobsAdapter.ViewHolderWeb onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater minflater=LayoutInflater.from(mContext);
        View view=minflater.inflate(R.layout.filter_webjobs_desc,viewGroup,false);
        return new webjobsAdapter.ViewHolderWeb(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final webjobsAdapter.ViewHolderWeb viewHolder, int i) {
        final WebJobs web=j2.get(i);
        viewHolder.webTitle.setText(web.getJob_name());
        viewHolder.webtime.setText(web.getPosted_on_date());
        viewHolder.weburl.setText(web.getJob_url());
        viewHolder.webDesc.setText(web.getJob_descriptions());
        viewHolder.weblocation.setText(web.getJob_location());
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

    public class ViewHolderWeb extends RecyclerView.ViewHolder {

        public TextView webTitle;
        public TextView webDesc;
        public TextView weburl;
        public TextView webtime;
        public TextView weblocation;
        public Button webgotourl;

        public ViewHolderWeb(@NonNull View itemView) {
            super(itemView);
           webTitle = (TextView)itemView.findViewById(R.id.webtitle);
            weblocation= (TextView)itemView.findViewById(R.id.weblocation);
            webDesc=(TextView)itemView.findViewById(R.id.webdescription);
            weburl=(TextView)itemView.findViewById(R.id.webURL);
            webtime=(TextView)itemView.findViewById(R.id.webtime);
            webgotourl=(Button) itemView.findViewById(R.id.webgotoURL);
        }
    }

}
