package com.example.zunairazamanchaudh.candidateengine;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobPostVacancyActivity;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.WelcomeRecruiter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spark.submitbutton.SubmitButton;
import com.tomer.fadingtextview.FadingTextView;
public class signup extends AppCompatActivity implements View.OnClickListener{
    private static final String DOMAIN_NAME = "gmail.com";
    private Button btnsignup;
private Button btnsignin;
    private ProgressBar mProgressBar;
FadingTextView FTS;
String[] text={"SIGN UP"};
private static int TIMEOUT=2000;
TextInputEditText fname,lname,mEmail,passwd,confirmpasswd;
    private TextInputLayout textname;
    private TextInputLayout textlastname;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;

    private TextInputLayout textInputPasswordconfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fname=(TextInputEditText)findViewById(R.id.firstnameadd);
        lname=(TextInputEditText)findViewById(R.id.lastnameadd);
        mEmail=(TextInputEditText)findViewById(R.id.emailaddjobseeker);
        passwd=(TextInputEditText)findViewById(R.id.addpassjobseeker);
        confirmpasswd=(TextInputEditText)findViewById(R.id.confirmjobseekerpass);
        textname=findViewById(R.id.fnamew);
        textlastname=findViewById(R.id.lnamew);
        textInputEmail=findViewById(R.id.emailadd);
        textInputPassword=findViewById(R.id.passadd);
        textInputPasswordconfirm=findViewById(R.id.passaddconfirm);
        btnsignup=findViewById(R.id.creataccount);
        btnsignin=findViewById(R.id.btnsignnn);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBarsignup);
        btnsignup.setOnClickListener(this);
       btnsignin.setOnClickListener(this);
       FTS=findViewById(R.id.titlesignup);
       FTS.setTexts(text);
       hideSoftKeyboard();
    }
    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = textname.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textname.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            textname.setError("Username too long");
            return false;
        } else {
            textname.setError(null);
            return true;
        }
    }

    private boolean validatelastname() {
        String usernameInput = textname.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textname.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            textname.setError("Username too long");
            return false;
        } else {
            textname.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    private boolean validatePasswordconfirm() {
        String passwordInput = textInputPasswordconfirm.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPasswordconfirm.setError("Field can't be empty");
            return false;
        } else {
            textInputPasswordconfirm.setError(null);
            return true;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsignnn:
              Intent ii=new Intent(signup.this,SplashScreen.class);
              startActivity(ii);
                break;
            case R.id.creataccount:
                if (!validateEmail() | !validateUsername() | !validatePassword() | !validatelastname() | !validatePasswordconfirm()) {
                    return;
                }

                //check if user has a company email address
                if(isValidDomain(mEmail.getText().toString())){

                    //check if passwords match
                    if(doStringsMatch(passwd.getText().toString(), confirmpasswd.getText().toString())){

                        //Initiate registration task
                        registerNewEmail(mEmail.getText().toString(), passwd.getText().toString());
                    }else{
                        Toast.makeText(signup.this, "Passwords do not Match", Toast.LENGTH_SHORT).show();
                    }
                }

                else{
                    Toast.makeText(signup.this, "Please Register with Company Email", Toast.LENGTH_SHORT).show();
                }

                String input = "Email: " + textInputEmail.getEditText().getText().toString();
                input += "\n";
                input += "Username: " + textname.getEditText().getText().toString();
                input += "\n";
                input += "Password: " + textInputPassword.getEditText().getText().toString();

              //  Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

           //     Intent i=new Intent(signup.this,RegistrationSlide.class);
            //    startActivity(i);
              //  EditPersonalDetails newFragment = EditPersonalDetails.newInstance();
              //  newFragment.show(getSupportFragmentManager(),"dialog");
                 break;
                default:
                    break;
        }
    }



    private void sendVerificationEmail(){
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(signup.this,"sent verification email",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(signup.this, "Couldn't send verification email",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


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
                          //  User user=new User();
                           // user.setName(email.substring(0,email.indexOf("@")));
                            //user.setPhone("1");
                            //user.setProfile_image("");
                            //user.setSecurity_level("1");
                            //user.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            users user=new users();
                            user.setFirstname(fname.getText().toString());
                            user.setLastname(fname.getText().toString());
                            user.setProfile_image("");
                            user.setSecurity_level("1");
                            user.setDob("");
                            user.setPhone("");
                            user.setEmail(mEmail.getText().toString());
                            user.setNationality("");
                            user.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            FirebaseDatabase.getInstance().getReference()
                                    .child(getString(R.string.dbnode_users))
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                   // FirebaseAuth.getInstance().signOut();
                                    redirectRegisterScreen();
                                  //  redirectLoginScreen();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                              //      FirebaseAuth.getInstance().signOut();
                               //     redirectLoginJobseekerScreen();
                                    Toast.makeText(signup.this,"something went wrong",Toast.LENGTH_SHORT).show();

                                }
                            });



                            ///this code replaced by upper onfailure code
                            //FirebaseAuth.getInstance().signOut();
                            //redirect the user to the login screen
                            //redirectLoginScreen();
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(signup.this, "Unable to Register",
                                    Toast.LENGTH_SHORT).show();
                        }
                        hideDialog();

                        // ...
                    }
                });

    }

    private void redirectRegisterScreen(){
        Log.d(TAG, "redirectLoginScreen: redirecting to login screen.");

        Intent intent = new Intent(signup.this, RegistrationSlide.class);
        startActivity(intent);
     //   finish();
    }

    private void redirectLoginJobseekerScreen(){
        Log.d(TAG, "redirectLoginScreen: redirecting to login screen.");

        Intent intent = new Intent(signup.this, LoginJobSeeker.class);
        startActivity(intent);
        finish();
    }
    private static final String TAG = "signup";

    private boolean isValidDomain(String email){
        Log.d(TAG, "isValidDomain: verifying email has correct domain: " + email);
        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        Log.d(TAG, "isValidDomain: users domain: " + domain);
        return domain.equals(DOMAIN_NAME);
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
}
