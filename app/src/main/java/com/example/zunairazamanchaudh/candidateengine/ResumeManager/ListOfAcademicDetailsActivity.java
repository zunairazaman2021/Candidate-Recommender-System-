package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zunairazamanchaudh.candidateengine.R;

import java.util.ArrayList;

public class ListOfAcademicDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Courses> courses=new ArrayList<Courses>();
    private Context context;
    private ListView list;
    private Button btnAdd;
public ListOfAcademicDetailsActivity(){

}
    public ListOfAcademicDetailsActivity(Context context, ArrayList<Courses> courses) {
        this.context=context;
        this.courses=courses;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_academic_details);
        ArrayList<Courses> coursesArrayList = new ArrayList<Courses>();
        coursesArrayList.add(new Courses("BSSE", "UOG", "A+", 2019));
        coursesArrayList.add(new Courses("BSSE", "UOG", "A+", 2019));
        coursesArrayList.add(new Courses("BSSE", "UOG", "A+", 2019));
        AcademicAdapter adapter=new AcademicAdapter(this,coursesArrayList);
        list.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnaddEdu:
                Intent intent=new Intent(ListOfAcademicDetailsActivity.this,AcademicDetailsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
