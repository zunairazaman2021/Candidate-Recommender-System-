package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Industry {
    private String industry_id;
    private String Industry_name;
    private String cv_id;
    public Industry(){
    }

    public Industry(String industry_name) {
        this.Industry_name = industry_name;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    public void setIndustry_name(String industry_name) {
        Industry_name = industry_name;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public String getIndustry_name() {
        return Industry_name;
    }

    public String getCv_id() {
        return cv_id;
    }
}
