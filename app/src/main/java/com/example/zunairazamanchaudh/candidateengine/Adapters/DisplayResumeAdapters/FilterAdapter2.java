package com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.Adapters.FilterAdapter;
import com.example.zunairazamanchaudh.candidateengine.BR;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.Post;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.SkillsCV;
import com.example.zunairazamanchaudh.candidateengine.FilterJobs;
import com.example.zunairazamanchaudh.candidateengine.IMainActivity;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.UserProfileView2;
import com.example.zunairazamanchaudh.candidateengine.ViewJobDescActivity;
import com.example.zunairazamanchaudh.candidateengine.ViewJobDescriptionDetail;
import com.example.zunairazamanchaudh.candidateengine.databinding.FilterjobslistBinding;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter2 extends RecyclerView.Adapter<FilterAdapter2.BindingHolder4>{
    private static final String TAG = "FilterAdapter4";

    private List<Post> mProducts = new ArrayList<>();
   private Context mContext;

    public FilterAdapter2(Context context,List<Post> products) {
        mProducts = products;
       mContext = context;
    }

    public FilterAdapter2(List<Post> products) {
        mProducts = products;
      }

    @NonNull
    @Override
    public BindingHolder4 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filterjobslist2,
                viewGroup, false);
        FilterAdapter2.BindingHolder4 viewHolder = new FilterAdapter2.BindingHolder4(view);
        return viewHolder;

    }
String jid,cid;
    @Override
    public void onBindViewHolder(@NonNull final BindingHolder4 holder, int i) {
        final Post job =mProducts.get(i);
        holder.titleofjob2.setText(job.getJobtitle());
        holder.companytitle2.setText(job.getCompanyName());
        holder.loc2.setText(job.getCity()+", "+job.getCountry());
        holder.dat2.setText(job.getCreatedOn());
        holder.postedbbyid.setText(job.getCreator_id());
        holder.jobbid.setText(job.getJobpost_id());
        jid= holder.jobbid.getText().toString();
        cid=holder.postedbbyid.getText().toString();

        holder.viewjobdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ViewJobDescriptionDetail fragment = new ViewJobDescriptionDetail();
         //       Bundle bundle = new Bundle();
           //     bundle.putString("intent_jobid",holder.jobid.getText().toString());
             //   bundle.putString("intent_createrid",holder.postedbyid.getText().toString());
     Intent i=new Intent(mContext, ViewJobDescActivity.class);
     i.putExtra("z_jobid", holder.jobbid.getText().toString());
     i.putExtra("z_createrid",holder.postedbbyid.getText().toString());
     i.putExtra("applied_creator","");
           //     Toast.makeText(mContext,jid+"  "+cid,Toast.LENGTH_LONG).show();
     mContext.startActivity(i);
               // fragment.setArguments(bundle);
             //   FragmentTransaction transaction =  ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
               // transaction.replace(R.id.somefiltercontent, fragment, "viewjobs");
                //transaction.addToBackStack("viewjobs");
                //transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class BindingHolder4 extends RecyclerView.ViewHolder{
        public TextView titleofjob2;
        public TextView companytitle2;
        public TextView postedbbyid;
        public TextView jobbid;
        public TextView loc2;
        public TextView dat2;
        public RelativeLayout viewjobdetail;
        public ImageButton favorite_button2;
        View mView;

        public BindingHolder4(View itemView) {
            super(itemView);
            mView=itemView;
            titleofjob2=(TextView)itemView.findViewById(R.id.titleofjob2);
            companytitle2=(TextView)itemView.findViewById(R.id.companytitle2);
            loc2=(TextView)itemView.findViewById(R.id.loc2);
            dat2=(TextView)itemView.findViewById(R.id.dat2);
            postedbbyid=(TextView)itemView.findViewById(R.id.jobpostedbyid);
            jobbid=(TextView)itemView.findViewById(R.id.jobid);
            viewjobdetail=(RelativeLayout)itemView.findViewById(R.id.JobDetailrel1j);
            favorite_button2=(ImageButton) itemView.findViewById(R.id.favorite_button2);
               }
    }
}