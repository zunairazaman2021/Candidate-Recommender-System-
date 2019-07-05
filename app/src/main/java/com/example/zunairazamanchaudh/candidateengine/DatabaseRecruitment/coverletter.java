package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class coverletter {
    String user_id;
    String recruitername,companyname,date,address,body;
    public coverletter(){}

    public coverletter(String user_id, String recruitername, String companyname, String date, String address, String body) {
        this.user_id = user_id;
        this.recruitername = recruitername;
        this.companyname = companyname;
        this.date = date;
        this.address = address;
        this.body = body;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setRecruitername(String recruitername) {
        this.recruitername = recruitername;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getRecruitername() {
        return recruitername;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getBody() {
        return body;
    }
}
