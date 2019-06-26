package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.BR;
import com.example.zunairazamanchaudh.candidateengine.IMainActivity;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.IMainActivityRecruiter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVs;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.databinding.FiltercvdetailBinding;
import com.example.zunairazamanchaudh.candidateengine.databinding.FilterjobslistBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.ArrayList;
import java.util.List;

public class CVsFilterationAdapter extends RecyclerView.Adapter<CVsFilterationAdapter.BindingHolderCV>{
    private static final String TAG = "CVsFilterationAdapter";

    private List<CVsview> mProducts = new ArrayList<>();
    private Context mContext;

    public CVsFilterationAdapter(Context mContext, List<CVsview> mProducts) {
        this.mProducts = mProducts;
        this.mContext = mContext;
    }
    public void refreshList(List<CVsview> products){
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BindingHolderCV onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
FiltercvdetailBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.filtercvdetail, viewGroup, false);

        return new CVsFilterationAdapter.BindingHolderCV(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(CVsFilterationAdapter.BindingHolderCV bindingHolderCV, int i) {
        CVsview product = mProducts.get(i);
           bindingHolderCV.binding.setCv(product);
        bindingHolderCV.binding.setIMainActivityRecruiter((IMainActivityRecruiter) mContext);
        bindingHolderCV.binding.setVariable(BR.cvs, product);
        bindingHolderCV.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class BindingHolderCV extends RecyclerView.ViewHolder{

        //        ViewDataBinding binding;
     //   FilterjobslistBinding binding;
FiltercvdetailBinding binding;
        public BindingHolderCV(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
