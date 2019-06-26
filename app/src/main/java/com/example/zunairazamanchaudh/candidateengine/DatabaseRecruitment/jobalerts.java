package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class jobalerts {
    private String jobalertid;
    private String alertname;
    private String keywords;
    private String country;
    private String city;
    private String jobrole;
    private String user_id;
public jobalerts(){}
    public jobalerts( String alertname, String keywords, String country, String city, String jobrole, String user_id) {
        this.alertname = alertname;
        this.keywords = keywords;
        this.country = country;
        this.city = city;
        this.jobrole = jobrole;
        this.user_id = user_id;
    }

    public String getJobalertid() {
        return jobalertid;
    }

    public String getAlertname() {
        return alertname;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getJobrole() {
        return jobrole;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setJobalertid(String jobalertid) {
        this.jobalertid = jobalertid;
    }

    public void setAlertname(String alertname) {
        this.alertname = alertname;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
