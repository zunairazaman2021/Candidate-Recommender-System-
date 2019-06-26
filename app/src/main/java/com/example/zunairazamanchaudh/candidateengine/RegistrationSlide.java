package com.example.zunairazamanchaudh.candidateengine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationSlide extends FragmentActivity{


    public RegistrationSlide() {
        // Required empty public constructor
    }


   // @Override
   /* public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_slide, container, false);
    }*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registration_slide);

        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, new Workdetails())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();


        // Permission Step

       /* addFragment(new PermissionStep.Builder().setTitle(getString(R.string.permission_title))
                .setContent(getString(R.string.permission_detail))
                .setBackgroundColor(Color.parseColor("#FF0957"))
                .setDrawable(R.drawable.ss_1)
                .setSummary(getString(R.string.continue_and_learn))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());
*/
    }

    public void currentFragmentPosition(int i) {

    }

}
