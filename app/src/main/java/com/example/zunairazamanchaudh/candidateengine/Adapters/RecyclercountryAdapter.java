package com.example.zunairazamanchaudh.candidateengine.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclercountryAdapter extends RecyclerView.Adapter<RecyclercountryAdapter.ViewHolder>{
    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<Integer> mimageurls=new ArrayList<>();
    public Context mContext;
    public RecyclercountryAdapter(ArrayList<String> mNames, ArrayList<Integer> mimageurls, Context mContext){
        this.mNames=mNames;
        this.mimageurls=mimageurls;
        this.mContext=mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater minflater=LayoutInflater.from(mContext);
        View view=minflater.inflate(R.layout.list_countryitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(mContext)
                .asBitmap()
                .load(mimageurls.get(i))
                .into(viewHolder.image);
        viewHolder.namename.setText(mNames.get(i));
    }


    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView image;
        public TextView namename;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            namename = (TextView)itemView.findViewById(R.id.nav_age);
        }
    }
}

