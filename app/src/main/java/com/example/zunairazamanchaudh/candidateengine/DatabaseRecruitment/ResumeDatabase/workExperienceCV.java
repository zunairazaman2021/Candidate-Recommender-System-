package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

import java.util.Date;

public class workExperienceCV {
    private String experience_id;
    private String organization,designation,role;
    private boolean emploaymentStatus;
    private Date FromExp;
    private Date ToExp;
    public workExperienceCV(){
    }

    public workExperienceCV(String organization, String designation, String role,
                            boolean emploaymentStatus, Date fromExp, Date toExp) {
        this.organization = organization;
        this.designation = designation;
        this.role = role;
        this.emploaymentStatus = emploaymentStatus;
        FromExp = fromExp;
        ToExp = toExp;
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

    public void setFromExp(Date fromExp) {
        FromExp = fromExp;
    }

    public void setToExp(Date toExp) {
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

    public Date getFromExp() {
        return FromExp;
    }

    public Date getToExp() {
        return ToExp;
    }
}
