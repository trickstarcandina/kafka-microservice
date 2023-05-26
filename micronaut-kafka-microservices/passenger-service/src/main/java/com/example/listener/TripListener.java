package com.example.listener;

import com.example.integration.Trip;
import com.example.integration.TripStatus;
import com.example.service.PassengerService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(groupId = "passenger")
public class TripListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripListener.class);

    private PassengerService service;

    public TripListener(PassengerService service) {
        this.service = service;
    }

    @Topic("trips")
    public void receive(@MessageBody Trip trip) {
        LOGGER.info("Received: {}", trip);
        if (trip.getStatus() == TripStatus.FINISHED) {
            service.processTripFinished(trip);
        }
    }

}

