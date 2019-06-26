package com.example.zunairazamanchaudh.candidateengine;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.databinding.FragmentViewRecommendJobDetailBinding;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;
import com.example.zunairazamanchaudh.candidateengine.model.JobViewModel;

import static com.example.zunairazamanchaudh.candidateengine.databinding.FragmentViewRecommendJobDetailBinding.inflate;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewRecommendJobDetail extends Fragment {
    private static final String TAG = "ViewRecommendJobDetail";
    // Data binding
    FragmentViewRecommendJobDetailBinding mBinding;
    //vars
    private JobView mProduct;
    TextView descText,jobdescription;
    ImageButton show, hide,show1,hide1;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mProduct = bundle.getParcelable(getString(R.string.intent_product));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentViewRecommendJobDetailBinding.inflate(inflater);
        JobViewModel viewProductModel = new JobViewModel();
        viewProductModel.setProduct(mProduct);
        viewProductModel.setQuantity(1);
        mBinding.setProductView(viewProductModel);
        descText = (TextView)mBinding.getRoot().findViewById(R.id.description_text);
        show = (ImageButton)mBinding.getRoot(). findViewById(R.id.show);
        jobdescription=(TextView)mBinding.getRoot().findViewById(R.id.textdescrip);
        show1 = (ImageButton)mBinding.getRoot(). findViewById(R.id.show2);
        show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show1.setVisibility(View.INVISIBLE);
                hide1.setVisibility(View.VISIBLE);
                jobdescription.setMaxLines(Integer.MAX_VALUE);

            }
        });
        hide1= (ImageButton)mBinding.getRoot(). findViewById(R.id.hide2);
        hide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide1.setVisibility(View.INVISIBLE);
                show1.setVisibility(View.VISIBLE);
                jobdescription.setMaxLines(5);

            }
        });
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);

            }
        });
        hide = (ImageButton)mBinding.getRoot(). findViewById(R.id.hide);
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);

            }
        });

        return mBinding.getRoot();
    }
}
