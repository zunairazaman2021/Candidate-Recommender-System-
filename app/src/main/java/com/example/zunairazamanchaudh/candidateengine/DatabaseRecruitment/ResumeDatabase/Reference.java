package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Reference {
   private String reference_id,refname,refdesignation,refOrg,refEmail,refMobile;
   public Reference(){}

    public Reference(String refname, String refdesignation, String refOrg, String refEmail, String refMobile) {
        this.refname = refname;
        this.refdesignation = refdesignation;
        this.refOrg = refOrg;
        this.refEmail = refEmail;
        this.refMobile = refMobile;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public void setRefname(String refname) {
        this.refname = refname;
    }

    public void setRefdesignation(String refdesignation) {
        this.refdesignation = refdesignation;
    }

    public void setRefOrg(String refOrg) {
        this.refOrg = refOrg;
    }

    public void setRefEmail(String refEmail) {
        this.refEmail = refEmail;
    }

    public void setRefMobile(String refMobile) {
        this.refMobile = refMobile;
    }

    public String getReference_id() {
        return reference_id;
    }

    public String getRefname() {
        return refname;
    }

    public String getRefdesignation() {
        return refdesignation;
    }

    public String getRefOrg() {
        return refOrg;
    }

    public String getRefEmail() {
        return refEmail;
    }

    public String getRefMobile() {
        return refMobile;
    }
}
