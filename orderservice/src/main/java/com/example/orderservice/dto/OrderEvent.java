package com.example.orderservice.dto;

public class OrderEvent {

    private String orderId;
    private String status;
    private double amount;

    public OrderEvent() {
    }

    public OrderEvent(String orderId, String status, double amount) {
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }
}
