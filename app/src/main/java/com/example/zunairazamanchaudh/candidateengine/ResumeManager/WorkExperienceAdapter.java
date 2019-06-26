package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.ArrayList;

public class WorkExperienceAdapter extends BaseAdapter {
    private ArrayList<WorkExperience> dataSet;
    Context mContext;

    public WorkExperienceAdapter(ArrayList<WorkExperience> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public WorkExperience getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        WorkExperienceAdapter.ViewHolderExperience viewHolder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.showlist_work_experience_items,null);
            viewHolder=new WorkExperienceAdapter.ViewHolderExperience();
            viewHolder.txtExperience=(TextView)convertView.findViewById(R.id.txtOrgTitle);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(WorkExperienceAdapter.ViewHolderExperience)convertView.getTag();
        }
        WorkExperience workExperienceitem=getItem(position);

        viewHolder.txtExperience.setText(workExperienceitem.getOrganization());

        return convertView;
    }

    public class ViewHolderExperience{
        public TextView txtExperience;
    }


    }

