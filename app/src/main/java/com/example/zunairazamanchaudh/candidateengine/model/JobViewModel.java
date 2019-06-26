package com.example.zunairazamanchaudh.candidateengine.model;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.zunairazamanchaudh.candidateengine.BR;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVs;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;


public class JobViewModel extends BaseObservable {
    private JobView product;
    private int quantity;
    private boolean imageVisibility = false;
    private int noOfvacancies;
    public void setNoOfvacancies(int noOfvacancies) {
        this.noOfvacancies = noOfvacancies;
        notifyPropertyChanged(noOfvacancies);
    }

    public int getNoOfvacancies() {

        return noOfvacancies;
    }

    @Bindable
    public int getQuantity() {
        return quantity;
    }

    @Bindable
    public JobView getProduct() {
        return product;
    }

    @Bindable
    public boolean getImageVisibility() {
        return imageVisibility;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }

    public void setProduct(JobView product) {
        this.product = product;
        notifyPropertyChanged(BR.product);
    }

    public void setImageVisible(boolean imageVisible) {
        imageVisibility = imageVisible;
        notifyPropertyChanged(BR.imageVisibility);
    }

    public RequestListener getCustomRequestListener(){
        return new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                setImageVisible(true);
                return false;
            }
        };
    }

}
