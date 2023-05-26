package com.example.listener;

import com.example.integration.Driver;
import com.example.service.TripService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import io.micronaut.messaging.annotation.MessageHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(groupId = "trip")
public class DriverListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverListener.class);

    private final TripService service;

    public DriverListener(TripService service) {
        this.service = service;
    }

    @Topic("drivers")
    public void receive(@MessageBody Driver driver, @MessageHeader("Order-Id") String orderId) {
        LOGGER.info("Received: driver->{}, header->{}", driver, orderId);
        service.processNewDriver(driver, orderId);
    }

}
