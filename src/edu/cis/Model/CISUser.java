package edu.cis.Model;

import java.util.ArrayList;
import java.util.UUID;

public class CISUser {
    private String userID;
    private String name;
    private String yearLevel;
    private ArrayList<Order> orders = new ArrayList<Order>();
    private double money;
    public CISUser(String name, String yearLevel, String id) {
        orders = new ArrayList<Order>();
        money = 50.0;
        this.userID = id;
        this.name = name;
        this.yearLevel = yearLevel;
    }
    public void addOrder(Order order) {
        orders.add(order);
    }
    public void removeOrder(int i) {
        orders.remove(i);
    }
    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public double getMoney() {
        return money;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void setMoney(double money) {
        this.money = money;

    }

    @Override
    public String toString() {
        return "CISUser{" +
                "userID='" + userID  +
                "', name='" + name  +
                "', yearLevel='" + yearLevel +
                "', orders= " + orders +
                ", money=" + money +
                "}";
    }
}
