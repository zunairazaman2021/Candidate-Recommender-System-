package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class JobPreference {
    private String preference_id;
    private String designation;
    private String country;
    private String city;
    private String salary;
    private String industry;
    private String employmenttype;
    private String careerlevel;
    private String jobrole;
    private String user_id;
public JobPreference(){}

    public JobPreference(String designation, String country, String city, String salary, String industry, String employmenttype, String careerlevel, String jobrole, String user_id) {
        this.designation = designation;
        this.country = country;
        this.city = city;
        this.salary = salary;
        this.industry = industry;
        this.employmenttype = employmenttype;
        this.careerlevel = careerlevel;
        this.jobrole = jobrole;
        this.user_id = user_id;
    }

    public void setEmploymenttype(String employmenttype) {
        this.employmenttype = employmenttype;
    }

    public void setCareerlevel(String careerlevel) {
        this.careerlevel = careerlevel;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public String getEmploymenttype() {
        return employmenttype;
    }

    public String getCareerlevel() {
        return careerlevel;
    }

    public String getJobrole() {
        return jobrole;
    }

    public String getPreference_id() {
        return preference_id;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getSalary() {
        return salary;
    }

    public String getIndustry() {
        return industry;
    }

    public String getUser_id() {
        return user_id;
    }


    public void setPreference_id(String preference_id) {
        this.preference_id = preference_id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
