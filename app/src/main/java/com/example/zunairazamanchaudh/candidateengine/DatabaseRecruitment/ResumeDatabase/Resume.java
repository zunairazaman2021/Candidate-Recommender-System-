package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Resume {
    private String cv_id;
    private String cv_Title;
    private String user_id;
    public  Resume(){}

    public Resume(String cv_Title, String user_id) {
        this.cv_Title = cv_Title;
        this.user_id = user_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }

    public void setCv_Title(String cv_Title) {
        this.cv_Title = cv_Title;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCv_id() {
        return cv_id;
    }

    public String getCv_Title() {
        return cv_Title;
    }

    public String getUser_id() {
        return user_id;
    }
}
