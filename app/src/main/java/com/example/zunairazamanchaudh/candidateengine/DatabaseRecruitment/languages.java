package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class languages {
    private String languageid;
    private String language;
    private String languagelevel;
    private String user_id;
public languages(){}
    public languages( String language,String languagelevel, String user_id) {
        this.language = language;
        this.languagelevel=languagelevel;
        this.user_id = user_id;
    }

    public String getLanguageid() {
        return languageid;
    }

    public String getLanguage() {
        return language;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getLanguagelevel() {
        return languagelevel;
    }

    public void setLanguageid(String languageid) {
        this.languageid = languageid;
    }

    public void setLanguagelevel(String languagelevel) {
        this.languagelevel = languagelevel;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
