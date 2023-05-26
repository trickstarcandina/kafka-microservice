package com.example.repository;

import jakarta.inject.Singleton;

@Singleton
public class NotificationRepository {

    public String getTokenByUserId(Long userId) {
        //hard code
        return "this is firebase token";
    }

}
