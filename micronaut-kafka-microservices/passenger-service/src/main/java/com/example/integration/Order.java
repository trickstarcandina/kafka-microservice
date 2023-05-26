package com.example.integration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private LocalDateTime createdAt;
    private OrderType type;
    private Long userId;
    private Long tripId;

    public Order(OrderType type, Long userId, Long tripId) {
        this.createdAt = LocalDateTime.now();
        this.type = type;
        this.userId = userId;
        this.tripId = tripId;
    }

}
