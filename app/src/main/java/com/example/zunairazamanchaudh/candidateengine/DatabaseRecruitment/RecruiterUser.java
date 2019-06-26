package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class RecruiterUser {
    private String firstname;
    private String lastname;
    private String profile_image;
    private String phone;
    private String email;
    private String user_id;
public RecruiterUser(){

}
    public RecruiterUser(String firstname, String lastname, String profile_image, String phone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.profile_image = profile_image;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
