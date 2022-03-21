package com.shop.shop2shop.Models;

import com.google.gson.annotations.SerializedName;

public class RidersOrder {

    @SerializedName("ID")
    private String ID;
    @SerializedName("Name")
    private String Name;
    @SerializedName("City")
    private String City;
    @SerializedName("Latitude")
    private String Latitude;
    @SerializedName("Longitude")
    private String Longitude;
    @SerializedName("Contact_PersonName_FirstName")
    private String Contact_PersonName_FirstName;
    @SerializedName("Contact_PersonName_LastName")
    private String Contact_PersonName_LastName;
    @SerializedName("OrderNumber")
    private String OrderNumber;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getContact_PersonName_FirstName() {
        return Contact_PersonName_FirstName;
    }

    public void setContact_PersonName_FirstName(String contact_PersonName_FirstName) {
        Contact_PersonName_FirstName = contact_PersonName_FirstName;
    }

    public String getContact_PersonName_LastName() {
        return Contact_PersonName_LastName;
    }

    public void setContact_PersonName_LastName(String contact_PersonName_LastName) {
        Contact_PersonName_LastName = contact_PersonName_LastName;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }
}
