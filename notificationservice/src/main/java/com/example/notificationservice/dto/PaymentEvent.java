package com.example.notificationservice.dto;

public class PaymentEvent {

    private String orderId;
    private String paymentStatus;
    private double amount;

    public PaymentEvent() {}

    public String getOrderId() { return orderId; }
    public String getPaymentStatus() { return paymentStatus; }
    public double getAmount() { return amount; }
}