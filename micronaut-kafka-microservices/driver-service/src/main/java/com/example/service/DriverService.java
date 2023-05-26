package com.example.service;

import com.example.client.DriverClient;
import com.example.client.NotificationClient;
import com.example.client.OrderClient;
import com.example.integration.NotificationRequest;
import com.example.integration.Order;
import com.example.integration.OrderType;
import com.example.integration.Trip;
import com.example.model.Driver;
import com.example.model.DriverStatus;
import com.example.repository.DriverRepository;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Singleton
public class DriverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverService.class);

    private final DriverClient driverClient;
    private final OrderClient orderClient;
    private final NotificationClient notificationClient;
    private final DriverRepository repository;

    public DriverService(DriverClient driverClient, OrderClient orderClient, NotificationClient notificationClient, DriverRepository repository) {
        this.driverClient = driverClient;
        this.orderClient = orderClient;
        this.notificationClient = notificationClient;
        this.repository = repository;
    }

    public void processNewTripOrder(Order order) {
        LOGGER.info("Processing: {}", order);
        Optional<Driver> driver = repository.findNearestDriver(order.getCurrentLocationX(), order.getCurrentLocationY());
        if (driver.isPresent()) {
            Driver driverLocal = driver.get();
            driverLocal.setStatus(DriverStatus.UNAVAILABLE);
            repository.updateDriver(driverLocal);
            driverClient.send(driverLocal, String.valueOf(order.getId()));
            notificationClient.send(new NotificationRequest(order.getUserId(), "NEW TRIP", "NEW TRIP"));
            LOGGER.info("Message sent: {}", driverLocal);
        }
    }

    public void processTripRejected(Trip trip) {
        LOGGER.info("Processing: {}", trip);
        Optional<Driver> driver = repository.findById(trip.getDriverId());
        driver.ifPresent(driverLocal -> {
            driverLocal.setStatus(DriverStatus.AVAILABLE);
            repository.updateDriver(driverLocal);
            notificationClient.send(new NotificationRequest(trip.getDriverId(),  "CANCEL TRIP", "CANCEL TRIP"));
        });
    }

    public void processTripFinished(Trip trip) {
        LOGGER.info("Processing: {}", trip);
        Optional<Driver> driver = repository.findById(trip.getDriverId());
        if (driver.isPresent()) {
            Driver driverLocal = driver.get();
            driverLocal.setBalance(driverLocal.getBalance() + trip.getPrice());
            repository.updateDriver(driverLocal);
            Order order = new Order(OrderType.PAYMENT_PROCESSED, driverLocal.getId(), trip.getId());
            orderClient.send(order);
            notificationClient.send(new NotificationRequest(order.getUserId(), "COMPLETE TRIP", "COMPLETE TRIP"));
            LOGGER.info("Message sent: {}", order);
        }
    }
}
