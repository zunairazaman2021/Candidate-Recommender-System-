package com.example.zunairazamanchaudh.candidateengine.RecruiterMainScreen.modelRecruiter;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.util.Date;

public class CVsview implements Parcelable{
    private static final String TAG = "CVsview";
    int serialnumber;
    String cvTitle;
    //contactInfo
    String name,country,city,email,phone;
    //personalInfo
    String maritalstatus,nationality;
    Date dob;
    //education
    String course,college,grade,passyear;
    //experience
    String organization,designation,jobRole;
            Date durationFrom,DurationTo;
    //projects
    String projectTitle,projectDescription,projectRole;
    String projectDuration;
    int projectTeam;
    String skill;
    String interests;
    String industrialExposure;
    String awards;
    String hobby;
    //reference
    String refName,refDesignation,refOrganization,refEmail,refMobile;
    private int image;

    public String getCvTitle() {
        return cvTitle;
    }

    public void setCvTitle(String cvTitle) {
        this.cvTitle = cvTitle;
    }

    public static Creator<CVsview> getCREATOR() {
        return CREATOR;
    }

    public int getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(int serialnumber) {
        this.serialnumber = serialnumber;
    }

    protected CVsview(Parcel in) {
        serialnumber=in.readInt();
        cvTitle=in.readString();
        name = in.readString(); country = in.readString(); city=in.readString(); email=in.readString(); phone=in.readString();
        maritalstatus=in.readString();        nationality=in.readString();
        dob=new Date(in.readLong());
        course=in.readString();   college=in.readString(); grade=in.readString(); passyear=in.readString();
        organization= in.readString(); designation= in.readString(); jobRole=in.readString();
        durationFrom=new Date(in.readLong()); DurationTo=new Date(in.readLong());
        projectTitle= in.readString(); projectDescription= in.readString(); projectRole= in.readString();
        projectDuration= in.readString();
        projectTeam=in.readInt();
        skill= in.readString();interests= in.readString(); industrialExposure= in.readString();
        awards= in.readString(); hobby= in.readString();
        refName= in.readString(); refDesignation= in.readString(); refOrganization= in.readString(); refEmail= in.readString();
        refMobile= in.readString();
        image = in.readInt();
    }

    public static final Creator<CVsview> CREATOR = new Creator<CVsview>() {
        @Override
        public CVsview createFromParcel(Parcel in) {
            return new CVsview(in);
        }

        @Override
        public CVsview[] newArray(int size) {
            return new CVsview[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(serialnumber);
        dest.writeString(cvTitle);
        dest.writeString(name);
        dest.writeString(country);
        dest.writeString(city);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(maritalstatus);
        dest.writeString(nationality);
        dest.writeLong(dob.getTime());
        dest.writeString(course);
        dest.writeString(college);
        dest.writeString(grade);
        dest.writeString(passyear);
        dest.writeString(organization);
        dest.writeString(designation);
        dest.writeString(jobRole);
        dest.writeLong(durationFrom.getTime());
        dest.writeLong(DurationTo.getTime());
        dest.writeString(projectTitle);
        dest.writeString(projectDescription);
        dest.writeString(projectRole);
        dest.writeString(projectDuration);
        dest.writeInt(projectTeam);
        dest.writeString(skill);
        dest.writeString(interests);
        dest.writeString(industrialExposure);
        dest.writeString(awards);
        dest.writeString(hobby);
        dest.writeString(refName);
        dest.writeString(refDesignation);
        dest.writeString(refEmail);
        dest.writeString(refMobile);
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    //getters methods
    public static String getTAG() {
        return TAG;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getDob() {
        return dob;
    }

    public String getCourse() {
        return course;
    }

    public String getCollege() {
        return college;
    }

    public String getGrade() {
        return grade;
    }

    public String getPassyear() {
        return passyear;
    }

    public String getOrganization() {
        return organization;
    }

    public String getDesignation() {
        return designation;
    }

    public String getJobRole() {
        return jobRole;
    }

    public Date getDurationFrom() {
        return durationFrom;
    }

    public Date getDurationTo() {
        return DurationTo;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectRole() {
        return projectRole;
    }


    public int getProjectTeam() {
        return projectTeam;
    }

    public String getSkill() {
        return skill;
    }

    public String getInterests() {
        return interests;
    }

    public String getIndustrialExposure() {
        return industrialExposure;
    }

    public String getAwards() {
        return awards;
    }

    public String getHobby() {
        return hobby;
    }

    public String getRefName() {
        return refName;
    }

    public String getRefDesignation() {
        return refDesignation;
    }

    public String getRefOrganization() {
        return refOrganization;
    }

    public String getRefEmail() {
        return refEmail;
    }

    public String getRefMobile() {
        return refMobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPassyear(String passyear) {
        this.passyear = passyear;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public void setDurationFrom(Date durationFrom) {
        this.durationFrom = durationFrom;
    }

    public void setDurationTo(Date durationTo) {
        DurationTo = durationTo;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }


    public void setProjectTeam(int projectTeam) {
        this.projectTeam = projectTeam;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setIndustrialExposure(String industrialExposure) {
        this.industrialExposure = industrialExposure;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public void setRefDesignation(String refDesignation) {
        this.refDesignation = refDesignation;
    }

    public void setRefOrganization(String refOrganization) {
        this.refOrganization = refOrganization;
    }

            public void setRefEmail(String refEmail) {
            this.refEmail = refEmail;
         }

         public String getProjectDuration() {
          return projectDuration;
    }

       public void setRefMobile(String refMobile) {
        this.refMobile = refMobile;
    }


       public  CVsview(){

       }
      public CVsview(int serialnumber,String cvTitle,String name, String country, String city, String email, String phone, String maritalstatus, String nationality, Date dob, String course, String college, String grade, String passyear, String organization, String designation, String jobRole, Date durationFrom, Date durationTo, String projectTitle, String projectDescription, String projectRole, String projectDuration, int projectTeam, String skill, String interests, String industrialExposure, String awards, String hobby, String refName,
                   String refDesignation, String refOrganization, String refEmail, String refMobile, int image) {
        this.serialnumber=serialnumber;
        this.cvTitle=cvTitle;
        this.name = name;
        this.country = country;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.maritalstatus = maritalstatus;
        this.nationality = nationality;
        this.dob = dob;
        this.course = course;
        this.college = college;
        this.grade = grade;
        this.passyear = passyear;
        this.organization = organization;
        this.designation = designation;
        this.jobRole = jobRole;
        this.durationFrom = durationFrom;
        DurationTo = durationTo;
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.projectRole = projectRole;
        this.projectDuration = projectDuration;
        this.projectTeam = projectTeam;
        this.skill = skill;
        this.interests = interests;
        this.industrialExposure = industrialExposure;
        this.awards = awards;
        this.hobby = hobby;
        this.refName = refName;
        this.refDesignation = refDesignation;
        this.refOrganization = refOrganization;
        this.refEmail = refEmail;
        this.refMobile = refMobile;
        this.image = image;
    }
    @Override
    public int describeContents() {
        return 0;
    }
}
