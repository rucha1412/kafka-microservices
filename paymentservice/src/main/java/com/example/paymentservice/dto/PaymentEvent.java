package com.example.paymentservice.dto;

public class PaymentEvent {

    private String orderId;
    private String paymentStatus;
    private double amount;

    public PaymentEvent() {}

    public PaymentEvent(String orderId, String paymentStatus, double amount) {
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
    }

    public String getOrderId() { return orderId; }
    public String getPaymentStatus() { return paymentStatus; }
    public double getAmount() { return amount; }
}