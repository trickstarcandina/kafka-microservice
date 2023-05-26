package com.example.client;

import com.example.integration.NotificationRequest;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import io.micronaut.scheduling.annotation.Async;

@KafkaClient
public interface NotificationClient {

    @Async
    @Topic("notifications")
    void send(@MessageBody NotificationRequest request);

}
