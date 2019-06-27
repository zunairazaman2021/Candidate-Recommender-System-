package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.workExperienceCV;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfAcademicDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Academic> courses=new ArrayList<Academic>();
    private Context context;
    private ListView list;
    private Button btnAdd;
public ListOfAcademicDetailsActivity(){

}
    public ListOfAcademicDetailsActivity(Context context, ArrayList<Academic> courses) {
        this.context=context;
        this.courses=courses;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_academic_details);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child(String.valueOf(R.string.dbnode_education_Resume))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Academic mExp = dataSnapshot.getValue(Academic.class);

                    courses.add(mExp);
                }

                //WorkExperienceAdapter workExperienceAdapter = new WorkExperienceAdapter(workExperiences, ListOfExperienceActivity.this);
                //listexp.setAdapter(workExperienceAdapter);
                AcademicAdapter adapter=new AcademicAdapter(ListOfAcademicDetailsActivity.this,courses);
                list.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

//                progressDialog.dismiss();

            }
        });



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
