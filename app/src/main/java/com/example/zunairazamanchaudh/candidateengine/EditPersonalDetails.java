package com.example.zunairazamanchaudh.candidateengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditPersonalDetails extends AppCompatActivity {
    static EditPersonalDetails newInstance() {
        return new EditPersonalDetails();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_details);
    }
}
