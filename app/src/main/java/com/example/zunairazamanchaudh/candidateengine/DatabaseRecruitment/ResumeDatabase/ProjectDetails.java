package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class ProjectDetails {
    private String project_id;
    private String title,description,duration,role;
    private int teamsize;
    public ProjectDetails(){ }

    public ProjectDetails(String title, String description, String duration, String role, int teamsize) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.role = role;
        this.teamsize = teamsize;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTeamsize(int teamsize) {
        this.teamsize = teamsize;
    }

    public String getProject_id() {
        return project_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public String getRole() {
        return role;
    }

    public int getTeamsize() {
        return teamsize;
    }
}
