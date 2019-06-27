package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Reference;
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

public class ReferenceActivity extends AppCompatActivity implements View.OnClickListener{
EditText rName,rDesignation,refOrg,refEmail,refMobile;
Button saveRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        rName=(EditText)findViewById(R.id.refName);
        rDesignation=(EditText)findViewById(R.id.refDesignation);
        refOrg=(EditText)findViewById(R.id.refOrg);
        refEmail=(EditText)findViewById(R.id.refEmail);
        refMobile=(EditText)findViewById(R.id.refMobile);
        saveRef=(Button)findViewById(R.id.saveReference);
        saveRef.setOnClickListener(this);
getReferencedetails();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveReference:
                saveReferenceDetailsCV();
                Intent refIntent=new Intent();
                refIntent.putExtra("rName",rName.getText().toString());
                refIntent.putExtra("rDesignation",rDesignation.getText().toString());
                refIntent.putExtra("rOrganization",refOrg.getText().toString());
                refIntent.putExtra("rEmail",refEmail.getText().toString());
                refIntent.putExtra("rMobile",refMobile.getText().toString());
                setResult(RESULT_OK,refIntent);
                finish();
                break;
        }
    }

    private void getReferencedetails(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_ReferenceDetails_Resume))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Reference user=singleSnapshot.getValue(Reference.class);
                        rName.setText(user.getRefname());
                        rDesignation.setText(user.getRefdesignation());
                        refEmail.setText(user.getRefEmail());
                        refMobile.setText(user.getRefMobile());
                        refOrg.setText(user.getRefOrg());
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void saveReferenceDetailsCV(){
        final Reference sauser=new Reference();
        sauser.setRefname(rName.getText().toString());
        sauser.setRefdesignation(rDesignation.getText().toString());
        sauser.setRefEmail(refEmail.getText().toString());
        sauser.setRefMobile(refMobile.getText().toString());
        sauser.setRefOrg(refOrg.getText().toString());
        sauser.setReference_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference()
                .child("ReferenceDetails_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(ReferenceActivity.this,"Reference details saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(ReferenceActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
