package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

import java.io.Serializable;

public class JobApplication {

    private String application_id;
    private String recruiter_id;
    private String cv_id;//cvid, userid,coverletterid
    private String applicationstatus;
    private String applicationDate;
  //user data
    private String firstname;
    private String lastname;
    private String profile_image;
    private String security_level;
    private String dob;
    private String phone;
    private String email;
    private String nationality;
    private String useridd;
  //jobData
   private String job_id;
   private String companyName,yourName,jcity,jcountry,status;
  //useraddress
    private String city;
    private String country;
    private String state;
    private String zipcode;

    public JobApplication(){
    }

    public JobApplication(String application_id, String recruiter_id, String cv_id, String applicationstatus,
                          String applicationDate, String firstname, String lastname, String profile_image,
                          String security_level, String dob, String phone, String email, String nationality,
                          String useridd, String job_id, String companyName, String yourName,
                          String jcity, String jcountry, String city, String country, String state, String zipcode,String status) {
        this.application_id = application_id;
        this.status=status;
        this.recruiter_id = recruiter_id;
        this.cv_id = cv_id;
        this.applicationstatus = applicationstatus;
        this.applicationDate = applicationDate;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profile_image = profile_image;
        this.security_level = security_level;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;
        this.useridd = useridd;
        this.job_id = job_id;
        this.companyName = companyName;
        this.yourName = yourName;
        this.jcity = jcity;
        this.jcountry = jcountry;
        this.city = city;
        this.country = country;
        this.state = state;
        this.zipcode = zipcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJcity(String jcity) {
        this.jcity = jcity;
    }

    public void setJcountry(String jcountry) {
        this.jcountry = jcountry;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getJcity() {
        return jcity;
    }

    public String getJcountry() {
        return jcountry;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public void setSecurity_level(String security_level) {
        this.security_level = security_level;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setUseridd(String useridd) {
        this.useridd = useridd;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getSecurity_level() {
        return security_level;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUseridd() {
        return useridd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getYourName() {
        return yourName;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getJob_id() {
        return job_id;
    }

    public String getApplication_id() {
        return application_id;
    }

    public String getRecruiter_id() {
        return recruiter_id;
    }

    public String getCv_id() {
        return cv_id;
    }

    public String getApplicationstatus() {
        return applicationstatus;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public void setRecruiter_id(String recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }

    public void setApplicationstatus(String applicationstatus) {
        this.applicationstatus = applicationstatus;
    }
}
