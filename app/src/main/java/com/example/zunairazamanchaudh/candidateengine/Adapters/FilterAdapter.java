package com.example.zunairazamanchaudh.candidateengine.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.BR;
import com.example.zunairazamanchaudh.candidateengine.IMainActivity;
import com.example.zunairazamanchaudh.candidateengine.R;

import com.example.zunairazamanchaudh.candidateengine.databinding.FilterjobslistBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.BindingHolder2> {
    private static final String TAG = "FilterAdapter";

    private List<JobView> mProducts = new ArrayList<>();
    private Context mContext;

    public FilterAdapter(Context context, List<JobView> products) {
        mProducts = products;
        mContext = context;
    }

    public void refreshList(List<JobView> products){
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public FilterAdapter.BindingHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewDataBinding binding = DataBindingUtil.inflate(
//                LayoutInflater.from(mContext), R.layout.product_item, parent, false);
        FilterjobslistBinding   binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.filterjobslist, parent, false);

        return new FilterAdapter.BindingHolder2(binding.getRoot());
    }



    @Override
    public void onBindViewHolder(FilterAdapter.BindingHolder2 holder, final int position) {
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

    public class BindingHolder2 extends RecyclerView.ViewHolder{

        //        ViewDataBinding binding;
        FilterjobslistBinding binding;

        public BindingHolder2(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
