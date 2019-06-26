package com.example.zunairazamanchaudh.candidateengine.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.example.zunairazamanchaudh.candidateengine.R;

public class GlideBindingAdapter {
    private static final String TAG = "GlideBindingAdapter";


    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, String imageUrl){

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, int imageUrl){

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);
    }


    @BindingAdapter({"requestListener", "imageResource"})
    public static void bindRequestListener(ImageView view, RequestListener listener, int imageResource){
        Log.d(TAG, "bindRequestListener: setting image resource.");

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageResource)
                .listener(listener)
                .into(view);
    }
}
