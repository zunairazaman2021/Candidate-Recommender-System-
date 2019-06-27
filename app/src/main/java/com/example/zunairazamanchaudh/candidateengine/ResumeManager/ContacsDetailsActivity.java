package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.contactdetails;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.R;
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

public class ContacsDetailsActivity extends AppCompatActivity implements View.OnClickListener {
EditText ciName,ciAddress,ciEmail,ciPhone;
Button saveContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacs_details);
        ciName=(EditText)findViewById(R.id.ciName);
        ciAddress=(EditText)findViewById(R.id.ciAddress);
        ciEmail=(EditText)findViewById(R.id.ciEmail);
        ciPhone=(EditText)findViewById(R.id.ciPhone);
        saveContact=(Button)findViewById(R.id.savecontactdetails);
        saveContact.setOnClickListener(this);
    getcontactdetails();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.savecontactdetails:
                savecontactDetailsCV();
                Intent i=new Intent();
                i.putExtra("KeyName",ciName.getText().toString());
                i.putExtra("KeyAdress",ciAddress.getText().toString());
                i.putExtra("KeyEmail",ciEmail.getText().toString());
                i.putExtra("KeyPhone",ciPhone.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }


    private void getcontactdetails(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_contactdetails_Resume))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        contactdetails user=singleSnapshot.getValue(contactdetails.class);
                       ciName.setText(user.getName());
                       ciAddress.setText(user.getAddress());
                       ciEmail.setText(user.getEmail());
                       ciPhone.setText(user.getPhone());
                        }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void savecontactDetailsCV(){
        final contactdetails sauser=new contactdetails();
        sauser.setName(ciName.getText().toString());
        sauser.setAddress(ciAddress.getText().toString());
        sauser.setPhone(ciPhone.getText().toString());
        sauser.setEmail(ciEmail.getText().toString());
        sauser.setContact_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference()
                .child("contactdetails_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
             //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
             //   startActivity(i);
                Toast.makeText(ContacsDetailsActivity.this,"Contact details saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(ContacsDetailsActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
