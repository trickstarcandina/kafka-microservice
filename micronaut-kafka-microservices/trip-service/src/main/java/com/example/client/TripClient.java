package com.example.client;

import com.example.model.Trip;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;

@KafkaClient
public interface TripClient {

    @Topic("trips")
    void send(@MessageBody Trip trip);

}
