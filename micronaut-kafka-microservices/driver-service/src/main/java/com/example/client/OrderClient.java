package com.example.client;

import com.example.integration.Order;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;

@KafkaClient
public interface OrderClient {

    @Topic("orders")
    void send(@MessageBody Order order);

}
