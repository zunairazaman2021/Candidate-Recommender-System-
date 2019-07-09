package com.example.zunairazamanchaudh.candidateengine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.AdressJobseeker;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditPersonalInfoJobseeker extends AppCompatActivity implements View.OnClickListener{
    private static String TAG="EditPersonalInfoDialog";
    EditText editLname,editFname,editdob,editphone,editemail,
            editNationality,editCurCity,editCurState,editCurzipcode;
    EditText editCurCountry,Currentpasswd;

    Button saveEditProfiledetails,cancelEditPdetails;
    ProgressBar progressBareditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info_jobseeker);
        editLname=(EditText)findViewById(R.id.editLname);
        editFname=(EditText)findViewById(R.id.editFname);
        progressBareditProfile=(ProgressBar)findViewById(R.id.progressBareditProfile);
        editdob=(EditText)findViewById(R.id.editdob);
        Currentpasswd=(EditText)findViewById(R.id.CurrentPassword);
        editphone=(EditText)findViewById(R.id.editphone);
        editemail=(EditText)findViewById(R.id.editemail);
        editNationality=(EditText)findViewById(R.id.editnationality);
        editCurCity=(EditText)findViewById(R.id.editCurCity);
        editCurCountry=(EditText)findViewById(R.id.editCurCountry);
        editCurState=(EditText)findViewById(R.id.editCurState);
        editCurzipcode=(EditText)findViewById(R.id.editCurzipcode);
        saveEditProfiledetails=(Button)findViewById(R.id.saveEditProfiledetails);
        cancelEditPdetails=(Button)findViewById(R.id.cancelEditPdetails);
        saveEditProfiledetails.setOnClickListener(this);
        cancelEditPdetails.setOnClickListener(this);
        getUserData();
        getUserAdressData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveEditProfiledetails:
                updateinfo();
                 Intent intent=new Intent(EditPersonalInfoJobseeker.this,WelcomeCandidate.class);
                 startActivity(intent);
                 finish();
                 break;
            case R.id.cancelEditPdetails:
                Intent i=new Intent(EditPersonalInfoJobseeker.this,WelcomeCandidate.class);
                startActivity(i);
                finish();
                break;
            default:
                break;
        }
    }
    private void updateinfo(){

        //see if they changed the email
        if(!editemail.getText().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
            //make sure email and current password fields are filled
            if(!isEmpty(editemail.getText().toString()) && !isEmpty(Currentpasswd.getText().toString())){

                //verify that user is changing to a company email address
                if(isValidDomain(editemail.getText().toString())){
                    editUserEmail();
                }else{
                    Toast.makeText(EditPersonalInfoJobseeker.this, "Invalid Domain", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(EditPersonalInfoJobseeker.this, "Email  and password Must be Filled to Save", Toast.LENGTH_SHORT).show();
            }
        }
                /*
                ------ METHOD 1 for changing database data (proper way in this scenario) -----
                 */
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                /*
                ------ Change Name -----
                 */
        if(!editFname.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_users))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("firstname")
                    .setValue(editFname.getText().toString());
        }

        if(!editLname.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_users))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("lastname")
                    .setValue(editLname.getText().toString());
        }
        if(!editdob.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_users))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("dob")
                    .setValue(editdob.getText().toString());
        }
        if(!editNationality.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_users))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("nationality")
                    .setValue(editNationality.getText().toString());
        }
                /*
                ------ Change Phone Number -----
                 */
        if(!editphone.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_users))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("phone")
                    .setValue(editphone.getText().toString());
        }
        if(!editCurCountry.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_addressDetails))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("country")
                    .setValue(editCurCountry.getText().toString());
        }

        if(!editCurCity.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_addressDetails))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("city")
                    .setValue(editCurCity.getText().toString());
        }

        if(!editCurState.getText().toString().equals("")){
            reference.child(getString(R.string.dbnode_addressDetails))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("state")
                    .setValue(editCurState.getText().toString());
        }

        if(!editCurzipcode.getText().toString().equals("")){
            int a=Integer.valueOf(editCurzipcode.getText().toString());
            reference.child(getString(R.string.dbnode_addressDetails))
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("zipcode")
                    .setValue(a);
        }
        Toast.makeText(EditPersonalInfoJobseeker.this, "saved", Toast.LENGTH_SHORT).show();

    }
    private void getUserData(){
        Log.d(TAG, "getUserData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
         */
        Query query1 = reference.child(getString(R.string.dbnode_users))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    //this loop will return a single result
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Log.d(TAG, "onDataChange: (QUERY METHOD 1) found user: "
                                + singleSnapshot.getValue(users.class).toString());
                        users user=singleSnapshot.getValue(users.class);
                        editFname.setText(user.getFirstname());
                        editLname.setText(user.getLastname());
                        editphone.setText(user.getPhone());
                        editemail.setText(user.getEmail());
                        editNationality.setText(user.getNationality());
                        editdob.setText(user.getDob());
                        //          User user = singleSnapshot.getValue(User.class);
                        //          mName.setText(user.getName());
                        //          mPhone.setText(user.getPhone());
                        //          ImageLoader.getInstance().displayImage(user.getProfile_image(), mProfileImage);
                    }
                }else{

                }
            }
            @Override
            public void onCancelled (DatabaseError databaseError){

            }
        });


        /*
            ---------- QUERY Method 2 ----------
         */
//        Query query2 = reference.child(getString(R.string.dbnode_users))
//                .orderByChild(getString(R.string.field_user_id))
//                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
//        query2.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                //this loop will return a single result
//                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
//                    Log.d(TAG, "onDataChange: (QUERY METHOD 2) found user: "
//                            + singleSnapshot.getValue(User.class).toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        //mEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

    }
    private void getUserAdressData() {
        Log.d(TAG, "getUserData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
         */
        Query query1 = reference.child(getString(R.string.dbnode_addressDetails))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    //this loop will return a single result
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Log.d(TAG, "onDataChange: (QUERY METHOD 1) found user: "
                                + singleSnapshot.getValue(AdressJobseeker.class).toString());
                        AdressJobseeker adress = singleSnapshot.getValue(AdressJobseeker.class);
                        editCurCountry.setText(adress.getCountry());
                        editCurCity.setText(adress.getCity());
                        editCurState.setText(adress.getState());
                        editCurzipcode.setText(adress.getZipcode());
                    }
                } else {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private boolean isEmpty(String string){
        return string.equals("");
    }

    private boolean isValidDomain(String email){
        Log.d(TAG, "isValidDomain: verifying email has correct domain: " + email);
        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        Log.d(TAG, "isValidDomain: users domain: " + domain);
        return domain.equals(DOMAIN_NAME);
    }
    private static final String DOMAIN_NAME = "gmail.com";
    private static final int REQUEST_CODE = 1234;
    private static final double MB_THRESHHOLD = 5.0;
    private static final double MB = 1000000.0;
    private void checkprovidersavailable(){
        ///////////////////now check to see if the email is not already present in the database

                             /*   FirebaseAuth.getInstance().fetchProvidersForEmail(editemail.getText().toString()).addOnCompleteListener(
                                        new OnCompleteListener<ProviderQueryResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<ProviderQueryResult> task) {

                                                if(task.isSuccessful()){
                                                    ///////// getProviders().size() will return size 1 if email ID is in use.

                                                    Log.d(TAG, "onComplete: RESULT: " + task.getResult().getProviders().size());
                                                    if(task.getResult().getProviders().size() == 1){
                                                        Log.d(TAG, "onComplete: That email is already in use.");
                                                        hideDialog();
                                                        Toast.makeText(getActivity(), "That email is already in use", Toast.LENGTH_SHORT).show();

                                                    }else{
                                                        Log.d(TAG, "onComplete: That email is available.");

                                                        /////////////////////add new email
                                                        FirebaseAuth.getInstance().getCurrentUser().updateEmail(editemail.getText().toString())
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            Log.d(TAG, "onComplete: User email address updated.");
                                                                            Toast.makeText(getActivity(), "Updated email", Toast.LENGTH_SHORT).show();
                                                                            sendVerificationEmail();
                                                                            FirebaseAuth.getInstance().signOut();
                                                                        }else{
                                                                            Log.d(TAG, "onComplete: Could not update email.");
                                                                            Toast.makeText(getActivity(), "unable to update email", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        hideDialog();
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        hideDialog();
                                                                        Toast.makeText(getActivity(), "unable to update email", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });


                                                    }

                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                hideDialog();
                                                Toast.makeText(getActivity(), "unable to update email", Toast.LENGTH_SHORT).show();
                                            }
                                        });*/


    }

    private void editUserEmail(){
        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.

        showDialog();

        AuthCredential credential = EmailAuthProvider
                .getCredential(FirebaseAuth.getInstance().getCurrentUser().getEmail(), Currentpasswd.getText().toString());
        Log.d(TAG, "editUserEmail: reauthenticating with:  \n email " + FirebaseAuth.getInstance().getCurrentUser().getEmail()
                + " \n passowrd: " + Currentpasswd.getText().toString());


        FirebaseAuth.getInstance().getCurrentUser().reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: reauthenticate success.");

                            //make sure the domain is valid
                            if(isValidDomain(editemail.getText().toString())){
                                //  checkprovidersavailable();
                                /////////////////////add new email
                                FirebaseAuth.getInstance().getCurrentUser().updateEmail(editemail.getText().toString())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "onComplete: User email address updated.");
                                                    Toast.makeText(EditPersonalInfoJobseeker.this, "Updated email", Toast.LENGTH_SHORT).show();
                                                    sendVerificationEmail();
                                                    FirebaseAuth.getInstance().signOut();
                                                }else{
                                                    Log.d(TAG, "onComplete: Could not update email.");
                                                    Toast.makeText(EditPersonalInfoJobseeker.this, "unable to update email", Toast.LENGTH_SHORT).show();
                                                }
                                                hideDialog();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                hideDialog();
                                                Toast.makeText(EditPersonalInfoJobseeker.this, "unable to update email", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }else{
                                Toast.makeText(EditPersonalInfoJobseeker.this, "you must use a company email", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Log.d(TAG, "onComplete: Incorrect Password");
                            Toast.makeText(EditPersonalInfoJobseeker.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            hideDialog();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        hideDialog();
                        Toast.makeText(EditPersonalInfoJobseeker.this, "“unable to update email”", Toast.LENGTH_SHORT).show();
                    }
                });
    }



    /**
     * sends an email verification link to the user
     */
    public void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(EditPersonalInfoJobseeker.this, "Sent Verification Email", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(EditPersonalInfoJobseeker.this, "Couldn't Verification Send Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    private void showDialog(){
        progressBareditProfile.setVisibility(View.VISIBLE);

    }

    private void hideDialog(){
        if(progressBareditProfile.getVisibility() == View.VISIBLE){
            progressBareditProfile.setVisibility(View.INVISIBLE);
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
