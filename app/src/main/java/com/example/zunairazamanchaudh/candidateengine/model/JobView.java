package com.example.zunairazamanchaudh.candidateengine.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class JobView implements Parcelable {
    private static final String TAG = "JobView";
    private int image;
    private String title;
    private String company;
    private String country;
    private String city;
    private Date published;
    private Date close;
    private int num_ratings;
    private BigDecimal rating;
    private int serial_number;
    private int experience;
    private int noOfvacancy;
    private int status;
    private String description;
    private String industry;
    private String functionalArea;

    public JobView(int image, String title, String company, String country, String city, Date published, Date close, int num_ratings, BigDecimal rating, int serial_number, int experience, int noOfvacancy, int status, String description, String industry, String functionalArea) {
        this.image = image;
        this.title = title;
        this.company = company;
        this.country = country;
        this.city = city;
        this.published = published;
        this.close = close;
        this.num_ratings = num_ratings;
        this.rating = rating;
        this.serial_number = serial_number;
        this.experience = experience;
        this.noOfvacancy = noOfvacancy;
        this.status = status;
        this.description = description;
        this.industry = industry;
        this.functionalArea = functionalArea;
    }


    public JobView() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getExperience() {
        return experience;
    }

    public int getNoOfvacancy() {
        return noOfvacancy;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getIndustry() {
        return industry;
    }

    public String getFunctionalArea() {
        return functionalArea;
    }

    public void setExperience(int experience) {

        this.experience = experience;
    }

    public void setNoOfvacancy(int noOfvacancy) {
        this.noOfvacancy = noOfvacancy;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setFunctionalArea(String functionalArea) {
        this.functionalArea = functionalArea;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(image);
        dest.writeString(title);
        dest.writeString(company);
        dest.writeString(city);
        dest.writeLong(published.getTime());
        dest.writeLong(close.getTime());
        dest.writeInt(num_ratings);
        dest.writeInt(serial_number);
        dest.writeInt(experience);
        dest.writeInt(noOfvacancy);
        dest.writeInt(status);
        dest.writeString(description);
        dest.writeString(industry);
        dest.writeString(functionalArea);


    }

    protected JobView(Parcel in) {
        title = in.readString();
        company = in.readString();
        country=in.readString();
        city=in.readString();
        published=new Date(in.readLong());
        close=new Date(in.readLong());
        image = in.readInt();
        num_ratings = in.readInt();
        serial_number = in.readInt();
        experience=in.readInt();
        noOfvacancy=in.readInt();
        status=in.readInt();
        description=in.readString();
        industry=in.readString();
        functionalArea=in.readString();
    }
    public static final Creator<JobView> CREATOR = new Creator<JobView>() {
        @Override
        public JobView createFromParcel(Parcel in) {
            return new JobView(in);
        }

        @Override
        public JobView[] newArray(int size) {
            return new JobView[size];
        }
    };


    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public void setClose(Date close) {
        this.close = close;
    }

    public void setNum_ratings(int num_ratings) {
        this.num_ratings = num_ratings;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

    public static String getTAG() {

        return TAG;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Date getPublished() {
        return published;
    }

    public Date getClose() {
        return close;
    }

    public int getNum_ratings() {
        return num_ratings;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public String getNumberRatingsString(){
        return ("(" + getNum_ratings() + ")");
    }
}
