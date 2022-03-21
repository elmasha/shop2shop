package com.shop.shop2shop.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("RidersOrder")
    private List<RidersOrder> ridersOrders = null;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<RidersOrder> getRidersOrders() {
        return ridersOrders;
    }
    public void setArticles(List<RidersOrder> ridersOrders) {
        this.ridersOrders = ridersOrders;
    }
}
