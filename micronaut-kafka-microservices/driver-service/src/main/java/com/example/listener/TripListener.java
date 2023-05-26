package com.example.listener;

import com.example.integration.Trip;
import com.example.service.DriverService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(groupId = "driver")
public class TripListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripListener.class);

    private final DriverService service;

    public TripListener(DriverService service) {
        this.service = service;
    }

    @Topic("trips")
    public void receive(@MessageBody Trip trip) {
        LOGGER.info("Received: {}", trip);
        switch (trip.getStatus()) {
            case FINISHED:
                service.processTripFinished(trip);
                break;
            case REJECTED:
                service.processTripRejected(trip);
                break;
            default:
                break;
        }
    }

}
