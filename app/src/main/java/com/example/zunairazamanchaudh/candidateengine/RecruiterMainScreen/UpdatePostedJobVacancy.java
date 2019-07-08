package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobApplication;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
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
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.richeditor.RichEditor;

public class UpdatePostedJobVacancy extends AppCompatActivity implements View.OnClickListener{
    private RichEditor mEditor,mEditor1;
    private TextView mPreview,mPreview1;
    Spinner country,gender,experiencelevel,jobtype;
    TextInputEditText companyname,yourname,phone,jobtitle,city,noOfvacancies,salaryperMonth,
            minDegree,maxDegree,skills,candidateNationality;
    Button btnpublish;
    String inputDescr;
    String inputCandidDescr;
    ProgressBar mProgressjob;
    String editjobid="null";
    String keyappid="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_posted_job_vacancy);
        setupFirebaseRecruiterAuth();
        editjobid=getIntent().getStringExtra("edit_jobid");
        getjobdata();
        btnpublish=(Button)findViewById(R.id.publishJob);
        btnpublish.setOnClickListener(this);
        mProgressjob=(ProgressBar)findViewById(R.id.progressbarPublishJob);
        //spinners
        country=(Spinner)findViewById(R.id.jvCountry);
        jobtype=(Spinner)findViewById(R.id.jvJobType);
        gender=(Spinner)findViewById(R.id.jvGender);
        experiencelevel=(Spinner)findViewById(R.id.jvExperience);
        //editViews
        companyname=(TextInputEditText)findViewById(R.id.jvCompany);
        yourname=(TextInputEditText)findViewById(R.id.jvName);
        phone=(TextInputEditText)findViewById(R.id.jvPhone);
        jobtitle=(TextInputEditText)findViewById(R.id.jvJobtitle);
        city=(TextInputEditText)findViewById(R.id.jvCity);
        noOfvacancies=(TextInputEditText)findViewById(R.id.jvVacancies);
        salaryperMonth=(TextInputEditText)findViewById(R.id.jvSalary);
        minDegree=(TextInputEditText)findViewById(R.id.jvEducation);
        maxDegree=(TextInputEditText)findViewById(R.id.jvMaxEducation);
        skills=(TextInputEditText)findViewById(R.id.jvSkills);
        candidateNationality=(TextInputEditText)findViewById(R.id.jvNationality);

        //editor
        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor1=(RichEditor)findViewById(R.id.editor1);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.RED);
        mEditor.setEditorBackgroundColor(Color.BLUE);
        mEditor.setBackgroundColor(Color.BLUE);
        mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        //  mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("Insert text here...");
        //mEditor.setInputEnabled(false);
//////////////////// For Editor Desired candidate profile
        mEditor1.setEditorHeight(200);
        mEditor1.setEditorFontSize(22);
        mEditor1.setEditorFontColor(Color.RED);
        mEditor1.setEditorBackgroundColor(Color.BLUE);
        mEditor1.setBackgroundColor(Color.BLUE);
        mEditor1.setBackgroundResource(R.drawable.bg);
        mEditor1.setPadding(10, 10, 10, 10);
        //  mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor1.setPlaceholder("Insert text here...");
        //mEditor.setInputEnabled(false);


        mPreview = (TextView) findViewById(R.id.preview);
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override public void onTextChange(String text) {
                // mPreview.setText(text);
                inputDescr=text;
            }
        });
        mPreview1 = (TextView) findViewById(R.id.preview1);
        mEditor1.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override public void onTextChange(String text) {
                //     mPreview.setText(text);
                inputCandidDescr=text;
            }
        });

        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSubscript();
            }
        });

        findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });

        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setIndent();
            }
        });

        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setOutdent();
            }
        });

        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });

        findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBlockquote();
            }
        });

        findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBullets();
            }
        });
        //// Desired Candidate profile////////////////////////////////////////////////////////
        findViewById(R.id.action_bold1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor1.setBold();
            }
        });

        findViewById(R.id.action_italic1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor1.setItalic();
            }
        });

        findViewById(R.id.action_insert_bullets1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBullets();
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////

        findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setNumbers();
            }
        });

        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
                        "dachshund");
            }
        });

        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
            }
        });
        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertTodo();
            }
        });
        hideSoftKeyboard();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publishJob:
                if((!isEmpty(companyname.getText().toString())) &&
                        (!isEmpty(yourname.getText().toString())) && (!isEmpty(phone.getText().toString()))
                        &&(!isEmpty(jobtitle.getText().toString())) && (!isEmpty(city.getText().toString()))
                        && (!isEmpty(noOfvacancies.getText().toString())) && (!isEmpty(salaryperMonth.getText().toString()))
                        && (!isEmpty(minDegree.getText().toString())) && (!isEmpty(maxDegree.getText().toString()))
                        && (!isEmpty(skills.getText().toString())) && (!isEmpty(candidateNationality.getText().toString()))
                        ){
                    updateJobPostVacancy();
                }else{
                    Toast.makeText(UpdatePostedJobVacancy.this,"Fields cannot be null",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getjobdata(){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference()
                .child("jobPost")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(editjobid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    JobPost post=dataSnapshot1.getValue(JobPost.class);
                    companyname.setText(post.getCompanyName());
                    jobtitle.setText(post.getJobtitle());
                    yourname.setText(post.getYourName());
                    phone.setText(post.getPhone());
                    city.setText(post.getCity());
                    noOfvacancies.setText(post.getNoOfvacancie());
                    salaryperMonth.setText(String.valueOf(post.getSalary()));
                            minDegree.setText(post.getMinDegree());
                            maxDegree.setText(post.getMaxDegree());
                            skills.setText(post.getSkills());
                            candidateNationality.setText(post.getNationality());
                            mEditor.setPlaceholder(post.getJobdescription());
                            mEditor1.setPlaceholder(post.getCandidateDescription());
                    Toast.makeText(UpdatePostedJobVacancy.this,"Job data displayed",Toast.LENGTH_SHORT).show();
                }
                           }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdatePostedJobVacancy.this,"Could not load job data",Toast.LENGTH_SHORT).show();
            }
        });

    }
    String key;
    private void updateJobPostVacancy(){
        JobPost sauser=new JobPost();
        sauser.setCompanyName(companyname.getText().toString());
        sauser.setYourName(yourname.getText().toString());
        sauser.setJobtitle(jobtitle.getText().toString());
        sauser.setCity(city.getText().toString());
        sauser.setCountry(country.getSelectedItem().toString());
        if(experiencelevel.getSelectedItem().toString()=="Select experience level required"){
            sauser.setExperience("none");
        }else {
            sauser.setExperience(experiencelevel.getSelectedItem().toString());
        }
        sauser.setJobdescription(inputDescr);
        sauser.setCandidateDescription(inputCandidDescr);
        if(gender.getSelectedItem().toString()=="Select Gender"){
            sauser.setGenderPreference("none");
        }else {
            sauser.setGenderPreference(gender.getSelectedItem().toString());
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        sauser.setCreatedOn(strDate);
        if(jobtype.getSelectedItem().toString()=="Select job type"){
            sauser.setJobtype("Not specified");
        }else {
            sauser.setJobtype(jobtype.getSelectedItem().toString());
        }
        sauser.setPhone(phone.getText().toString());
        sauser.setMinDegree(minDegree.getText().toString());
        sauser.setMaxDegree(maxDegree.getText().toString());
        sauser.setSkills(skills.getText().toString());
        sauser.setNationality(candidateNationality.getText().toString());
        sauser.setNoOfvacancie(Integer.valueOf(noOfvacancies.getText().toString()));
        sauser.setSalary(Double.valueOf(salaryperMonth.getText().toString()));
        sauser.setStatus(true);
        sauser.setCreator_id(FirebaseAuth.getInstance().getCurrentUser().getUid());

        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    //    key=mDatabase.child("Posts").push().getKey();
        sauser.setJobpost_id(key);
        String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.child("Posts")
                .child(editjobid)
                .setValue(sauser);
        FirebaseDatabase.getInstance().getReference()
                .child("JobPost")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(editjobid)
                .setValue(sauser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
               // JobApplication jb=new JobApplication();
               // jb.setCv_id("000");
               // jb.setJob_id(editjobid);
               // jb.setRecruiter_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
               // FirebaseDatabase.getInstance().getReference().child("AllApplications").orderByChild("");

                //jb.setApplication_id(editjobid);
                //jb.setStatus("true");
                //final String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
               /*Map<String, Object> updates = new HashMap<String,Object>();
                   updates.put("status","true");
                FirebaseDatabase.getInstance().getReference().child("AllApplications")
                        .child(keyappid)
                        .updateChildren(updates);
                FirebaseDatabase.getInstance().getReference()
                        .child("jobApplication")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child(editjobid).child(keyappid).updateChildren(updates);
               */
                //   redirectjbpreferenceFragment();
                Intent i=new Intent(UpdatePostedJobVacancy.this,WelcomeRecruiter.class);
                startActivity(i);
                Toast.makeText(UpdatePostedJobVacancy.this,"Job updated!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(UpdatePostedJobVacancy.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }



    private static String TAG="JobPostVacancy";
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
                    Toast.makeText(UpdatePostedJobVacancy.this, "Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdatePostedJobVacancy.this, LoginRecruiter.class);
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
        mProgressjob.setVisibility(View.VISIBLE);

    }

    private void hideDialog(){
        if(mProgressjob.getVisibility() == View.VISIBLE){
            mProgressjob.setVisibility(View.INVISIBLE);
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    private boolean isEmpty(String string){
        return string.equals("");
    }

}
