package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;

@Controller
public class ProductController {

    @Inject
    ProductClient productClient;

    @Get("test")
    public void testKafka(@QueryValue String name, @QueryValue String brand) {
        productClient.sendProduct(brand, name);
    }
}
