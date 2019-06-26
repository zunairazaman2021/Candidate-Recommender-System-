package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Academic {
    private String course_id;
    private String name;
    private String instituition;
    private String grade;
    private int passingYear;
    public Academic(){}
    public Academic(String name, String instituition, String grade, int passingYear) {
        this.name = name;
        this.instituition = instituition;
        this.grade = grade;
        this.passingYear = passingYear;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstituition(String instituition) {
        this.instituition = instituition;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPassingYear(int passingYear) {
        this.passingYear = passingYear;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getName() {
        return name;
    }

    public String getInstituition() {
        return instituition;
    }

    public String getGrade() {
        return grade;
    }

    public int getPassingYear() {
        return passingYear;
    }
}
