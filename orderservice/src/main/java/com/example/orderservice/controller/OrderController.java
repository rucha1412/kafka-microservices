package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderEvent;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer orderProducer;
    private final OrderRepository orderRepository;

    public OrderController(OrderProducer orderProducer, OrderRepository orderRepository) {
        this.orderProducer = orderProducer;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderEvent orderEvent) {

        Order order = new Order(
                orderEvent.getOrderId(),
                orderEvent.getStatus(),
                orderEvent.getAmount()
        );

        orderRepository.save(order);

        orderProducer.sendOrderEvent(orderEvent);

        return "Order saved and event sent to Kafka";
    }
}