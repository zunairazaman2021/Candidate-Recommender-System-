package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPreference;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterCompany;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.RecruiterUser;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.experience;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.users;
import com.example.zunairazamanchaudh.candidateengine.LoginJobSeeker;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecruiterProfileFragment extends Fragment implements View.OnClickListener{
ProgressBar mProgressbarprofile;
CircleImageView imageViewRecruiter;
Button btnuploadRecruiter;
ImageView editRecruiter,editcompanyInfo;
TextView nameRecruiter,showphone,showemail,showplace;
TextView showcompanyName,showjobrole,showceoname,showheadHR,
        showgroupOfCompany,showIndustryname,showownershipType,showCompanydescription;
    public RecruiterProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_recruiter_profile, container, false);
        editRecruiter=(ImageView)view.findViewById(R.id.editrecruiterinfo);
        editcompanyInfo=(ImageView)view.findViewById(R.id.editCompanyinfo);
        btnuploadRecruiter=(Button)view.findViewById(R.id.buttonFotoRecruiter);
        editRecruiter.setOnClickListener(this);
        editcompanyInfo.setOnClickListener(this);
        btnuploadRecruiter.setOnClickListener(this);
        imageViewRecruiter=(CircleImageView)view.findViewById(R.id.imageViewRecruiter);
        ////TextViews
        nameRecruiter=(TextView)view.findViewById(R.id.nameRecruiter);
        showphone=(TextView)view.findViewById(R.id.show_phone);
        showemail=(TextView)view.findViewById(R.id.show_email);
        //company views
        showplace=(TextView)view.findViewById(R.id.show_place);
        showcompanyName=(TextView)view.findViewById(R.id.show_companyName);
        showjobrole=(TextView)view.findViewById(R.id.show_jobRole);
        showceoname=(TextView)view.findViewById(R.id.show_ceoName);
        showheadHR=(TextView)view.findViewById(R.id.show_headHRdepartment);
        showgroupOfCompany=(TextView)view.findViewById(R.id.show_groupOfCompany);
        showIndustryname=(TextView)view.findViewById(R.id.show_industryName);
        showownershipType=(TextView)view.findViewById(R.id.show_ownershiptype);
        showCompanydescription=(TextView)view.findViewById(R.id.show_companyDescription);
        mProgressbarprofile=(ProgressBar)view.findViewById(R.id.progressBarProfileViewR);
        setupFirebaseRecruiterAuth();
        getRecruiterAccountData();
        getUserCompanyData();
        hideSoftKeyboard();
    return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonFotoRecruiter:
                break;
            case R.id.editrecruiterinfo:

                break;
            case R.id.editCompanyinfo:
                Intent i=new Intent(getActivity(),EditCompanyDetails.class);
                startActivity(i);
                break;

        }
    }


    private void getRecruiterAccountData(){
        Log.d(TAG, "getRecruiterAccountData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
         */
        Query query1 = reference.child(getString(R.string.dbnode_Recruiterusers))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Log.d(TAG, "onDataChange: (QUERY METHOD 1) found user: "
                                + singleSnapshot.getValue(users.class).toString());
                        RecruiterUser user=singleSnapshot.getValue(RecruiterUser.class);
                        nameRecruiter.setText(user.getFirstname()+" "+user.getLastname());
                        showemail.setText(user.getEmail());
                        showphone.setText(user.getPhone());
                        }}else
                            {
                            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void getUserCompanyData(){
        Log.d(TAG, "getUserPreferenceData: getting the user's account information");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        /*
            ---------- QUERY Method 1 ----------
             FirebaseDatabase.getInstance().getReference()
            .child("job")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .setValue(eduuser)
         */
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
                        showcompanyName.setText(jpuser.getCompanyName());
                        showjobrole.setText(jpuser.getJobRole());
                        showceoname.setText(jpuser.getCeoName());
                        showgroupOfCompany.setText(jpuser.getGroupOfCompany());
                        showheadHR.setText(jpuser.getHeadHRdepartment());
                        showIndustryname.setText(jpuser.getIndustryName());
                        showownershipType.setText(jpuser.getOwnershipType());
                        showplace.setText(jpuser.getCity()+", "+jpuser.getCountry());
                        showCompanydescription.setText(jpuser.getCompanyDescription());
                    }
                }else{}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }



    private static String TAG="RecruiterProfile";
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
                    Toast.makeText(getActivity(), "Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginRecruiter.class);
                    startActivity(intent);
                    getActivity().finish();
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
        mProgressbarprofile.setVisibility(View.VISIBLE);

    }

    private void hideDialog(){
        if(mProgressbarprofile.getVisibility() == View.VISIBLE){
            mProgressbarprofile.setVisibility(View.INVISIBLE);
        }
    }

    private void hideSoftKeyboard(){
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
