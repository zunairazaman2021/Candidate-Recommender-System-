package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Personalinfo;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.contactdetails;
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

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {
EditText editnation,editmaritalstatus,editdob,editCustom;
Button btnsavePI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        editdob=(EditText)findViewById(R.id.dobPI);
        editmaritalstatus=(EditText)findViewById(R.id.maritalstatusPI);
        editnation=(EditText)findViewById(R.id.nationalityPI);
        editCustom=(EditText)findViewById(R.id.customPI);
        btnsavePI=(Button) findViewById(R.id.savePIdetails);
        btnsavePI.setOnClickListener(this);
getPersonalInfodetails();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.savePIdetails:
                personalInfoDetailsCV();
                Intent i=new Intent();
                i.putExtra("KeyNationality",editnation.getText().toString());
                i.putExtra("KeyMarital",editmaritalstatus.getText().toString());
                i.putExtra("KeyDOB",editdob.getText().toString());
                i.putExtra("KeyCustom",editCustom.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }


    private void getPersonalInfodetails(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_personalinfo_Resume))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Personalinfo user=singleSnapshot.getValue(Personalinfo.class);
                        editnation.setText(user.getNationality());
                        editmaritalstatus.setText(user.getMaritalstatus());
                        editdob.setText(user.getDob());
                        editCustom.setText(user.getCustom());
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void personalInfoDetailsCV(){
        final Personalinfo sauser=new Personalinfo();
        sauser.setMaritalstatus(editmaritalstatus.getText().toString());
        sauser.setNationality(editnation.getText().toString());
        sauser.setDob(editdob.getText().toString());
        sauser.setCustom(editCustom.getText().toString());
        sauser.setPersnlInfo_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference()
                .child("personalinfo_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(PersonalInfoActivity.this,"Contact details saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(PersonalInfoActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
