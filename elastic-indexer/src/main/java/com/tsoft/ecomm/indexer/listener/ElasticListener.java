package com.tsoft.ecomm.indexer.listener;

import com.tsoft.ecomm.indexer.service.IndexMessage;
import com.tsoft.ecomm.order.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ElasticListener {

    private final IndexMessage indexMessage;

    public ElasticListener(IndexMessage indexMessage) {
        this.indexMessage = indexMessage;
    }

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received order event: " + orderPlacedEvent);
        indexMessage.indexOrder(orderPlacedEvent);

    }
}
