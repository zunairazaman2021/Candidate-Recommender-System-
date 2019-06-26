package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class education {
    private String education_id;
    private String course;
    private String institute;
    private String grade;
    private String status;
    private int passyear;
    private String user_id;
public education(){}
    public education(String course, String institute, String grade, String status, int passyear, String user_id) {
        this.course = course;
        this.institute = institute;
        this.grade = grade;
        this.status = status;
        this.passyear = passyear;
        this.user_id = user_id;
    }

    public String getEducation_id() {
        return education_id;
    }

    public String getCourse() {
        return course;
    }

    public String getInstitute() {
        return institute;
    }

    public String getGrade() {
        return grade;
    }

    public String getStatus() {
        return status;
    }

    public int getPassyear() {
        return passyear;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setEducation_id(String education_id) {
        this.education_id = education_id;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPassyear(int passyear) {
        this.passyear = passyear;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
