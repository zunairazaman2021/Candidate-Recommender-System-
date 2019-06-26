package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Interests {
    String interest_id;
    String InterestName;
    public Interests(){
    }

    public Interests(String interestName) {
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
}
