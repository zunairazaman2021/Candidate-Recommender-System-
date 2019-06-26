package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

public class Courses {
   private String name;
   private String instituition;
   private String grade;
   private int passingYear;

    public Courses(String name, String instituition, String grade, int passingYear) {
        this.name = name;
        this.instituition = instituition;
        this.grade = grade;
        this.passingYear = passingYear;
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
