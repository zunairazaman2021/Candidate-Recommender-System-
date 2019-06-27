package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.ProjectDetails;
import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.ArrayList;

public class ProjectsAdapter extends BaseAdapter{
    private ArrayList<ProjectDetails> dataSet;
    Context mContext;

    public ProjectsAdapter(ArrayList<ProjectDetails> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public ProjectDetails getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProjectsAdapter.ViewHolderProjects viewHolder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.showlist_project_items,null);
            viewHolder=new ProjectsAdapter.ViewHolderProjects();
            viewHolder.txtprojectTitle=(TextView)convertView.findViewById(R.id.txtProjectName);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ProjectsAdapter.ViewHolderProjects)convertView.getTag();
        }
        ProjectDetails projectsitem=getItem(position);

        viewHolder.txtprojectTitle.setText(projectsitem.getTitle());

        return convertView;

    }

    public class ViewHolderProjects{
        public TextView txtprojectTitle;
    }

}
