package com.example.zunairazamanchaudh.candidateengine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewJobDescriptionDetail extends Fragment {
    String jobid,creatorid;
    TextView descText,jobdescription;
    ImageButton show, hide,show1,hide1;
    TextView titletext2,companytxt2,experience2,vacancytxt2,contrytxt2,mm2,nationaltxt2,edutxt2;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            jobid= bundle.getString("intent_jobid");
            creatorid=bundle.getString("intent_createrid");
        }
    }

    public ViewJobDescriptionDetail() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_view_job_description_detail, container, false);
        titletext2=(TextView)view.findViewById(R.id.titletext2);
        companytxt2=(TextView)view.findViewById(R.id.companytxt2);
        experience2 =(TextView)view.findViewById(R.id.experience2);
        vacancytxt2=(TextView)view.findViewById(R.id.vacancytxt2);
        contrytxt2=(TextView)view.findViewById(R.id.contrytxt2);
        mm2=(TextView)view.findViewById(R.id.mm2);
        nationaltxt2=(TextView)view.findViewById(R.id.nationaltxt2);
        edutxt2  =(TextView)view.findViewById(R.id.edutxt2);
        descText = (TextView)view.findViewById(R.id.description_text2);
        show = (ImageButton)view. findViewById(R.id.showv);
        jobdescription=(TextView)view.findViewById(R.id.textdescripv);
        show1 = (ImageButton)view. findViewById(R.id.show2v);
        show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show1.setVisibility(View.INVISIBLE);
                hide1.setVisibility(View.VISIBLE);
                jobdescription.setMaxLines(Integer.MAX_VALUE);

            }
        });
        hide1= (ImageButton)view. findViewById(R.id.hide2v);
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
        hide = (ImageButton)view. findViewById(R.id.hidev);
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);
            }
        });
        return view;
    }

}
