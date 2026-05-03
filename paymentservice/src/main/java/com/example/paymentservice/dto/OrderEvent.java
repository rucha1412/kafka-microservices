package com.example.paymentservice.dto;

public class OrderEvent {

    private String orderId;
    private String status;
    private double amount;

    public OrderEvent() {}

    public String getOrderId() { return orderId; }
    public String getStatus() { return status; }
    public double getAmount() { return amount; }
}
