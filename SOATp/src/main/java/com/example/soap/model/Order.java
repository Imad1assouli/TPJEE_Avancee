package com.example.soap.model;

public class Order {
    private String id;
    private String customerId;
    private double amount;

    // Constructor
    public Order(String id, String customerId, double amount) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
