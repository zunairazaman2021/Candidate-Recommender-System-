package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class PhotoSignature {
    private String photisign_id;
    private String userImage;
    private String userSignature;
    private String cv_id;
    public PhotoSignature(){}

    public PhotoSignature(String userImage, String userSignature) {
        this.userImage = userImage;
        this.userSignature = userSignature;
    }

    public String getCv_id() {
        return cv_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }

    public void setPhotisign_id(String photisign_id) {
        this.photisign_id = photisign_id;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public String getPhotisign_id() {
        return photisign_id;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getUserSignature() {
        return userSignature;
    }
}
