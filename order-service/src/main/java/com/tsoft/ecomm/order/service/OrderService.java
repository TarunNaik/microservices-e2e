package com.tsoft.ecomm.order.service;

import com.tsoft.ecomm.order.client.FeignClient;
import com.tsoft.ecomm.order.dto.OrderRequest;
import com.tsoft.ecomm.order.dto.OrderResponse;
import com.tsoft.ecomm.order.event.OrderPlacedEvent;
import com.tsoft.ecomm.order.model.Order;
import com.tsoft.ecomm.order.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final FeignClient feignClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;


    public OrderResponse createOrder(OrderRequest orderRequest) {
        Integer inventory = feignClient.getInventory(orderRequest.skuCode());
        if (inventory < orderRequest.quantity()) {
            throw new RuntimeException("Insufficient inventory for this order");
        }
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuCode(orderRequest.skuCode())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();
        Order savedOrder = orderRepository.save(order);
        log.info("Order created: {}", savedOrder);
        //time to send a message to the user that the order has been created successfully
        OrderPlacedEvent orderPlacedEvent = OrderPlacedEvent.builder()
                .lastName(orderRequest.userDetail().lastName())
                .firstName(orderRequest.userDetail().firstName())
                .email(orderRequest.userDetail().email()).orderNumber(savedOrder.getOrderNumber()).build();
        log.info("Sending order placed event: {}", orderPlacedEvent);
        kafkaTemplate.send("order-topic", orderPlacedEvent);
        return new OrderResponse(savedOrder.getId(), savedOrder.getOrderNumber(), savedOrder.getSkuCode(), savedOrder.getPrice(), savedOrder.getQuantity());
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity()))
                .toList();
    }
}
