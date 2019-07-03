package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class Post {
    String jobpost_id;
    String createdOn;
    String jobdescription,candidateDescription;
    String companyName,yourName,city,country,experience;
    String jobtype,genderPreference,phone,jobtitle,minDegree,maxDegree,skills,nationality;
    int noOfvacancie;
    double salary;
    Boolean status;
    String creator_id;
    public Post(){
    }
    public Post(String jobpost_id,String creator_id,String createdOn, String jobdescription, String candidateDescription,
                String companyName, String yourName, String city, String country, String experience,
                String jobtype, String genderPreference, String phone, String jobtitle,
                String minDegree, String maxDegree,
                String skills, String nationality, int noOfvacancie, double salary, Boolean status) {
        this.jobpost_id=jobpost_id;
        this.creator_id=creator_id;
        this.createdOn = createdOn;
        this.jobdescription = jobdescription;
        this.candidateDescription = candidateDescription;
        this.companyName = companyName;
        this.yourName = yourName;
        this.city = city;
        this.country = country;
        this.experience = experience;
        this.jobtype = jobtype;
        this.genderPreference = genderPreference;
        this.phone = phone;
        this.jobtitle = jobtitle;
        this.minDegree = minDegree;
        this.maxDegree = maxDegree;
        this.skills = skills;
        this.nationality = nationality;
        this.noOfvacancie = noOfvacancie;
        this.salary = salary;
        this.status = status;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public void setJobpost_id(String jobpost_id) {
        this.jobpost_id = jobpost_id;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public void setCandidateDescription(String candidateDescription) {
        this.candidateDescription = candidateDescription;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public void setMinDegree(String minDegree) {
        this.minDegree = minDegree;
    }

    public void setMaxDegree(String maxDegree) {
        this.maxDegree = maxDegree;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setNoOfvacancie(int noOfvacancie) {
        this.noOfvacancie = noOfvacancie;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getJobpost_id() {
        return jobpost_id;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public String getCandidateDescription() {
        return candidateDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getYourName() {
        return yourName;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getExperience() {
        return experience;
    }

    public String getJobtype() {
        return jobtype;
    }

    public String getGenderPreference() {
        return genderPreference;
    }

    public String getPhone() {
        return phone;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public String getMinDegree() {
        return minDegree;
    }

    public String getMaxDegree() {
        return maxDegree;
    }

    public String getSkills() {
        return skills;
    }

    public String getNationality() {
        return nationality;
    }

    public int getNoOfvacancie() {
        return noOfvacancie;
    }

    public double getSalary() {
        return salary;
    }

    public Boolean getStatus() {
        return status;
    }
}
