package com.example.service;

import com.example.client.NotificationClient;
import com.example.client.OrderClient;
import com.example.integration.NotificationRequest;
import com.example.integration.Order;
import com.example.integration.OrderType;
import com.example.model.Passenger;
import com.example.integration.Trip;
import com.example.repository.PassengerRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Singleton
public class PassengerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassengerService.class);
    private static final int MIN_BALANCE = 50;

    @Inject
    private PassengerRepository repository;

    @Inject
    private OrderClient orderClient;

    @Inject
    private NotificationClient notificationClient;

    public void processTripFinished(Trip trip) {
        LOGGER.info("Processing: {}", trip);
        Optional<Passenger> passenger = repository.findById(trip.getPassengerId());
        passenger.ifPresent(localPassenger -> {
            localPassenger.setBalance(localPassenger.getBalance() - trip.getPrice());
            repository.update(localPassenger);
            Order order = new Order(OrderType.PAYMENT_PROCESSED, localPassenger.getId(), trip.getId());
            orderClient.send(order);
            notificationClient.send(new NotificationRequest(order.getUserId(), "COMPLETE TRIP", "COMPLETE TRIP"));
            LOGGER.info("Message sent: {}", order);
        });
    }

    public void processNewTripOrder(Order order) {
        LOGGER.info("Processing: {}", order);
        Optional<Passenger> passenger = repository.findById(order.getUserId());
        passenger.ifPresent(localPassenger -> {
            if (localPassenger.getBalance() < MIN_BALANCE) {
                Order cancelOrder = new Order(OrderType.CANCEL_TRIP, localPassenger.getId(), null);
                orderClient.send(cancelOrder);
                notificationClient.send(new NotificationRequest(order.getUserId(), "NEW TRIP", "NEW TRIP"));
                LOGGER.info("Message sent: {}", cancelOrder);
            }
        });
    }
}

