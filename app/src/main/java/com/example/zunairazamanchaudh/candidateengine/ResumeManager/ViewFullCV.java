package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.cvvieweducationAdapter;
import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.cvviewexperienceAdapter;
import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.cvviewindustryAdapter;
import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.cvviewprojectAdapter;
import com.example.zunairazamanchaudh.candidateengine.Adapters.DisplayResumeAdapters.cvviewskillsAdapter;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.JobPost;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Academic;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Awards;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.CVTitle;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Hobby;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Industry;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Interests;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Objective;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Personalinfo;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.ProjectDetails;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Reference;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.SkillsCV;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.Strengths;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.contactdetails;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase.workExperienceCV;
import com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.experience;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.SearchCV2Adapter;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.AdaptersRecruiter.detailjobposted2Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewFullCV extends AppCompatActivity {
RecyclerView vEducationRecycler,vfExperienceRecycler,vfSkillsRecycler
        ,vfProjectRecycler,vfIndustryRecycler;

    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

TextView vName,vAddress,vcitycountry,vPhone,vEmail,vCVtitle;

TextView vRefName,vRefDesignation,vRefOrg,vRefPhone,vRefEmail;

TextView vHobbies,vInterests,vStrengths,vObjective,vAward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_cv);
        String cvid=getIntent().getStringExtra("intent_viewFullCV");
        vEducationRecycler=(RecyclerView)findViewById(R.id.vEducationRecycler);
        vfExperienceRecycler=(RecyclerView)findViewById(R.id.vfExperienceRecycler);
        vfSkillsRecycler=(RecyclerView)findViewById(R.id.vfSkillsRecycler);
        vfProjectRecycler=(RecyclerView)findViewById(R.id.vfProjectRecycler);
        vfIndustryRecycler=(RecyclerView)findViewById(R.id.vfIndustryRecycler);
        vCVtitle=(TextView)findViewById(R.id.vCVTitle);
        vRefName=(TextView)findViewById(R.id.vRefName);
        vRefDesignation=(TextView)findViewById(R.id.vRefDesignation);
        vRefOrg=(TextView)findViewById(R.id.vRefOrg);
        vRefPhone=(TextView)findViewById(R.id.vRefPhone);
        vRefEmail=(TextView)findViewById(R.id.vRefEmail);
        vName=(TextView)findViewById(R.id.vName);
        vAddress=(TextView)findViewById(R.id.vAddress);
        vcitycountry=(TextView)findViewById(R.id.vcitycountry);
        vPhone=(TextView)findViewById(R.id.vPhone);
        vEmail=(TextView)findViewById(R.id.vEmail);
        vObjective=(TextView)findViewById(R.id.vObjective);
        vHobbies=(TextView)findViewById(R.id.vHobby);
        vInterests=(TextView)findViewById(R.id.vInterest);
        vStrengths=(TextView)findViewById(R.id.vStrength);
        vAward=(TextView)findViewById(R.id.vAward);
        getFirstHead(cvid);
        getDataFromAwardsResume(cvid);
        getDataFromHobbiesResume(cvid);
        getDataFromInterestesResume(cvid);
        getDataFromStrengthsResume(cvid);
        getDataFromObjectiveResume(cvid);
        getDataFromcontactdetails_Resume(cvid);
        getDataFromReference(cvid);
        //Recylers
        getExperience(cvid);
        getProjects(cvid);
        getEducation(cvid);
        getSkillsResume(cvid);
        getIndustries(cvid);
    }

    public RecyclerView.Adapter miAdapter;
    private RecyclerView.LayoutManager layoutManageri;

    private void getIndustries(String id){
        final List<Industry> list = new ArrayList<>();
        vfIndustryRecycler.setHasFixedSize(true);

        vfIndustryRecycler.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        //     progressDialog.setMessage("Loading Data...");

        // progressDialog.show();
        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference()
                .child("industries_Resume");
        Query query=dref.child(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Industry mjobs = dataSnapshot.getValue(Industry.class);

                    list.add(mjobs);
                }
                cvviewindustryAdapter adapter=(cvviewindustryAdapter)vfIndustryRecycler.getAdapter();

                adapter = new cvviewindustryAdapter(list);

                vfIndustryRecycler.setAdapter(adapter);

                //           progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //         progressDialog.dismiss();

            }
        });

    }
    private void getSkillsResume(String id){

        final List<SkillsCV> list = new ArrayList<>();
        vfSkillsRecycler.setHasFixedSize(true);

        vfSkillsRecycler.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        //     progressDialog.setMessage("Loading Data...");

        // progressDialog.show();
        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference()
                .child("skills_Resume");
        Query query=dref.child(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    SkillsCV mjobs = dataSnapshot.getValue(SkillsCV.class);

                    list.add(mjobs);
                }
                cvviewskillsAdapter adapter=(cvviewskillsAdapter)vfSkillsRecycler.getAdapter();

               adapter = new cvviewskillsAdapter( list);

                vfSkillsRecycler.setAdapter(adapter);

                //           progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //         progressDialog.dismiss();

            }
        });
    }
    private void getEducation(String id){

        final List<Academic> list = new ArrayList<>();
        vEducationRecycler.setHasFixedSize(true);

        vEducationRecycler.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        //     progressDialog.setMessage("Loading Data...");

       // progressDialog.show();
        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference()
                .child("education_Resume");
        Query query=dref.child(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Academic mjobs = dataSnapshot.getValue(Academic.class);

                    list.add(mjobs);
                }
                cvvieweducationAdapter adapter=(cvvieweducationAdapter) vEducationRecycler.getAdapter();

                 adapter = new cvvieweducationAdapter( list);

                vEducationRecycler.setAdapter(adapter);

                //           progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //         progressDialog.dismiss();

            }
        });
    }

    private void getProjects(String id){

        final List<ProjectDetails> list = new ArrayList<>();
        vfProjectRecycler.setHasFixedSize(true);

        vfProjectRecycler.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

   //     progressDialog.setMessage("Loading Data...");

        //progressDialog.show();
        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference()
                .child("projects_Resume");
        Query query=dref.child(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    ProjectDetails mjobs = dataSnapshot.getValue(ProjectDetails.class);

                    list.add(mjobs);
                }

                cvviewprojectAdapter adapter=(cvviewprojectAdapter)vfProjectRecycler.getAdapter();
                adapter = new cvviewprojectAdapter( list);

                vfProjectRecycler.setAdapter(adapter);

     //           progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

       //         progressDialog.dismiss();

            }
        });
    }

    private void getExperience(String id){

        final List<workExperienceCV> list = new ArrayList<>();
        vfExperienceRecycler.setHasFixedSize(true);

        vfExperienceRecycler.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

       // progressDialog.setMessage("Loading Data...");

        //progressDialog.show();
        DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference()
                .child("experience_Resume");
        Query query=ref.child(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    workExperienceCV mjobs = dataSnapshot.getValue(workExperienceCV.class);

                    list.add(mjobs);
                }

                cvviewexperienceAdapter adapter = new cvviewexperienceAdapter( list);

                vfExperienceRecycler.setAdapter(adapter);

         //       progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

       //         progressDialog.dismiss();

            }
        });
    }


    private void getFirstHead(String id){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child(getString(R.string.dbnode_MyResume))
                .orderByKey().equalTo(id);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        CVTitle user=singleSnapshot.getValue(CVTitle.class);
                        vName.setText(user.getFirstname()+" "+user.getLastname());
                        vCVtitle.setText(user.getCv_title());
                        vcitycountry.setText(user.getCity()+", "+user.getCountry());
                        vPhone.setText(user.getPhone());
                        vEmail.setText(user.getEmail());
                        //setimage later IA
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //  editEmailP.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

    }

    private void getDataFromReference(String id){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child("ReferenceDetails_Resume")
                .orderByKey().equalTo(id);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Reference user=singleSnapshot.getValue(Reference.class);
                        vRefName.setText(user.getRefname());
                        vRefDesignation.setText(user.getRefdesignation());
                        vRefOrg.setText(user.getRefOrg());
                        vRefEmail.setText(user.getRefEmail());
                        vRefPhone.setText(user.getRefMobile());
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDataFromcontactdetails_Resume(String id){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child("contactdetails_Resume")
                .orderByKey().equalTo(id);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        contactdetails user=singleSnapshot.getValue(contactdetails.class);
                        vAddress.setText(user.getAddress());
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDataFromObjectiveResume(String id){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query1 = reference.child("objective_Resume")
                .orderByKey().equalTo(id);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Objective user=singleSnapshot.getValue(Objective.class);
                        vObjective.setText(user.getObjective());
                    }}else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getDataFromHobbiesResume(String id){
        final ArrayList<String> input=new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query1 = reference.child("hobbies_Resume").child(id).limitToFirst(5);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Hobby user=singleSnapshot.getValue(Hobby.class);
                        input.add(user.getHobby_name());
                        //setimage later IA
                    }
                    vHobbies.setText(input.toString());
                }else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getDataFromInterestesResume(String id){
        final ArrayList<String> input=new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query1 = reference.child("interests_Resume").child(id).limitToFirst(5);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Interests user=singleSnapshot.getValue(Interests.class);
                        input.add(user.getInterestName());
                        //setimage later IA
                    }
                    vInterests.setText(input.toString());
                }else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getDataFromStrengthsResume(String id){
        final ArrayList<String> input=new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query1 = reference.child("strengths_Resume").child(id).limitToFirst(5);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Strengths user=singleSnapshot.getValue(Strengths.class);
                        input.add(user.getStrength_name());
                        //setimage later IA
                    }
                    vStrengths.setText(input.toString());
                }else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getDataFromAwardsResume(String id){
        final ArrayList<String> input=new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query1 = reference.child("awards_Resume").child(id).limitToFirst(5);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    //this loop will return a single result
                    for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                        Awards user=singleSnapshot.getValue(Awards.class);
                        input.add(user.getAward_name());
                        //setimage later IA
                    }
                    vAward.setText(input.toString());
                }else {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
