package com.example.integration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    private Long id;
    private float locationX;
    private float locationY;
    private String destination;
    private Long passengerId;
    private Long driverId;
    private int price;
    private TripStatus status;
    private LocalDateTime startTime;

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

}
