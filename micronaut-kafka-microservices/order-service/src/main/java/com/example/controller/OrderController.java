package com.example.controller;

import com.example.client.OrderClient;
import com.example.model.Order;
import com.example.repository.OrderRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.Set;

@Controller("order")
public class OrderController {

    @Inject
    OrderRepository repository;

    @Inject
    OrderClient client;


    @Post
    public Order add(@Body Order order) {
        order = repository.add(order);
        client.send(order);
        return order;
    }

    @Get
    public Set<Order> findAll() {
        return repository.findAll();
    }

}
