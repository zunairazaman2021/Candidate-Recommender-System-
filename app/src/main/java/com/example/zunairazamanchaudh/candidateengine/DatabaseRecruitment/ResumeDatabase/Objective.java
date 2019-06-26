package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Objective {
  String obj_id,Objective,objDate,objPlace;
  public Objective(){}

    public Objective(String objective, String objDate, String objPlace) {
        Objective = objective;
        this.objDate = objDate;
        this.objPlace = objPlace;
    }


    public void setObj_id(String obj_id) {
        this.obj_id = obj_id;
    }

    public void setObjective(String objective) {
        Objective = objective;
    }

    public void setObjDate(String objDate) {
        this.objDate = objDate;
    }

    public void setObjPlace(String objPlace) {
        this.objPlace = objPlace;
    }

    public String getObj_id() {
        return obj_id;
    }

    public String getObjective() {
        return Objective;
    }

    public String getObjDate() {
        return objDate;
    }

    public String getObjPlace() {
        return objPlace;
    }
}
