package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterFollowers;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterUser;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterProfileUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class RegisterRecruiterActivity extends AppCompatActivity {
    private static final String TAG ="RegisterRecruiter";
    private static final String DOMAIN_NAME = "gmail.com";
    //widgets
    private EditText mEmail, mPassword, mConfirmPassword,firstname,lastname,phonenumber;
    private Button mRegister;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_recruiter);
        firstname=(EditText)findViewById(R.id.input_firstName);
        lastname=(EditText)findViewById(R.id.input_lastName);
        phonenumber=(EditText)findViewById(R.id.input_phoneNumber);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mConfirmPassword = (EditText) findViewById(R.id.input_confirm_password);
        mRegister = (Button) findViewById(R.id.btn_register);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBarRegister);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: attempting to register.");

                //check for null valued EditText fields
                if( !isEmpty(firstname.getText().toString()) && !isEmpty(lastname.getText().toString())
                        && !isEmpty(phonenumber.getText().toString())
                        && !isEmpty(mEmail.getText().toString())
                        && !isEmpty(mPassword.getText().toString())
                        && !isEmpty(mConfirmPassword.getText().toString())){
                    //check if user has a company email address
                    if(isValidDomain(mEmail.getText().toString())){
                        //check if passwords match
                        if(doStringsMatch(mPassword.getText().toString(), mConfirmPassword.getText().toString())){

                            //Initiate registration task
                            registerNewEmail(mEmail.getText().toString(), mPassword.getText().toString());
                        }else{
                            Toast.makeText(RegisterRecruiterActivity.this, "Passwords do not Match", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else{
                        Toast.makeText(RegisterRecruiterActivity.this, "Please Register with Company Email", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegisterRecruiterActivity.this, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hideSoftKeyboard();

    }

    private void sendVerificationEmail(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterRecruiterActivity.this,"sent verification email",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterRecruiterActivity.this, "Couldn't send verification email",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    /**
     * Register a new email and password to Firebase Authentication
     * @param email
     * @param password
     */
    public void registerNewEmail(final String email, String password){


        showDialog();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: AuthState: " + FirebaseAuth.getInstance().getCurrentUser().getUid());
                            //send email verification
                            sendVerificationEmail();
                            RecruiterUser user=new RecruiterUser();
                            user.setFirstname(firstname.getText().toString());
                            user.setLastname(lastname.getText().toString());
                            user.setPhone(phonenumber.getText().toString());
                            user.setProfile_image("");
                            user.setEmail(mEmail.getText().toString());
                            user.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            FirebaseDatabase.getInstance().getReference()
                                    .child(getString(R.string.dbnode_Recruiterusers))
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    FirebaseAuth.getInstance().signOut();
                                    redirectLoginScreen();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    FirebaseAuth.getInstance().signOut();
                                    redirectLoginScreen();
                                    Toast.makeText(RegisterRecruiterActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();

                                }
                            });
                            ///this code replaced by upper onfailure code
                            //FirebaseAuth.getInstance().signOut();
                            //redirect the user to the login screen
                            //redirectLoginScreen();
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterRecruiterActivity.this, "Unable to Register",
                                    Toast.LENGTH_SHORT).show();
                        }
                        hideDialog();

                        // ...
                    }
                });

    }

    /**
     * Returns True if the user's email contains '@tabian.ca'
     * @param email
     * @return
     */
    private boolean isValidDomain(String email){
        Log.d(TAG, "isValidDomain: verifying email has correct domain: " + email);
        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        Log.d(TAG, "isValidDomain: users domain: " + domain);
        return domain.equals(DOMAIN_NAME);
    }

    /**
     * Redirects the user to the login screen
     */
    private void redirectLoginScreen(){
        Log.d(TAG, "redirectLoginScreen: redirecting to login screen.");

        Intent intent = new Intent(RegisterRecruiterActivity.this, LoginRecruiter.class);
        startActivity(intent);
        finish();
    }
    /**
     * Return true if @param 's1' matches @param 's2'
     * @param s1
     * @param s2
     * @return
     */
    private boolean doStringsMatch(String s1, String s2){
        return s1.equals(s2);
    }

    /**
     * Return true if the @param is null
     * @param string
     * @return
     */
    private boolean isEmpty(String string){
        return string.equals("");
    }
    private void showDialog(){
        mProgressBar.setVisibility(View.VISIBLE);

    }
    private void hideDialog(){
        if(mProgressBar.getVisibility() == View.VISIBLE){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }



/*
    private void followForJob(String recruiter){
        RecruiterFollowers ja=new RecruiterFollowers();
        ja.setRecruiterid(recruiter);
        ja.setFolloweddate("");
        ja.setFollowingstatus("false");
        ja.setUser_id("000");
        ja.setFollowid("000");
        ja.setFirstname("");
        ja.setLastname("");
        ja.setDob("");
        ja.setNationality("");
        ja.setPhone("");
        ja.setEmail("");
        ja.setSecurity_level("");
        ja.setCountry("");
        ja.setCity("");
        ja.setZipcode("");
        ja.setState("");
        ja.setProfile_image("");
        ja.setRimage("");
        ja.setRfirstname("");
        ja.setRlastname("");
        ja.setRemail("");
        ja.setRphone("");
        FirebaseDatabase.getInstance().getReference()
                .child("RecruiterFollowers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("000")
                .setValue(ja).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
     }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }*/
}