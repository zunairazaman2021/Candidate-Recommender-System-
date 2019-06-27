package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Personalinfo {
   private String persnlInfo_id,nationality,maritalstatus,dob,custom;
    public Personalinfo(){}

    public Personalinfo(String nationality, String maritalstatus, String dob,String custom) {
        this.nationality = nationality;
        this.maritalstatus = maritalstatus;
        this.dob = dob;
        this.custom=custom;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public void setPersnlInfo_id(String persnlInfo_id) {
        this.persnlInfo_id = persnlInfo_id;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPersnlInfo_id() {
        return persnlInfo_id;
    }

    public String getNationality() {
        return nationality;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public String getDob() {
        return dob;
    }
}
