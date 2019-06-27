package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.workExperienceCV;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.detailjobposted2Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListOfExperienceActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<workExperienceCV> workExperiences=new ArrayList<workExperienceCV>();
    private Context context;
    private ListView listexp;
    private Button btnAddexp;
    SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
    public void showFullExpList(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child(String.valueOf(R.string.dbnode_experience_Resume))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    workExperienceCV mExp = dataSnapshot.getValue(workExperienceCV.class);

                    workExperiences.add(mExp);
                }

                WorkExperienceAdapter workExperienceAdapter = new WorkExperienceAdapter(workExperiences, ListOfExperienceActivity.this);
                listexp.setAdapter(workExperienceAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

//                progressDialog.dismiss();

            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_experience);
        btnAddexp=(Button)findViewById(R.id.btnaddExp);
        listexp=(ListView)findViewById(R.id.listExpDetails);
        showFullExpList();
       btnAddexp.setOnClickListener(this);
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
