package com.example.zunairazamanchaudh.candidateengine.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.BR;
import com.example.zunairazamanchaudh.candidateengine.IMainActivity;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.databinding.ViewlistjobsBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends  RecyclerView.Adapter<RecommendAdapter.BindingHolder> {



    private static final String TAG = "RecommendAdapter";

    private List<JobView> mProducts = new ArrayList<>();
    private Context mContext;

    public RecommendAdapter(Context context, List<JobView> products) {
        mProducts = products;
        mContext = context;
    }

    public void refreshList(List<JobView> products){
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewDataBinding binding = DataBindingUtil.inflate(
//                LayoutInflater.from(mContext), R.layout.product_item, parent, false);
        ViewlistjobsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.viewlistjobs, parent, false);

        return new BindingHolder(binding.getRoot());
    }



    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {
        JobView product = mProducts.get(position);
     //   holder.binding.setProduct(product);
       holder.binding.setIMainActivity((IMainActivity)mContext);
        holder.binding.setVariable(BR.product, product);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder{

        //        ViewDataBinding binding;
        ViewlistjobsBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
