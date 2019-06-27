package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.ArrayList;

public class AcademicAdapter extends BaseAdapter {

    private ArrayList<Academic> dataSet;
    Context mContext;

    protected AcademicAdapter(Context context, ArrayList<Academic> arrayList) {
        this.dataSet=arrayList;
       this.mContext=context;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Academic getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.showlistitems,null);
            viewHolder=new ViewHolder();
            viewHolder.txtcourse=(TextView)convertView.findViewById(R.id.txtTitleDegree);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        Academic coursesitem=getItem(position);

        viewHolder.txtcourse.setText(coursesitem.getName());

        return convertView;
    }

    public class ViewHolder{
        public TextView txtcourse;
    }
}