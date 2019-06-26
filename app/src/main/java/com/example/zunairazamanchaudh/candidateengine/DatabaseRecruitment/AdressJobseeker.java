package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class AdressJobseeker {
   private String adressid;
   private String country;
   private String city;
   private String state;
   private int zipcode;
  private   String uid;
public AdressJobseeker(){}
    public AdressJobseeker( String country, String city, String state, int zipcode, String uid) {
        this.country = country;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.uid = uid;
    }

    public String getAdressid() {
        return adressid;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getUid() {
        return uid;
    }

    public void setAdressid(String adressid) {
        this.adressid = adressid;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}