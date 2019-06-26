package com.example.zunairazamanchaudh.candidateengine;

import com.example.zunairazamanchaudh.candidateengine.model.JobView;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Products {


    public JobView[] PRODUCTS= {RED_LAMP, YELLOW_LAMP, BLUE_MUG, WHITE_MUG, RED_MUG, BLACK_HAT, BLUE_HAT, WHITE_HAT, ORANGE_HAT,
            WHITE_SHIRT_MALE, WHITE_SHIRT_FEMALE, BLACK_SHIRT_FEMALE, BLACK_SHIRT_MALE, GREY_FIDGET_SPINNER, GREEN_FIDGET_SPINNER, ICELAND_PICTURE
            , ICEY_COAST_PICTURE, HAVASU_FALLS_PICTURE, FRANCE_MOUNTAINS_PICTURE, GREEN_HILLS_PICTURE};


    public HashMap<String, JobView> PRODUCT_MAP = new HashMap<>();

    public Products()  {
        for(JobView product : PRODUCTS){
            PRODUCT_MAP.put(String.valueOf(product.getSerial_number()), product);
        }

    }

    public static final JobView RED_LAMP = new JobView(R.drawable.uiux,"UI/UX Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Canada - Any, Vancouver\n" +
            "Industry: Internet / E-Commerce, IT / Software Development, Retail / Merchandising\n" +
            "Amazon's Compute Cloud (EC2) team is looking for a passionate, high-caliber developer who is fascinated to solve unprecedented scale and availability challenges in distributed systems.\n" +
            "Who are we?\n" +
            "\n" +
            "We own the EC2.\n ","Software development","Engineering");

    public static final JobView YELLOW_LAMP= new JobView(R.drawable.ios,"IOS developer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Up to£55,000 per annum + benefits:\n" +
            "Create Recruitment Specialists Ltd:\n" +
            "Create are partnered with an independently owned supplier of luxury uniforms who are recruiting for an experienced Product development manager\n" +
            "West London\n" +
            "The key functions that are being sought after for this position are Marketing, Brand Development, Vice President, Growing Businesses, Social Networking, Auditor.\n" +
            "\n" +
            "Similar jobs like this which have been posted recently are Product Developement Manager, Head Technical Year, Senior Manager Product, Production Machinist, Sample Machinist, Menswear Sample Machinist ","Software development","Engineering");

    public static final JobView BLUE_MUG =  new JobView(R.drawable.androiddevelopment255x198,"android developer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Up to£55,000 per annum + benefits:\n" +
            "Create Recruitment Specialists Ltd:\n" +
            "Create are partnered with an independently owned supplier of luxury uniforms who are recruiting for an experienced Product development manager\n" +
            "West London\n" +
            "The key functions that are being sought after for this position are Marketing, Brand Development, Vice President, Growing Businesses, Social Networking, Auditor.\n" +
            "\n" +
            "Similar jobs like this which have been posted recently are Product Developement Manager, Head Technical Year, Senior Manager Product, Production Machinist, Sample Machinist, Menswear Sample Machinist","Software development","Engineering");

    public static final JobView WHITE_MUG =  new JobView(R.drawable.accountant_255x198,"Accountant", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView RED_MUG = new JobView(R.drawable.administrator_255x198,"Office administrator", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView BLACK_HAT =  new JobView(R.drawable.ios,"IOS developer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView BLUE_HAT = new JobView(R.drawable.androiddevelopment255x198,"android developer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView WHITE_HAT =  new JobView(R.drawable.uiux,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView ORANGE_HAT =  new JobView(R.drawable.androiddevelopment255x198,"Android developer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView WHITE_SHIRT_FEMALE =  new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView WHITE_SHIRT_MALE =  new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");


    public static final JobView BLACK_SHIRT_FEMALE = new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");


    public static final JobView BLACK_SHIRT_MALE =  new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");


    public static final JobView GREY_FIDGET_SPINNER = new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");


    public static final JobView GREEN_FIDGET_SPINNER =  new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");


    public static final JobView ICELAND_PICTURE =  new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");


    public static final JobView FRANCE_MOUNTAINS_PICTURE = new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView GREEN_HILLS_PICTURE =  new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView HAVASU_FALLS_PICTURE =  new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");

    public static final JobView ICEY_COAST_PICTURE =  new JobView(R.drawable.office_building,"Web Designer", "Pvt ltd" ,
            "Pakistan", "Lahore", new java.util.Date(System.currentTimeMillis()),new java.util.Date(System.currentTimeMillis()),10,new BigDecimal(3.5),92929,1,2,1,"Main Job duties: design,model and test prototypes for products, conduct research and design ","Software development","Engineering");





}
