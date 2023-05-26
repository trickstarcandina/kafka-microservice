package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Driver {

    private Long id;
    private String name;
    private int balance;
    private String carModel;
    private String carRegistrationNo;
    private int currentLocationX;
    private int currentLocationY;
    private DriverStatus status;


    public Driver(Long id, String name, int currentLocationX, int currentLocationY, DriverStatus status) {
        this.id = id;
        this.name = name;
        this.currentLocationX = currentLocationX;
        this.currentLocationY = currentLocationY;
        this.status = status;
    }

    public Driver(String name, int currentLocationX, int currentLocationY, DriverStatus status) {
        this.name = name;
        this.currentLocationX = currentLocationX;
        this.currentLocationY = currentLocationY;
        this.status = status;
    }

    public Driver(Long id, int balance, DriverStatus status) {
        this.id = id;
        this.balance = balance;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", carModel='" + carModel + '\'' +
                ", carRegistrationNo='" + carRegistrationNo + '\'' +
                ", currentLocationX=" + currentLocationX +
                ", currentLocationY=" + currentLocationY +
                ", status=" + status +
                '}';
    }
}
