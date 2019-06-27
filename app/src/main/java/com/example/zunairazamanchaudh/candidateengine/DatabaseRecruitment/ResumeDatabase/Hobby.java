package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Hobby {
    private String hobby_id;
    private String hobby_name;
    private String cv_id;
    public Hobby(){}

    public Hobby(String hobby_name) {
        this.hobby_name = hobby_name;
    }

    public String getHobby_id() {
        return hobby_id;
    }

    public String getHobby_name() {
        return hobby_name;
    }

    public void setHobby_id(String hobby_id) {
        this.hobby_id = hobby_id;
    }

    public void setHobby_name(String hobby_name) {
        this.hobby_name = hobby_name;
    }

    public String getCv_id() {
        return cv_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }
}
