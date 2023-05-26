package com.example.client;

import com.example.model.Driver;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import io.micronaut.messaging.annotation.MessageHeader;

@KafkaClient
public interface DriverClient {

    @Topic("drivers")
    void send(@MessageBody Driver driver, @MessageHeader("Order-Id") String orderId);

}
