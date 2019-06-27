package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Objective;
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

public class ObjectiveActivity extends AppCompatActivity implements View.OnClickListener{
EditText eObj,eDate,ePlace;
Button btnSaveObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objective);
        eObj=(EditText)findViewById(R.id.ObjDescription);
        eDate=(EditText)findViewById(R.id.ObjDate);
        ePlace=(EditText)findViewById(R.id.Objplace);
        btnSaveObj=(Button)findViewById(R.id.saveObjective);
        btnSaveObj.setOnClickListener(this);
        getobjectivedetails();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveObjective:
                saveobjectiveDetailsCV();
                Intent objintent=new Intent();
                objintent.putExtra("KeyObjective",eObj.getText().toString());
                objintent.putExtra("KeyDate",eDate.getText().toString());
                objintent.putExtra("KeyPlace",ePlace.getText().toString());
                setResult(RESULT_OK,objintent);
                finish();
                break;
        }
    }

    private void getobjectivedetails(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_objective_Resume))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Objective user=singleSnapshot.getValue(Objective.class);
                        eObj.setText(user.getObjective());
                        ePlace.setText(user.getObjPlace());
                        eDate.setText(user.getObjDate());
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void saveobjectiveDetailsCV(){
        final Objective sauser=new Objective();
        sauser.setObjective(eObj.getText().toString());
        sauser.setObjPlace(ePlace.getText().toString());
        sauser.setObjDate(eDate.getText().toString());
        sauser.setObj_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference()
                .child("objective_Resume")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //   Intent i=new Intent(ContacsDetailsActivity.this,CVDashBoard.class);
                //   startActivity(i);
                Toast.makeText(ObjectiveActivity.this,"Objective details saved!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(ObjectiveActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
