package com.csat.mealordering;

public class Meal {

    int imageid;
    String mealName;
    double mealPrice;
    int quantity;
    double totalPrice;

    public Meal(int imageid, String mealName, double mealPrice, int quantity, double totalPrice) {
        this.imageid = imageid;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getOrderid() {
        return imageid;
    }

    public void setOrderid(int orderid) {
        this.imageid = orderid;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
