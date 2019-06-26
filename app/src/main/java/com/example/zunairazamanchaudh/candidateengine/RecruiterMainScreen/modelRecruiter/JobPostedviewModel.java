package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.zunairazamanchaudh.candidateengine.BR;
import com.example.zunairazamanchaudh.candidateengine.R;

public class JobPostedviewModel extends BaseObservable {
    private JobPostedview jb;
    private int quantity;



    public JobPostedview getJb() {
        return jb;
    }

    public void setJb(JobPostedview jb) {
        this.jb = jb;
        notifyPropertyChanged(BR.jobpost);
     }

    public RequestListener getCustomRequestListener(){
        return new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
//                setImageVisible(true);
                return false;
            }
        };
    }
}
