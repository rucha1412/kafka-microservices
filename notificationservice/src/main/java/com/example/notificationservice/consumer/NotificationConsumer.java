package com.example.notificationservice.consumer;

import com.example.notificationservice.dto.PaymentEvent;
import com.example.notificationservice.entity.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    public NotificationConsumer(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "payment-events", groupId = "notification-service-group")
    public void sendNotification(PaymentEvent event) {

        String message;

        if ("SUCCESS".equalsIgnoreCase(event.getPaymentStatus())) {
            message = "Order " + event.getOrderId() + " payment successful";
        } else {
            message = "Order " + event.getOrderId() + " payment failed";
        }

        Notification notification = new Notification(
                event.getOrderId(),
                message,
                event.getPaymentStatus()
        );

        notificationRepository.save(notification);

        System.out.println("Notification saved:");
        System.out.println(message);
    }
}