package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Interests {
    private String interest_id;
    private String InterestName;
    private String cv_id;
    public Interests(){
    }

    public Interests(String interestName,String cv_id) {
        this.cv_id=cv_id;
        InterestName = interestName;
    }

    public String getInterest_id() {
        return interest_id;
    }

    public String getInterestName() {
        return InterestName;
    }

    public void setInterest_id(String interest_id) {
        this.interest_id = interest_id;
    }

    public void setInterestName(String interestName) {
        InterestName = interestName;
    }

    public String getCv_id() {
        return cv_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }
}
