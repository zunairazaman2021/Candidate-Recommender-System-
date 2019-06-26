package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class PhotoSignature {
    String photisign_id;
    String userImage;
    String userSignature;
    public PhotoSignature(){}

    public PhotoSignature(String userImage, String userSignature) {
        this.userImage = userImage;
        this.userSignature = userSignature;
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
