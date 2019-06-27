package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

import java.util.Date;

public class workExperienceCV {
    private String experience_id;
    private String organization,designation,role;
    private boolean emploaymentStatus;
    private String FromExp;
    private String ToExp;
    private String cv_id;
    public workExperienceCV(){
    }

    public workExperienceCV(String organization, String designation, String role,
                            boolean emploaymentStatus, String fromExp, String toExp) {
        this.organization = organization;
        this.designation = designation;
        this.role = role;
        this.emploaymentStatus = emploaymentStatus;
        FromExp = fromExp;
        ToExp = toExp;
    }

    public String getCv_id() {
        return cv_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }

    public void setExperience_id(String experience_id) {
        this.experience_id = experience_id;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmploaymentStatus(boolean emploaymentStatus) {
        this.emploaymentStatus = emploaymentStatus;
    }

    public void setFromExp(String fromExp) {
        FromExp = fromExp;
    }

    public void setToExp(String toExp) {
        ToExp = toExp;
    }

    public String getExperience_id() {
        return experience_id;
    }

    public String getOrganization() {
        return organization;
    }

    public String getDesignation() {
        return designation;
    }

    public String getRole() {
        return role;
    }

    public boolean isEmploaymentStatus() {
        return emploaymentStatus;
    }

    public String getFromExp() {
        return FromExp;
    }

    public String getToExp() {
        return ToExp;
    }
}
