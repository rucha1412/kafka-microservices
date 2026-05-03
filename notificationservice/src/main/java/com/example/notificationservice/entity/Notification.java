package com.example.notificationservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String message;
    private String status;

    public Notification() {
    }

    public Notification(String orderId, String message, String status) {
        this.orderId = orderId;
        this.message = message;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}