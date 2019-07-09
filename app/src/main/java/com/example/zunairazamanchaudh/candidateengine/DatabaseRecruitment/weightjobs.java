package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class weightjobs {

    private String job_descriptions,job_location,job_name,job_url,jobsearchqueryid,posted_on_date;
    private long weight;

    public weightjobs(String job_descriptions, String job_location, String job_name,
                                      String job_url, String jobsearchqueryid, String posted_on_date,
                                      long weight) {
        this.job_descriptions = job_descriptions;
        this.job_location = job_location;
        this.job_name = job_name;
        this.job_url = job_url;
        this.jobsearchqueryid = jobsearchqueryid;
        this.posted_on_date = posted_on_date;
        this.weight = weight;
    }

    public void setJob_descriptions(String job_descriptions) {
        this.job_descriptions = job_descriptions;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public void setJob_url(String job_url) {
        this.job_url = job_url;
    }

    public void setJobsearchqueryid(String jobsearchqueryid) {
        this.jobsearchqueryid = jobsearchqueryid;
    }

    public void setPosted_on_date(String posted_on_date) {
        this.posted_on_date = posted_on_date;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getJob_descriptions() {
        return job_descriptions;
    }

    public String getJob_location() {
        return job_location;
    }

    public String getJob_name() {
        return job_name;
    }

    public String getJob_url() {
        return job_url;
    }

    public String getJobsearchqueryid() {
        return jobsearchqueryid;
    }

    public String getPosted_on_date() {
        return posted_on_date;
    }

    public weightjobs(){}
}
