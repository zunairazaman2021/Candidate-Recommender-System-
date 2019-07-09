package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class JobSearchQuery {
    private String career_level,company,experience_level_required,gender,job_city,job_industry,job_title,job_type;
 private int salary;
 public JobSearchQuery(){}

    public JobSearchQuery(String career_level, String company, String experience_level_required, String gender, String job_city, String job_industry, String job_title, String job_type, int salary) {
        this.career_level = career_level;
        this.company = company;
        this.experience_level_required = experience_level_required;
        this.gender = gender;
        this.job_city = job_city;
        this.job_industry = job_industry;
        this.job_title = job_title;
        this.job_type = job_type;
        this.salary = salary;
    }

    public void setCareer_level(String career_level) {
        this.career_level = career_level;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setExperience_level_required(String experience_level_required) {
        this.experience_level_required = experience_level_required;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setJob_city(String job_city) {
        this.job_city = job_city;
    }

    public void setJob_industry(String job_industry) {
        this.job_industry = job_industry;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCareer_level() {
        return career_level;
    }

    public String getCompany() {
        return company;
    }

    public String getExperience_level_required() {
        return experience_level_required;
    }

    public String getGender() {
        return gender;
    }

    public String getJob_city() {
        return job_city;
    }

    public String getJob_industry() {
        return job_industry;
    }

    public String getJob_title() {
        return job_title;
    }

    public String getJob_type() {
        return job_type;
    }

    public int getSalary() {
        return salary;
    }
}
