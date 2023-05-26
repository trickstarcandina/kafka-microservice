package com.example.listener;

import com.example.model.NotificationRequest;
import com.example.service.FcmService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(groupId = "notification")
public class NotificationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationListener.class);

    private final FcmService fcmService;

    public NotificationListener(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @Topic("notifications")
    public void receive(@MessageBody NotificationRequest request) {
        LOGGER.info("Received: {}", request);
        fcmService.fcmSendNotification(request);
    }

}
