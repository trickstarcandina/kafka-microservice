package com.example.listener;

import com.example.model.Order;
import com.example.service.OrderService;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderListener.class);

    @Inject
    private OrderService service;

    @Topic("orders")
    public void receive(@MessageBody Order order) {
        LOGGER.info("Received: {}", order);
        switch (order.getType()) {
            case PAYMENT_PROCESSED:
                service.processPaymentProcessedOrder(order);
                break;
            case CANCEL_TRIP:
                service.processCancelTripOrder(order);
                break;
            default:
                break;
        }
    }

}
