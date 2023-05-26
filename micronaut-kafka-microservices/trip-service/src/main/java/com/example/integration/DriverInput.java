package com.example.integration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverInput {

    private Long id;
    private int amount;
    private DriverStatus status;

}
