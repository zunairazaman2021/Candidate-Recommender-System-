package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class experience {
    private String experience_id;
    private String organization;
    private String designation;
    private String empStatus;
    private String expFrom;
    private String expTo;
    private String role;
    private String user_id;

    public experience(String organization, String designation, String empStatus, String expFrom, String expTo, String role, String user_id) {
        this.organization = organization;
        this.designation = designation;
        this.empStatus = empStatus;
        this.expFrom = expFrom;
        this.expTo = expTo;
        this.role = role;
        this.user_id = user_id;
    }
    public experience(){}

    public String getExperience_id() {
        return experience_id;
    }

    public String getOrganization() {
        return organization;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public String getExpFrom() {
        return expFrom;
    }

    public String getExpTo() {
        return expTo;
    }

    public String getRole() {
        return role;
    }

    public String getUser_id() {
        return user_id;
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

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public void setExpFrom(String expFrom) {
        this.expFrom = expFrom;
    }

    public void setExpTo(String expTo) {
        this.expTo = expTo;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
