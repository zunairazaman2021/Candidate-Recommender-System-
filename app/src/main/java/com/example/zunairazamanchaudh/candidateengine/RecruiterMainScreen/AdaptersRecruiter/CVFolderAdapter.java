package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zunairazamanchaudh.candidateengine.BR;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.IMainActivityRecruiter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.databinding.FiltercvdetailBinding;
import com.example.zunairazamanchaudh.candidateengine.databinding.ShowcvfoldersBinding;

import java.util.ArrayList;
import java.util.List;

public class CVFolderAdapter extends RecyclerView.Adapter<CVFolderAdapter.BindingHolderCVFolder>{
    private static final String TAG = "CVFolderAdapter";

    private List<CVsview> mProducts = new ArrayList<>();
    private Context mContext;
    public CVFolderAdapter(Context mContext, List<CVsview> mProducts) {
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
    public BindingHolderCVFolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ShowcvfoldersBinding binding=DataBindingUtil.inflate( LayoutInflater.from(mContext), R.layout.showcvfolders, viewGroup, false);
        return new CVFolderAdapter.BindingHolderCVFolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolderCVFolder bindingHolderCVFolder, int i) {
        CVsview product = mProducts.get(i);
        bindingHolderCVFolder.binding.setCv(product);
        bindingHolderCVFolder.binding.setIMainActivityRecruiter((IMainActivityRecruiter) mContext);
        bindingHolderCVFolder.binding.setVariable(BR.cvs, product);
        bindingHolderCVFolder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class BindingHolderCVFolder extends RecyclerView.ViewHolder{

        //        ViewDataBinding binding;
        //   FilterjobslistBinding binding;
        ShowcvfoldersBinding binding;
        public BindingHolderCVFolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
