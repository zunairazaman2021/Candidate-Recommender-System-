package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter;

import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.Date;
import java.util.HashMap;

public class JobPostedData {

    public JobPostedview[] jpv= {YU, YY,YB,YT};

    public HashMap<String, JobPostedview> JOB_MAP = new HashMap<>();

    public JobPostedData()  {
        for(JobPostedview jb : jpv){
            JOB_MAP.put(String.valueOf(jb.getSerialno()),jb);
        }
    }
    public static final JobPostedview YU=new JobPostedview(1,1508, "Android Developer","This requires android developer having max 2 year experience and project encompass social benefits",
            "Android development", "middle",2, "", "Pakistan", "","Islamabad", "BSCS","MS in Computer Science", 2,
            30000,
            "None", "Hiring process will prefer candidates having strong skills at android development");

    public static final JobPostedview YY=new JobPostedview(2,1508, "Kotlin Developer","This requires android developer having max 2 year experience and project encompass social benefits",
            "Android development", "middle",2, "", "Pakistan", "","Islamabad", "BSCS","MS in Computer Science", 2,
            30000,
            "None", "Hiring process will prefer candidates having strong skills at android development");
    public static final JobPostedview YT=new JobPostedview(3,1508, "Kotlin Developer","This requires android developer having max 2 year experience and project encompass social benefits",
            "Android development", "middle",2, "", "Pakistan", "","Islamabad", "BSCS","MS in Computer Science", 2,
            30000,
            "None", "Hiring process will prefer candidates having strong skills at android development");
    public static final JobPostedview YB=new JobPostedview(4,1508, "Kotlin Developer","This requires android developer having max 2 year experience and project encompass social benefits",
            "Android development", "middle",2, "", "Pakistan", "","Islamabad", "BSCS","MS in Computer Science", 2,
            30000,
            "None", "Hiring process will prefer candidates having strong skills at android development");

}
