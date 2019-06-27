package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zunairazamanchaudh.candidateengine.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileView2 extends AppCompatActivity {
CircleImageView profile;
TextView name,locationorg,designation,citycv,dob,marriage,college,
        occupationskill,mobilenumber,country, email,educationcourse;
Button btnviewfullcv;
String cv_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_view2);
        cv_id=getIntent().getStringExtra("intent_cvid");
        profile=(CircleImageView)findViewById(R.id.profile);
        name=(TextView)findViewById(R.id.name);
        locationorg=(TextView)findViewById(R.id.location);
        designation=(TextView)findViewById(R.id.designation);
         citycv=(TextView)findViewById(R.id.citycv);
         dob=(TextView)findViewById(R.id.dob);
         marriage=(TextView)findViewById(R.id.marriage);
         college=(TextView)findViewById(R.id.college);
         occupationskill=(TextView)findViewById(R.id.occupation);
         mobilenumber=(TextView)findViewById(R.id.mobileNumber);
         country=(TextView)findViewById(R.id.countrycv);
         email=(TextView)findViewById(R.id.email);
         educationcourse=(TextView)findViewById(R.id.education);
         btnviewfullcv=(Button)findViewById(R.id.btnviewfullcv);


    }
}
