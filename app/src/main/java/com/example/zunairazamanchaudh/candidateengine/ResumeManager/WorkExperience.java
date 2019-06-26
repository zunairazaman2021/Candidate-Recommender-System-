package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import java.util.Date;

public class WorkExperience {
    private String organization,designation,role;
    private boolean emploaymentStatus;
    private Date FromExp;
    private Date ToExp;

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

    public WorkExperience(String organization, String designation, String role, boolean emploaymentStatus, Date fromExp, Date toExp) {
        this.organization = organization;
        this.designation = designation;
        this.role = role;
        this.emploaymentStatus = emploaymentStatus;
        FromExp = fromExp;
        ToExp = toExp;

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
