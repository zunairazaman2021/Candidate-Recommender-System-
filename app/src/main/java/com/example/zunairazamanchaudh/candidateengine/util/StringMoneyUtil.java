package com.example.zunairazamanchaudh.candidateengine.util;

import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.R;

public class StringMoneyUtil {

    public static String getSalaryString(double salary){
        return (String.valueOf(salary));
    }
    public static String salaryIntoToString(double value){

        return (String.valueOf(value)+" daily budget");
    }

   }
