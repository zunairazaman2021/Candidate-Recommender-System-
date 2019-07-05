package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class JobApplication {
    String job_id;
    String application_id;
    String recruiter_id;
    String cv_id;//cvid, userid,coverletterid
    String applicationstatus;
    public JobApplication(){
    }

    public JobApplication(String job_id, String application_id, String recruiter_id, String cv_id, String applicationstatus) {
        this.job_id = job_id;
        this.application_id = application_id;
        this.recruiter_id = recruiter_id;
        this.cv_id = cv_id;
        this.applicationstatus = applicationstatus;
    }

    public String getJob_id() {
        return job_id;
    }

    public String getApplication_id() {
        return application_id;
    }

    public String getRecruiter_id() {
        return recruiter_id;
    }

    public String getCv_id() {
        return cv_id;
    }

    public String getApplicationstatus() {
        return applicationstatus;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public void setRecruiter_id(String recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }

    public void setApplicationstatus(String applicationstatus) {
        this.applicationstatus = applicationstatus;
    }
}
