
package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPreference;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterCompany;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.skills;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditCompanyDetails extends AppCompatActivity implements OnClickListener{
Button btnupdate,btncancel;
EditText companyName,jobRole,CEO,headHR,groupOfCompany,industryName,description,city;
Spinner ownershipType,country;
ProgressBar mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_company_details);
        mProgress=(ProgressBar)findViewById(R.id.progressBarCompany);
        companyName=(EditText)findViewById(R.id.input_companyName);
        jobRole=(EditText)findViewById(R.id.input_jobRole);
        CEO=(EditText)findViewById(R.id.input_ceoName);
        city=(EditText)findViewById(R.id.input_companycity);
        country=(Spinner)findViewById(R.id.input_country);
        groupOfCompany=(EditText)findViewById(R.id.input_groupOfCompany);
        headHR=(EditText)findViewById(R.id.input_hodHRdepartment);
        industryName=(EditText)findViewById(R.id.input_industryName);
        description=(EditText)findViewById(R.id.input_companyDescription);
        ownershipType=(Spinner)findViewById(R.id.input_ownershipType);
        btnupdate=(Button)findViewById(R.id.btn_updateCompanyInfo);
        btncancel=(Button)findViewById(R.id.btn_cancelUpdate);
        btnupdate.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        setupFirebaseRecruiterAuth();
        showCompanyData();
        hideSoftKeyboard();

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.btn_updateCompanyInfo:
            updateCompanyInfo();
            Intent i=new Intent(this,WelcomeRecruiter.class);
            startActivity(i);
            finish();

            break;
        case R.id.cancelEditPdetails:
            Intent ii=new Intent(this,WelcomeRecruiter.class);
            startActivity(ii);
            finish();
            break;
    }
    }

    private void updateCompanyInfo(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_Company))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
                 updateExistingCompany();
                }else{
                    saveFirstTimeCompany();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateExistingCompany(){
                 /*
                ------ METHOD 1 for changing database data (proper way in this scenario) -----
                 */
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                 /*
                ------ Change Name -----
                 */
        if(!companyName.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("companyName")
                    .setValue(companyName.getText().toString());
        }

        if(!jobRole.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("jobRole")
                    .setValue(jobRole.getText().toString());
        }

        if(!CEO.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("ceoName")
                    .setValue(CEO.getText().toString());
        }

        if(!headHR.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("headHRdepartment")
                    .setValue(headHR.getText().toString());
        }
        if(!groupOfCompany.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("groupOfCompany")
                    .setValue(headHR.getText().toString());
        }
        if(!description.getText().toString().equals("")) {
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("companyDescription")
                    .setValue(description.getText().toString());
        }

        if(!industryName.getText().toString().equals("")) {
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("industryName")
                    .setValue(industryName.getText().toString());
        }
        if(!city.getText().toString().equals("")) {
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("city")
                    .setValue(city.getText().toString());
        }

        if(!ownershipType.getSelectedItem().toString().equals("")) {
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("ownershipType")
                    .setValue(ownershipType.getSelectedItem().toString());
        }

        if(!country.getSelectedItem().toString().equals("Select Country")) {
            reference.child(getString(R.string.dbnode_Company))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("country")
                    .setValue(country.getSelectedItem().toString());
        }
        Toast.makeText(EditCompanyDetails.this, "saved", Toast.LENGTH_SHORT).show();
        }
    private void showCompanyData(){
        Log.d(TAG, "getUserPreferenceData: getting the user's account information");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query1 = reference.child(getString(R.string.dbnode_Company))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
                    //this loop will return a single result
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Log.d(TAG, "onDataChange: (QUERY METHOD 1) found company: "
                                + singleSnapshot.getValue(JobPreference.class).toString());
                        RecruiterCompany jpuser = singleSnapshot.getValue(RecruiterCompany.class);
                        companyName.setText(jpuser.getCompanyName());
                        CEO.setText(jpuser.getCeoName());
                        jobRole.setText(jpuser.getJobRole());
                        groupOfCompany.setText(jpuser.getGroupOfCompany());
                        headHR.setText(jpuser.getHeadHRdepartment());
                        industryName.setText(jpuser.getIndustryName());
                        city.setText(jpuser.getCity());
                        String cc=jpuser.getCountry();
                        if(country.getItemAtPosition(0).toString()==cc){
                            country.setSelection(0);
                        }else if(country.getItemAtPosition(1).toString()==cc){
                            country.setSelection(1);
                        }else if(country.getItemAtPosition(2).toString()==cc){
                            country.setSelection(2);
                        }else if(country.getItemAtPosition(3).toString()==cc){
                            country.setSelection(3);
                        }else if(country.getItemAtPosition(4).toString()==cc){
                            country.setSelection(4);
                        }else if(country.getItemAtPosition(5).toString()==cc){
                            country.setSelection(5);
                        }else if(country.getItemAtPosition(6).toString()==cc){
                            country.setSelection(6);
                        }else if(country.getItemAtPosition(7).toString()==cc){
                            country.setSelection(7);
                        }else{
                            country.setSelection(8);
                        }
                //        ownershipType.setText(jpuser.getOwnershipType());
                        String match=jpuser.getOwnershipType();

                        if(ownershipType.getItemAtPosition(0).toString()==match){
                            ownershipType.setSelection(0);
                        }else if(ownershipType.getItemAtPosition(1).toString()==match){
                            ownershipType.setSelection(1);
                        }else if(ownershipType.getItemAtPosition(2).toString()==match){
                            ownershipType.setSelection(2);
                        }else if(ownershipType.getItemAtPosition(3).toString()==match){
                            ownershipType.setSelection(3);
                        }else if(ownershipType.getItemAtPosition(4).toString()==match){
                            ownershipType.setSelection(4);
                        }else if(ownershipType.getItemAtPosition(5).toString()==match){
                            ownershipType.setSelection(5);
                        }else{
                            ownershipType.setSelection(6);
                        }
                        description.setText(jpuser.getCompanyDescription());
                    }

                }else{}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

     private void saveFirstTimeCompany(){
         RecruiterCompany sauser=new RecruiterCompany();
         sauser.setCompanyName(companyName.getText().toString());
         sauser.setJobRole(jobRole.getText().toString());
         sauser.setCeoName(CEO.getText().toString());
         sauser.setHeadHRdepartment(headHR.getText().toString());
         sauser.setIndustryName(industryName.getText().toString());
         sauser.setOwnershipType(ownershipType.getSelectedItem().toString());
         sauser.setCompanyDescription(description.getText().toString());
         sauser.setCountry(country.getSelectedItem().toString());
         sauser.setCity(city.getText().toString());
         sauser.setRecruiteruser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
         FirebaseDatabase.getInstance().getReference()
                 .child("company")
                 .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                 .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                 //   redirectjbpreferenceFragment();
                 Toast.makeText(EditCompanyDetails.this,"Skill for user id:  "+id+" saved!",Toast.LENGTH_SHORT).show();
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {

                 Toast.makeText(EditCompanyDetails.this,"something went wrong",Toast.LENGTH_SHORT).show();
             }
         });
     }


    private static String TAG="EditCompany";
    //firebase
    private FirebaseAuth.AuthStateListener mAuthListener;
    private void setupFirebaseRecruiterAuth(){
        Log.d(TAG, "setupFirebaseAuth: started.");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: " + user.getEmail());


                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(EditCompanyDetails.this, "Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditCompanyDetails.this, LoginRecruiter.class);
                    startActivity(intent);
                    finish();
                }
                // ...
            }
        };


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    private void showDialog(){
        mProgress.setVisibility(View.VISIBLE);

    }

    private void hideDialog(){
        if(mProgress.getVisibility() == View.VISIBLE){
            mProgress.setVisibility(View.INVISIBLE);
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    private boolean isEmpty(String string){
        return string.equals("");
    }
}