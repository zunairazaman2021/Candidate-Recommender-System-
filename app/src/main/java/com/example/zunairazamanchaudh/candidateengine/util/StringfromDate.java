package com.example.zunairazamanchaudh.candidateengine.util;

import android.databinding.BindingConversion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringfromDate {
    @BindingConversion
    public static String convertDateToDisplayTime(Date date) {
        return new SimpleDateFormat("yyyy:MM:dd").format(date);
    }
}
