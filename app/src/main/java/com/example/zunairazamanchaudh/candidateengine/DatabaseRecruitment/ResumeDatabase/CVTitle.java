package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class CVTitle {
    private String cv_id;
    private String cv_title;
    private String createdon;
    private String firstname;
    private String lastname;
    private String profile_image;
    private String security_level;
    private String dob;
    private String phone;
    private String email;
    private String nationality;
    private String city;
    private String country;
    private String state;
    private String zipcode;
    private String useridd;
    public CVTitle(){}

    public CVTitle(String cv_title, String createdon, String firstname,
                   String lastname, String profile_image, String security_level,
                   String dob, String phone, String email,
                   String nationality, String city, String country, String state, String zipcode,String useridd) {
        this.cv_title = cv_title;
        this.createdon = createdon;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profile_image = profile_image;
        this.security_level = security_level;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;
        this.city = city;
        this.country = country;
        this.state = state;
        this.zipcode = zipcode;
        this.useridd=useridd;
    }

    public String getUseridd() {
        return useridd;
    }

    public void setUseridd(String useridd) {
        this.useridd = useridd;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
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

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }

    public void setCv_title(String cv_title) {
        this.cv_title = cv_title;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public String getCv_id() {
        return cv_id;
    }

    public String getCv_title() {
        return cv_title;
    }

    public String getCreatedon() {
        return createdon;
    }
}
