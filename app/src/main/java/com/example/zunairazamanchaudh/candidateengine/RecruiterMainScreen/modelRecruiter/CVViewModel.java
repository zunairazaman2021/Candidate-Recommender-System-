package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingConversion;
import android.databinding.ObservableField;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.zunairazamanchaudh.candidateengine.BR;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;

public class CVViewModel extends BaseObservable {
    private CVsview cv;
    private int quantity;
    private boolean imageVisibility = false;
    public final ObservableField<Date> updatedDate = new ObservableField<>();



    @Bindable
    public boolean getImageVisibility() {
        return imageVisibility;
    }

    public void setImageVisible(boolean imageVisible) {
        imageVisibility = imageVisible;
        notifyPropertyChanged(BR.imageVisibility);
    }
    @BindingConversion
    public static String convertDateToDisplayedText(Date date) {
        return new SimpleDateFormat("yyyy:MM:dd").format(date);
    }
    @Bindable
    public CVsview getCv(){
        return cv;
    }

    public void setCv(CVsview cv) {
        this.cv = cv;
        notifyPropertyChanged(BR.cv);
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