package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class UploadPicture {
    String pic_id;
    String fotopath;
    public UploadPicture(){}

    public UploadPicture(String fotopath) {
        this.fotopath = fotopath;
    }

    public String getPic_id() {
        return pic_id;
    }

    public String getFotopath() {
        return fotopath;
    }

    public void setPic_id(String pic_id) {
        this.pic_id = pic_id;
    }

    public void setFotopath(String fotopath) {
        this.fotopath = fotopath;
    }
}
