package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter;

import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class CVs {
    public CVsview[] CVS= {RED_LAMP, YELLOW_LAMP, BLUE_MUG, WHITE_MUG, RED_MUG, BLACK_HAT, BLUE_HAT,YY};

    public HashMap<String, CVsview> CV_MAP = new HashMap<>();

    public CVs()  {
        for(CVsview cv : CVS){
            CV_MAP.put(String.valueOf(cv.getSerialnumber()),cv);
        }
    }
    public static final CVsview YY=new CVsview(1,"UX designer","zunaira","Pakistan","Islamabad", "pi@gmail.com","121323213", "Married", "Pakistani",  new java.util.Date(System.currentTimeMillis()), "BSSE","UOG", "A+", "2019", "Netsol", "Software Engineer", "IT Developer", new java.util.Date(System.currentTimeMillis()), new java.util.Date(System.currentTimeMillis()),"CRS", "Recommender system","group work", "1 year", 3, "Android"," development", "netsol","Best actress", "traveling", "Ms. abc",
    "Lecturer","UoG", "missabc@gmail.com", "+923823230", R.drawable.outline_account48dp);
    public static final CVsview RED_LAMP = new CVsview(2,"Android developer","zoya","Pakistan","Islamabad", "pi@gmail.com","121323213", "Married", "Pakistani",  new Date(System.currentTimeMillis()), "BSSE","UOG", "A+", "2019", "Netsol", "Software Engineer", "IT Developer", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),"CRS", "Recommender system","group work", "1 year", 3, "Android"," development", "netsol","Best actress", "traveling", "Ms. abc",
            "Lecturer","UoG", "missabc@gmail.com", "+923823230", R.drawable.outline_account48dp);
    public static final CVsview YELLOW_LAMP = new CVsview(3,"Front end developer","ABC","Pakistan","Islamabad", "pi@gmail.com","121323213", "Married", "Pakistani",  new java.util.Date(System.currentTimeMillis()), "BSSE","UOG", "A+", "2019", "Netsol", "Software Engineer", "IT Developer", new java.util.Date(System.currentTimeMillis()), new java.util.Date(System.currentTimeMillis()),"CRS", "Recommender system","group work", "1 year", 3, "Android"," development", "netsol","Best actress", "traveling", "Ms. abc",
            "Lecturer","UoG", "missabc@gmail.com", "+923823230", R.drawable.outline_account48dp);
    public static final CVsview BLUE_MUG = new  CVsview(4,"Product Manager","Faris","Pakistan","Islamabad", "pi@gmail.com","121323213", "Married", "Pakistani",  new java.util.Date(System.currentTimeMillis()), "BSSE","UOG", "A+", "2019", "Netsol", "Software Engineer", "IT Developer", new java.util.Date(System.currentTimeMillis()), new java.util.Date(System.currentTimeMillis()),"CRS", "Recommender system","group work", "1 year", 3, "Android"," development", "netsol","Best actress", "traveling", "Ms. abc",
            "Lecturer","UoG", "missabc@gmail.com", "+923823230", R.drawable.outline_account48dp);
    public static final CVsview WHITE_MUG = new  CVsview(5,"QA Engineer","Ahmed","Pakistan","Islamabad", "pi@gmail.com","121323213", "Married", "Pakistani",  new java.util.Date(System.currentTimeMillis()), "BSSE","UOG", "A+", "2019", "Netsol", "Software Engineer", "IT Developer", new java.util.Date(System.currentTimeMillis()), new java.util.Date(System.currentTimeMillis()),"CRS", "Recommender system","group work", "1 year", 3, "Android"," development", "netsol","Best actress", "traveling", "Ms. abc",
            "Lecturer","UoG", "missabc@gmail.com", "+923823230", R.drawable.outline_account48dp);
    public static final CVsview RED_MUG = new  CVsview(6,"UX Researcher","Emaan","Pakistan","Islamabad", "pi@gmail.com","121323213", "Married", "Pakistani",  new java.util.Date(System.currentTimeMillis()), "BSSE","UOG", "A+", "2019", "Netsol", "Software Engineer", "IT Developer", new java.util.Date(System.currentTimeMillis()), new java.util.Date(System.currentTimeMillis()),"CRS", "Recommender system","group work", "1 year", 3, "Android"," development", "netsol","Best actress", "traveling", "Ms. abc",
            "Lecturer","UoG", "missabc@gmail.com", "+923823230", R.drawable.outline_account48dp);
    public static final CVsview BLACK_HAT = new  CVsview(7,"Software Engineer","Meesum","Pakistan","Islamabad", "pi@gmail.com","121323213", "Married", "Pakistani",  new java.util.Date(System.currentTimeMillis()), "BSSE","UOG", "A+", "2019", "Netsol", "Software Engineer", "IT Developer", new java.util.Date(System.currentTimeMillis()), new java.util.Date(System.currentTimeMillis()),"CRS", "Recommender system","group work", "1 year", 3, "Android"," development", "netsol","Best actress", "traveling", "Ms. abc",
            "Lecturer","UoG", "missabc@gmail.com", "+923823230", R.drawable.outline_account48dp);
    public static final CVsview BLUE_HAT = new  CVsview(8,"Product Manager","Eisa","Pakistan","Islamabad", "pi@gmail.com","121323213", "Married", "Pakistani",  new java.util.Date(System.currentTimeMillis()), "BSSE","UOG", "A+", "2019", "Netsol", "Software Engineer", "IT Developer", new java.util.Date(System.currentTimeMillis()), new java.util.Date(System.currentTimeMillis()),"CRS", "Recommender system","group work", "1 year", 3, "Android"," development", "netsol","Best actress", "traveling", "Ms. abc",
            "Lecturer","UoG", "missabc@gmail.com", "+923823230", R.drawable.outline_account48dp);
}
