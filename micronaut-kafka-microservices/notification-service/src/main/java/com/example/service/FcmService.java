package com.example.service;

import com.example.model.NotificationRequest;
import com.example.repository.NotificationRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class FcmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcmService.class);

    private final NotificationRepository repository;


    public FcmService(NotificationRepository repository) {
        this.repository = repository;
    }

    public void fcmSendNotification(NotificationRequest request) {
        String token = repository.getTokenByUserId(request.getUserId());
        Message message = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder().setTitle(request.getTitle()).setBody(request.getContent()).build())
                .build();
        try {
            String msgSend = false ? FirebaseMessaging.getInstance().send(message) : "hard message :)";
            LOGGER.info("Send notification success to user {}, message ID {}", request.getUserId(), msgSend);
        } catch (FirebaseMessagingException exception) {
            LOGGER.warn("Send notification fail to user {}", request.getUserId());
        }

    }

}
