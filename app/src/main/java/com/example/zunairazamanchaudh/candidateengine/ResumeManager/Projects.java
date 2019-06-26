package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

public class Projects {
    private String title,description,duration,role;
    private Integer teamsize;

    public Projects(String title, String description, String duration, String role, Integer teamsize) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.role = role;
        this.teamsize = teamsize;
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

    public void setTeamsize(Integer teamsize) {
        this.teamsize = teamsize;
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

    public Integer getTeamsize() {
        return teamsize;
    }
}
