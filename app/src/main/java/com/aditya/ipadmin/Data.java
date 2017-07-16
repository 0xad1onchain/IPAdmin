package com.aditya.ipadmin;

/**
 * Created by Adi on 10/02/17.
 */

public class Data {

    String title;
    String description;
    String eligibility;
    String city;
    String contact;
    //String imgurl;
    String uniqueID;
    String type;
    String date;
    String phone;
    boolean event;
    public String area;
    public String endDate;
    public String paymentMode;
    public String price;

    public Data()
    {}
    public Data(String title, String city, String description, String eligibility, String contact, String imgurl, String phone,
                boolean event, String uid, String type, String date, String area, String endDate, String paymentMode, String price) {
      /*Blank default constructor essential for Firebase*/
        this.title = title;
        this.description = description;
        this.eligibility = eligibility;
        this.contact = contact;
        //this.imgurl = imgurl;
        this.event = event;
        this.uniqueID = uid;
        this.type = type;
        this.date = date;
        this.city = city;
        this.area = area;
        this.endDate = endDate;
        this.paymentMode = paymentMode;
        this.phone = phone;
        this.price = price;
    }

    public String getTitle()
    {
        return this.title;
    }
    public String getDescription()
    {
        return this.description;
    }
    public String getEligibility()
    {
        return this.eligibility;
    }
    public String getType()
    {
        return this.type;
    }

    public String getContact()
    {
        return this.contact;
    }
//    //public String getImgurl()
//    {
//        return this.imgurl;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getEvent()
    {
        return this.event;
    }
    public String getUid()
    {
        return this.uniqueID;
    }
    public String getDate() {return this.date;}
    public String getCity() {return this.city;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public void setEvent(boolean event) {
        this.event = event;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}
