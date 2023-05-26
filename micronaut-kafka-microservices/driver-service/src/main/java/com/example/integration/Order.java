package com.example.integration;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class Order {

    private Long id;
    private LocalDateTime createdAt;
    private OrderType type;
    private Long userId;
    private Long tripId;
    private float currentLocationX;
    private float currentLocationY;

    public Order() {
    }

    public Order(OrderType type, Long userId, Long tripId) {
        this.createdAt = LocalDateTime.now();
        this.type = type;
        this.userId = userId;
        this.tripId = tripId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", type=" + type +
                ", userId=" + userId +
                '}';
    }

}
