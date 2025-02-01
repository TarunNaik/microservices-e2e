package com.tsoft.ecomm.indexer.service;

import com.tsoft.ecomm.indexer.model.Order;
import com.tsoft.ecomm.indexer.repo.OrderRepository;
import com.tsoft.ecomm.order.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IndexMessage {

    private final OrderRepository orderRepository;

    public IndexMessage(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void indexOrder(OrderPlacedEvent orderPlacedEvent) {
        Order order = Order.builder()
                .orderNumber(orderPlacedEvent.getOrderNumber())
                .orderAmount(orderPlacedEvent.getOrderAmount())
                .discountAmount(orderPlacedEvent.getDiscountAmount())
                .customerName(orderPlacedEvent.getFirstName() + " " + orderPlacedEvent.getLastName())
                .customerEmail(orderPlacedEvent.getEmail())
                .build();
        orderRepository.save(order);
        log.info("Order indexed with Order Number as: {}", order.getOrderNumber());
    }

}
