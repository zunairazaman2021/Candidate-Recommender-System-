package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class RecruiterFollowers {
    private String recruiterid;
    private String followeddate,followingstatus;
    private String followid;
    private String firstname;
    private String lastname;
    private String profile_image;
    private String security_level;
    private String dob;
    private String phone;
    private String email;
    private String nationality;
    private String user_id;

    private String city;
    private String country;
    private String state;
    private String zipcode;

    private String rfirstname;
    private String rlastname;
    private String rphone;
    private String remail;
    private String rimage;

    public RecruiterFollowers(){}

    public RecruiterFollowers(String recruiterid, String followeddate, String followingstatus,
                              String followid, String firstname, String lastname, String profile_image,
                              String security_level, String dob, String phone, String email,
                              String nationality, String user_id, String city, String country,
                              String state, String zipcode, String rfirstname,
                              String rlastname, String rphone, String remail, String rimage) {
        this.recruiterid = recruiterid;
        this.followeddate = followeddate;
        this.followingstatus = followingstatus;
        this.followid = followid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profile_image = profile_image;
        this.security_level = security_level;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;
        this.user_id = user_id;
        this.city = city;
        this.country = country;
        this.state = state;
        this.zipcode = zipcode;
        this.rfirstname = rfirstname;
        this.rlastname = rlastname;
        this.rphone = rphone;
        this.remail = remail;
        this.rimage = rimage;
    }

    public String getRfirstname() {
        return rfirstname;
    }

    public String getRlastname() {
        return rlastname;
    }

    public String getRphone() {
        return rphone;
    }

    public String getRemail() {
        return remail;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRfirstname(String rfirstname) {
        this.rfirstname = rfirstname;
    }

    public void setRlastname(String rlastname) {
        this.rlastname = rlastname;
    }

    public void setRphone(String rphone) {
        this.rphone = rphone;
    }

    public void setRemail(String remail) {
        this.remail = remail;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setRecruiterid(String recruiterid) {
        this.recruiterid = recruiterid;
    }

    public void setFolloweddate(String followeddate) {
        this.followeddate = followeddate;
    }

    public void setFollowingstatus(String followingstatus) {
        this.followingstatus = followingstatus;
    }

    public void setFollowid(String followid) {
        this.followid = followid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public void setSecurity_level(String security_level) {
        this.security_level = security_level;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRecruiterid() {
        return recruiterid;
    }

    public String getFolloweddate() {
        return followeddate;
    }

    public String getFollowingstatus() {
        return followingstatus;
    }

    public String getFollowid() {
        return followid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getSecurity_level() {
        return security_level;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUser_id() {
        return user_id;
    }
}
