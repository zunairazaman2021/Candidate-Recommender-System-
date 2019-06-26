package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter;

import android.os.Parcel;
import android.os.Parcelable;

public class JobPostedview implements Parcelable{
    int serialno;
    int creator;
    //review Skill attribute type linked with mongo
    String jobTitle,jobdescription,skills,careerLevel;
    int noOfVacancies;
     String street,country,state,city,minDegree, maxDegree;
     int yearofExperience;
    double salary;
    String genderPreferences,candidateDescription;

    public JobPostedview(int serialno,int creator, String jobTitle, String jobdescription, String skills, String careerLevel, int noOfVacancies, String street, String country, String state, String city, String minDegree, String maxDegree, int yearofExperience, double salary, String genderPreferences, String candidateDescription) {
        this.serialno=serialno;
        this.creator = creator;
        this.jobTitle = jobTitle;
        this.jobdescription = jobdescription;
        this.skills = skills;
        this.careerLevel = careerLevel;
        this.noOfVacancies = noOfVacancies;
        this.street = street;
        this.country = country;
        this.state = state;
        this.city = city;
        this.minDegree = minDegree;
        this.maxDegree = maxDegree;
        this.yearofExperience = yearofExperience;
        this.salary = salary;
        this.genderPreferences = genderPreferences;
        this.candidateDescription = candidateDescription;
    }

    protected JobPostedview(Parcel in) {
        serialno=in.readInt();
        creator = in.readInt();
        jobTitle = in.readString();
        jobdescription = in.readString();
        skills = in.readString();
        careerLevel = in.readString();
        noOfVacancies = in.readInt();
        street = in.readString();
        country = in.readString();
        state = in.readString();
        city = in.readString();
        minDegree = in.readString();
        maxDegree = in.readString();
        yearofExperience = in.readInt();
            salary = in.readDouble();
        genderPreferences = in.readString();
        candidateDescription = in.readString();
    }

    public static final Creator<JobPostedview> CREATOR = new Creator<JobPostedview>() {
        @Override
        public JobPostedview createFromParcel(Parcel in) {
            return new JobPostedview(in);
        }

        @Override
        public JobPostedview[] newArray(int size) {
            return new JobPostedview[size];
        }
    };

    public int getSerialno() {
        return serialno;
    }

    public static Creator<JobPostedview> getCREATOR() {
        return CREATOR;
    }

    public int getCreator() {
        return creator;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public String getSkills() {
        return skills;
    }

    public String getCareerLevel() {
        return careerLevel;
    }

    public int getNoOfVacancies() {
        return noOfVacancies;
    }

    public String getStreet() {
        return street;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getMinDegree() {
        return minDegree;
    }

    public String getMaxDegree() {
        return maxDegree;
    }

    public int getYearofExperience() {
        return yearofExperience;
    }

    public double getSalary() {
        return salary;
    }

    public String getGenderPreferences() {
        return genderPreferences;
    }

    public String getCandidateDescription() {
        return candidateDescription;
    }
    //setters

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    public void setNoOfVacancies(int noOfVacancies) {
        this.noOfVacancies = noOfVacancies;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMinDegree(String minDegree) {
        this.minDegree = minDegree;
    }

    public void setMaxDegree(String maxDegree) {
        this.maxDegree = maxDegree;
    }

    public void setYearofExperience(int yearofExperience) {
        this.yearofExperience = yearofExperience;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setGenderPreferences(String genderPreferences) {
        this.genderPreferences = genderPreferences;
    }

    public void setCandidateDescription(String candidateDescription) {
        this.candidateDescription = candidateDescription;
    }

    public void setSerialno(int serialno) {
        this.serialno = serialno;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(serialno);
        dest.writeInt(creator);
        dest.writeString(jobTitle);
        dest.writeString(jobdescription);
        dest.writeString(skills);
        dest.writeString(careerLevel);
        dest.writeInt(noOfVacancies);
        dest.writeString(street);
        dest.writeString(country);
        dest.writeString(state);
        dest.writeString(city);
        dest.writeString(minDegree);
        dest.writeString(maxDegree);
        dest.writeInt(yearofExperience);
        dest.writeDouble(salary);
        dest.writeString(genderPreferences);
        dest.writeString(candidateDescription);
    }
}
