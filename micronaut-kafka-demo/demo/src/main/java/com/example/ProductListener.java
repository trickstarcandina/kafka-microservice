package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ProductListener {

    @Topic("my-products")
    public void receive(@KafkaKey String brand, String name) {
        System.out.println("Got Product - " + name + " by " + brand);
    }


}
