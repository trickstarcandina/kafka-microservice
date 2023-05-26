package com.example.integration;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@AllArgsConstructor
public class NotificationRequest {

    private Long userId;

    private String title;

    private String content;

}
