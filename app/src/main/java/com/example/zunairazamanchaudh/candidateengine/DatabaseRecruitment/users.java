package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class users {
    private String firstname;
    private String lastname;
    private String profile_image;
    private String security_level;
    private String dob;
    private String phone;
    private String email;
    private String nationality;
    private String user_id;

    public users(String firstname, String lastname, String profile_image, String security_level, String dob, String phone,String email,String nationality) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.profile_image = profile_image;
        this.security_level = security_level;
        this.dob = dob;
        this.phone=phone;
        this.email=email;
        this.nationality=nationality;
    }


    public users(){

    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getUser_id() {
        return user_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "users{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", security_level='" + security_level + '\'' +
                ", dob='" + dob + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
