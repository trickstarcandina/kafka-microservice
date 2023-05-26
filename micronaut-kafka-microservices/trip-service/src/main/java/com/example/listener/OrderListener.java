package com.example.listener;

import com.example.integration.Order;
import com.example.service.TripService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(groupId = "trip")
public class OrderListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderListener.class);

    private final TripService service;

    public OrderListener(TripService service) {
        this.service = service;
    }

    @Topic("orders")
    public void receive(@MessageBody Order order) {
        LOGGER.info("Received: {}", order);
        switch (order.getType()) {
            case NEW_TRIP:
                service.processNewTripOrder(order);
                break;
            case END_TRIP:
                service.processEndTripOrder(order);
                break;
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
