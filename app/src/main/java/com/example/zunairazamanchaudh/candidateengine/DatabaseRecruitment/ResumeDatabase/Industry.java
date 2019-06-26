package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Industry {
    String industry_id;
    String Industry_name;
    public Industry(){
    }

    public Industry(String industry_name) {
        Industry_name = industry_name;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public String getIndustry_name() {
        return Industry_name;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    public void setIndustry_name(String industry_name) {
        Industry_name = industry_name;
    }
}
