package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.Jobs;
import com.example.zunairazamanchaudh.candidateengine.LoginJobSeeker;
import com.example.zunairazamanchaudh.candidateengine.R;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.IMainActivityPostedJob;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.PostedJobs2Fragment;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.PostedJobsFragment;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.JobBoardFolder.WelcomeAddJob;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.CVsview;
import com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter.JobPostedview;
import com.example.zunairazamanchaudh.candidateengine.WelcomeCandidate;
import com.example.zunairazamanchaudh.candidateengine.databinding.ActivityWelcomeRecruiterBinding;
import com.example.zunairazamanchaudh.candidateengine.util.UniversalImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nostra13.universalimageloader.core.ImageLoader;

public class WelcomeRecruiter extends AppCompatActivity implements IMainActivityPostedJob, IMainActivityRecruiter, NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    ActivityWelcomeRecruiterBinding mBinding;
    private static final String TAG = "WelcomeRecruiter";

    //Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   initImageLoader();
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_welcome_recruiter);
        toolbar = (Toolbar) findViewById(R.id.toolbarRecruiter);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Recruiter Recommeder");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutRecruiter);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setupFirebaseAuth();
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    // attaching bottom sheet behaviour - hide / show on scroll
    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
    layoutParams.setBehavior(new BottomNavigationBehavior());

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_viewRecruiter);
        navigationView.setNavigationItemSelectedListener(this);
        setFragmentRecruiter(new CVSearchRecruiter());


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment2;
            FragmentTransaction ft2;
            switch (item.getItemId()) {
                case R.id.navigation_searchcv:
                    toolbar.setTitle("Search CV");
                  fragment2=new CVDisplay2();
                    ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.main_contentRecruiter, fragment2);
                    ft2.addToBackStack(null);
                    ft2.commit();
                    return true;
                case R.id.navigation_jobsposted:
                    toolbar.setTitle("Jobs");
                //   fragment2=new PostedJobsFragment();
                  fragment2=new PostedJobs2Fragment();
                    ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.main_contentRecruiter, fragment2);
                    ft2.addToBackStack(null);
                    ft2.commit();
                    return true;
                case R.id.navigation_postJob:
                    toolbar.setTitle("Add Job");
                    fragment2=new WelcomeAddJob();
                    ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.main_contentRecruiter, fragment2);
                    ft2.addToBackStack(null);
                    ft2.commit();
                    return true;
                case R.id.navigation_cvfolder:
                    toolbar.setTitle("CV Folder");
                   fragment2=new CVFolder();
                    ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.main_contentRecruiter, fragment2);
                    ft2.addToBackStack(null);
                    ft2.commit();

                    return true;

                case R.id.navigation_profile:
                    toolbar.setTitle("Recruiter Profile");
                    fragment2=new RecruiterProfileFragment();
                    ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.main_contentRecruiter, fragment2);
                    ft2.addToBackStack(null);
                    ft2.commit();

                   return true;
            }
            return false;
        }
    };


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutRecruiter);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_recruiterr, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_signoutR:
                signOut();
                return true;
            case R.id.action_passwordchangeR:
                return true;
            case R.id.action_settingsRecruiter:
                //    Intent intent = new Intent(SignedInActivity.this, SettingsActivity.class);
                //    startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_dashboardRecruiter) {
            // Handle the camera action
            setFragmentRecruiter(new Jobs());
        } else if (id == R.id.jobsRecruiter) {
            Fragment fragment=new CVDisplay();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_contentRecruiter, fragment);
            ft.commit();

        } else if (id == R.id.CVRecruiter) {
            /*Fragment fragment=new WelcomeAddJob();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_contentRecruiter, fragment);
            ft.commit();
*/

        } else if (id == R.id.searchCVRecruiter) {

            Fragment fragment=new PostedJobsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_contentRecruiter, fragment);
            ft.commit();


        } else if (id == R.id.nav_shareRecruiter) {

            Fragment fragment=new PostedJobVacancyDetailView();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_contentRecruiter, fragment);
            ft.commit();

        } else if (id == R.id.nav_sendRecruiter) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutRecruiter);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setFragmentRecruiter(Fragment fragment){
        if(fragment!=null){
            FragmentTransaction fr=getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.main_contentRecruiter,fragment);
            fr.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutRecruiter);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void inflateViewCVFragment(CVsview cvView) {
        UserProfileView fragment = new UserProfileView();
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.intent_cv_user), cvView);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_contentRecruiter, fragment, getString(R.string.fragment_filter_cv));
        transaction.addToBackStack(getString(R.string.fragment_filter_cv));
        transaction.commit();

    }

    @Override
    public void inflateViewJobPostedFragment(JobPostedview jobPostedview) {

    }

    private void checkAuthenticationState(){
        Log.d(TAG, "checkAuthenticationState: checking authentication state.");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            Log.d(TAG, "checkAuthenticationState: user is null, navigating back to login screen.");

            Intent intent = new Intent(WelcomeRecruiter.this, LoginJobSeeker.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else{
            Log.d(TAG, "checkAuthenticationState: user is authenticated.");
        }
    }


    /**
     * Sign out the current user
     */
    private void signOut(){
        Log.d(TAG, "signOut: signing out");
        FirebaseAuth.getInstance().signOut();
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

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Intent intent = new Intent(WelcomeRecruiter.this, LoginJobSeeker.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

}
