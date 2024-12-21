package com.example.soap.dto;

import com.example.soap.model.Order;

public class GetOrderResponse {
    private Order order;

    // Getter and setter
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}