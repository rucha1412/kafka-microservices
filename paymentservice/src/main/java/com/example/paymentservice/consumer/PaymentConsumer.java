package com.example.paymentservice.consumer;

import com.example.paymentservice.dto.OrderEvent;
import com.example.paymentservice.dto.PaymentEvent;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.PaymentProducer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.RetryableTopic;

@Component
public class PaymentConsumer {

    private final PaymentProducer paymentProducer;
    private final PaymentRepository paymentRepository;

    public PaymentConsumer(PaymentProducer paymentProducer,
                           PaymentRepository paymentRepository) {
        this.paymentProducer = paymentProducer;
        this.paymentRepository = paymentRepository;
    }

    @RetryableTopic(attempts = "3")  // ⭐ retry 3 times
    @KafkaListener(topics = "order-events", groupId = "payment-service-group")
    public void processPayment(OrderEvent orderEvent) {

        System.out.println("Processing order: " + orderEvent.getOrderId());

        // ⭐ simulate technical failure
        if (orderEvent.getAmount() == 999) {
            System.out.println("Simulated system failure!");
            throw new RuntimeException("Temporary failure");
        }

        String status;

        if (orderEvent.getAmount() > 1000) {
            status = "FAILED";  // business failure
        } else {
            status = "SUCCESS";
        }

        Payment payment = new Payment(
                orderEvent.getOrderId(),
                status,
                orderEvent.getAmount()
        );

        paymentRepository.save(payment);

        PaymentEvent event = new PaymentEvent(
                orderEvent.getOrderId(),
                status,
                orderEvent.getAmount()
        );

        paymentProducer.sendPaymentEvent(event);

    }

    @DltHandler
    public void handleDlt(OrderEvent orderEvent) {

        System.out.println("❌ Message moved to DLT");
        System.out.println("Order ID: " + orderEvent.getOrderId());
    }
}