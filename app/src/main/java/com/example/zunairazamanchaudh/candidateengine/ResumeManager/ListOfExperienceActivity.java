package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zunairazamanchaudh.candidateengine.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListOfExperienceActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<WorkExperience> workExperiences=new ArrayList<WorkExperience>();
    private Context context;
    private ListView listexp;
    private Button btnAddexp;
    SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_experience);
        btnAddexp=(Button)findViewById(R.id.btnaddExp);
        listexp=(ListView)findViewById(R.id.listExpDetails);
try {
    Date date1 = dateformat3.parse("17/7/2000");
    Date date2 = dateformat3.parse("17/12/2001");

    workExperiences.add(new WorkExperience("Tesco", "Marketing Manager", "Served as marketing manager at Tesco companies", true, date1, date2));
    workExperiences.add(new WorkExperience("Tesco", "Marketing Manager", "Served as marketing manager at Tesco companies", true, date1, date2));
    workExperiences.add(new WorkExperience("Tesco", "Marketing Manager", "Served as marketing manager at Tesco companies", true, date1, date2));

    WorkExperienceAdapter workExperienceAdapter = new WorkExperienceAdapter(workExperiences, this);
    listexp.setAdapter(workExperienceAdapter);
    btnAddexp.setOnClickListener(this);
}catch (Exception e){

}
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnaddExp:
                Intent i=new Intent(this,ExperienceActivity.class);
                startActivity(i);
            break;

        }
    }
}
