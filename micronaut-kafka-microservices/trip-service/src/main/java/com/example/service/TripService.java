package com.example.service;

import com.example.client.TripClient;
import com.example.integration.Driver;
import com.example.integration.Order;
import com.example.model.Trip;
import com.example.model.TripStatus;
import com.example.repository.TripRepository;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Singleton
public class TripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripService.class);

    private TripRepository repository;
    private TripClient client;

    public TripService(TripRepository repository, TripClient client) {
        this.repository = repository;
        this.client = client;
    }

    public void processNewTripOrder(Order order) {
        LOGGER.info("Processing: {}", order);
        Trip trip = new Trip(order.getCurrentLocationX(), order.getCurrentLocationY(), "", order.getUserId());
        trip.setOrderId(order.getId());
        trip = repository.add(trip);
        client.send(trip);
        LOGGER.info("Message sent: {}", trip);
    }

    public void processEndTripOrder(Order order) {
        LOGGER.info("Processing: {}", order);
        Optional<Trip> trip = repository.findById(order.getTripId());
        trip.ifPresent(tripLocal -> {
            tripLocal.setStatus(TripStatus.FINISHED);
            Duration duration = Duration.between(tripLocal.getStartTime(), LocalDateTime.now());
            tripLocal.setPrice((int) duration.toSeconds());
            repository.update(tripLocal);
            client.send(tripLocal);
            LOGGER.info("Message sent: {}", tripLocal);
        });
    }

    public void processPaymentProcessedOrder(Order order) {
        LOGGER.info("Processing: {}", order);
        Optional<Trip> trip = repository.findById(order.getTripId());
        trip.ifPresent(tripLocal -> {
            tripLocal.setStatus(TripStatus.PAYED);
            repository.update(tripLocal);
        });
    }

    public void processCancelTripOrder(Order order) {
        LOGGER.info("Processing: {}", order);
        Optional<Trip> trip = repository.findNewestByPassengerId(order.getUserId());
        trip.ifPresent(tripLocal -> {
            tripLocal.setStatus(TripStatus.REJECTED);
            repository.update(tripLocal);
            client.send(tripLocal);
            LOGGER.info("Message sent: {}", tripLocal);
        });
    }

    public void processNewDriver(Driver driver, String orderId) {
        LOGGER.info("Processing: {}", driver);
        Optional<Trip> trip = repository.findByOrderId(Long.valueOf(orderId));
        trip.ifPresent(tripLocal -> {
            tripLocal.setDriverId(driver.getId());
            repository.update(tripLocal);
        });
    }

}
