package com.example.listener;

import com.example.integration.Order;
import com.example.integration.OrderType;
import com.example.service.DriverService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(groupId = "driver")
public class OrderListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderListener.class);

    private final DriverService service;

    public OrderListener(DriverService service) {
        this.service = service;
    }

    @Topic("orders")
    public void receive(@MessageBody Order order) {
        LOGGER.info("Received: {}", order);
        if (order.getType() == OrderType.NEW_TRIP) {
            service.processNewTripOrder(order);
        }
    }

}
