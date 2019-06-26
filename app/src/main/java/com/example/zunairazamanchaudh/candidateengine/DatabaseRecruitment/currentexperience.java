package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class currentexperience {
    private String exp_id;
    private String user_id;
    private String organization;
    private String designation;
    private String jobrole;
    private String jobDuration;
    private String monthlysalary;

    public currentexperience(String user_id, String organization, String designation, String jobrole, String jobDuration, String monthlysalary) {
        this.user_id = user_id;
        this.organization = organization;
        this.designation = designation;
        this.jobrole = jobrole;
        this.jobDuration = jobDuration;
        this.monthlysalary = monthlysalary;
    }
public currentexperience(){}
    public String getExp_id() {
        return exp_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getOrganization() {
        return organization;
    }

    public String getDesignation() {
        return designation;
    }

    public String getJobrole() {
        return jobrole;
    }

    public String getJobDuration() {
        return jobDuration;
    }

    public String getMonthlysalary() {
        return monthlysalary;
    }

    public void setExp_id(String exp_id) {
        this.exp_id = exp_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public void setJobDuration(String jobDuration) {
        this.jobDuration = jobDuration;
    }

    public void setMonthlysalary(String monthlysalary) {
        this.monthlysalary = monthlysalary;
    }
}
