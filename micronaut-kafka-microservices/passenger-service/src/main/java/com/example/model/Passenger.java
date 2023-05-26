package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    private Long id;
    private String name;
    private String login;
    private String phoneNo;
    private int balance;
    private int homeLocationX;
    private int homeLocationY;
    private int discount;

    public Passenger(Long id, String name, String login, int balance) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.balance = balance;
    }
}
