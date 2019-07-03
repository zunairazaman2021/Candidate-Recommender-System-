package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class RecruiterCompany {
    String companyid;
    String recruiteruser_id;
    String companyName,jobRole,ceoName,headHRdepartment,
            groupOfCompany,companyDescription,industryName,ownershipType,city,country;
    public RecruiterCompany(){
    }
    public RecruiterCompany(String recruiteruser_id, String companyName, String jobRole, String ceoName,
                            String headHRdepartment, String groupOfCompany, String companyDescription,
                            String industryName, String ownershipType,String city,String country) {
        this.recruiteruser_id = recruiteruser_id;
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.ceoName = ceoName;
        this.headHRdepartment = headHRdepartment;
        this.groupOfCompany = groupOfCompany;
        this.companyDescription = companyDescription;
        this.industryName = industryName;
        this.ownershipType = ownershipType;
        this.city=city;
        this.country=country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public void setRecruiteruser_id(String recruiteruser_id) {
        this.recruiteruser_id = recruiteruser_id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public void setHeadHRdepartment(String headHRdepartment) {
        this.headHRdepartment = headHRdepartment;
    }

    public void setGroupOfCompany(String groupOfCompany) {
        this.groupOfCompany = groupOfCompany;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }

    public String getCompanyid() {
        return companyid;
    }

    public String getRecruiteruser_id() {
        return recruiteruser_id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public String getCeoName() {
        return ceoName;
    }

    public String getHeadHRdepartment() {
        return headHRdepartment;
    }

    public String getGroupOfCompany() {
        return groupOfCompany;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public String getIndustryName() {
        return industryName;
    }

    public String getOwnershipType() {
        return ownershipType;
    }
}
