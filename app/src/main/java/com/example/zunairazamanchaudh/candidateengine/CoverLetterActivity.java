package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.AdressJobseeker;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.coverletter;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.ResumeManager.CVDashBoard;
import com.example.zunairazamanchaudh.candidateengine.ResumeManager.MainCVTitle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CoverLetterActivity extends AppCompatActivity {
EditText clname,clcompanyname,clbody,cldate,claddress;
Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_letter);
        cldate=(EditText)findViewById(R.id.datetoday);
        clcompanyname=(EditText)findViewById(R.id.clcompanyname);
        clname=(EditText)findViewById(R.id.clname);
        clbody=(EditText)findViewById(R.id.clbody);
        claddress=(EditText)findViewById(R.id.claddress);
        save=(Button)findViewById(R.id.savecl);
        getUser();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             saveCV();
            }
        });

    }

    private void getUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child("coverletter")
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        coverletter user=singleSnapshot.getValue(coverletter.class);
                        cldate.setText(user.getDate());
                        clname.setText(user.getRecruitername());
                        clcompanyname.setText(user.getCompanyname());
                        claddress.setText(user.getAddress());
                        clbody.setText(user.getBody());
                    }}else {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
                    String strDate = mdformat.format(calendar.getTime());
                    cldate.setText(strDate);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }


    private void saveCV(){
        final coverletter sauser=new coverletter();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        sauser.setDate(cldate.getText().toString());
        sauser.setRecruitername(clname.getText().toString());
        sauser.setCompanyname(clcompanyname.getText().toString());
        sauser.setAddress(claddress.getText().toString());
        sauser.setBody(clbody.getText().toString());
        sauser.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        //getUser();
        //final String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference()
                .child("coverletter")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   redirectjbpreferenceFragment();
                Intent i=new Intent(CoverLetterActivity.this,WelcomeCandidate.class);
                startActivity(i);
                Toast.makeText(CoverLetterActivity.this,"Cover letter created!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(CoverLetterActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
