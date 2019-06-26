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
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.LoginRecruiter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.WelcomeRecruiter;
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

public class LoginJobSeeker extends AppCompatActivity implements View.OnClickListener {
Button btnlogin,signup,forgetpass;
EditText mEmail,mPasswd;
TextView resendverification;
    //Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "LoginJobSeeker";

    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_job_seeker);
        mEmail=(EditText)findViewById(R.id.jobseekeremail);
        mPasswd=(EditText)findViewById(R.id.jobseekerpass);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBarloginJb);
        signup=(Button)findViewById(R.id.btnsign1);
        forgetpass=(Button)findViewById(R.id.btnforgetpass1);
        signup.setOnClickListener(this);
        forgetpass.setOnClickListener(this);
        btnlogin=(Button)findViewById(R.id.btnlogin1);
        btnlogin.setOnClickListener(this);
        resendverification=(TextView)findViewById(R.id.resendverification);
        resendverification.setOnClickListener(this);
        setupFirebaseAuth();
        hideSoftKeyboard();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin1:
                //check if the fields are filled out
                if(!isEmpty(mEmail.getText().toString())
                        && !isEmpty(mPasswd.getText().toString())){
                    Log.d(TAG, "onClick: attempting to authenticate.");

                    showDialog();

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(mEmail.getText().toString(),
                            mPasswd.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    hideDialog();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginJobSeeker.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            hideDialog();
                        }
                    });
                }else{
                    Toast.makeText(LoginJobSeeker.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnsign1:
                Intent i=new Intent(LoginJobSeeker.this,signup.class);
                startActivity(i);
                break;
            case R.id.btnforgetpass1:
                PasswordResetDialog dialog = new PasswordResetDialog();
                dialog.show(getSupportFragmentManager(), "dialog_password_reset");
                break;
            case R.id.resendverification:
                ResendVerificationDialog dialogg = new ResendVerificationDialog();
                dialogg.show(getSupportFragmentManager(), "dialog_resend_email_verification");
                break;
             default:
                 break;
        }
    }


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
        ----------------------------- Firebase setup ---------------------------------
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: started.");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    //check if email is verified
                    if(user.isEmailVerified()){
                        Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                        Toast.makeText(LoginJobSeeker.this, "Authenticated with: " + user.getEmail(), Toast.LENGTH_SHORT).show();


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
                                if(dataSnapshot.getValue()!=null){
                                      Intent intent = new Intent(LoginJobSeeker.this, WelcomeCandidate.class);
                                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                     startActivity(intent);
                                     finish();

                                }else
                                {
                                    Toast.makeText(LoginJobSeeker.this,"User must be registered as Recruiter",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }else{
                        Toast.makeText(LoginJobSeeker.this, "Email is not Verified\nCheck your Inbox", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
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
}
