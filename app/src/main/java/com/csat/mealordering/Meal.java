package com.csat.mealordering;

public class Meal {

    private int orderid;
    private String mealName;
    private double mealPrice;
    private int quantity;
    private double totalPrice;

    public Meal(int orderid, String mealName, double mealPrice, int quantity, double totalPrice) {
        this.orderid = orderid;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
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
