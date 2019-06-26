package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobPostVacancyActivity;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.WelcomeRecruiter;
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

public class MainCVTitle extends AppCompatActivity implements View.OnClickListener {
Toolbar toolbar;
EditText editTitle;
Button btn;



    private String firstname;
    private String lastname;
    private String profile_image;
    private String security_level;
    private String dob;
    private String phone;
    private String email;
    private String nationality;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cvtitle);
        toolbar=(Toolbar)findViewById(R.id.toolbarCV);
        toolbar.setTitle("Resume Builder");
        editTitle=(EditText)findViewById(R.id.CVTitlep);
        btn=(Button)findViewById(R.id.createcv);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createcv:
                if(editTitle.getText().toString()!=""){
                    saveCV();
                }else {
                    editTitle.setError("Field cannot be empty");
                }
                break;
            case R.id.cancelcv:
                break;
        }
    }

    private void getUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_users))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        users user=singleSnapshot.getValue(users.class);
                        firstname=user.getFirstname();
                        lastname=user.getLastname();
                        profile_image=user.getProfile_image();
                        security_level=user.getSecurity_level();
                        dob=user.getDob();
                        nationality=user.getNationality();
                        phone=user.getPhone();
                        email=user.getEmail();
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void saveCV(){
        CVTitle sauser=new CVTitle();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        sauser.setCreatedon(strDate);
        sauser.setCv_title(editTitle.getText().toString());
        sauser.setFirstname(firstname);
        sauser.setLastname(lastname);
        sauser.setDob(dob);
        sauser.setEmail(email);
        sauser.setPhone(phone);
        sauser.setProfile_image(profile_image);
        sauser.setNationality(nationality);
        sauser.setSecurity_level(security_level);

        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        getUser();
        String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference()
                .child("MyResume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   redirectjbpreferenceFragment();
                Intent i=new Intent(MainCVTitle.this,CVDashBoard.class);
                startActivity(i);
                Toast.makeText(MainCVTitle.this,"CV created!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainCVTitle.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
