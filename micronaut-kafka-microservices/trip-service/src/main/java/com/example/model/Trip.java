package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class Trip {

    private Long id;
    private float locationX;
    private float locationY;
    private String destination;
    private Long passengerId;
    private Long driverId;
    private Long orderId;
    private int price;
    private TripStatus status;
    private LocalDateTime startTime;

    public Trip() {

    }

    public Trip(float locationX, float locationY, String destination, Long passengerId) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.destination = destination;
        this.passengerId = passengerId;
        this.startTime = LocalDateTime.now();
        this.status = TripStatus.NEW;
    }

    public Trip(Long id, float locationX, float locationY, Long passengerId, Long driverId) {
        this.id = id;
        this.locationX = locationX;
        this.locationY = locationY;
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.startTime = LocalDateTime.now();
        this.status = TripStatus.NEW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id.equals(trip.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                ", destination='" + destination + '\'' +
                ", passengerId=" + passengerId +
                ", driverId=" + driverId +
                ", price=" + price +
                ", status=" + status +
                ", startTime=" + startTime +
                '}';
    }
}
