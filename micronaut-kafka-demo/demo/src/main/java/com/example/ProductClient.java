package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface ProductClient {

    @Topic("my-products")
    void sendProduct(@KafkaKey String brand, String name);

    void sendProduct(@Topic String topic, @KafkaKey String brand, String name);

}
