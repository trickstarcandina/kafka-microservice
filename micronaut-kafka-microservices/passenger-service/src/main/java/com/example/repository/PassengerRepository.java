package com.example.repository;

import com.example.model.Passenger;
import jakarta.inject.Singleton;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;

@Singleton
public class PassengerRepository {

    private Set<Passenger> passengers = new HashSet<>();

    public Optional<Passenger> findById(Long id) {
        return passengers.stream().filter(passenger -> passenger.getId().equals(id)).findAny();
    }

    public void update(Passenger passenger) {
        passengers.remove(passenger);
        passengers.add(passenger);
    }

    @PostConstruct
    public void init() {
        passengers.add(new Passenger(1L, "Trickstar", "play-maker",  1000000));
        passengers.add(new Passenger(2L, "Duy", "2PM",  10));
        passengers.add(new Passenger(3L, "Son", "AWS",  1000));
        passengers.add(new Passenger(4L, "Dung", "SWAT",  100));
        passengers.add(new Passenger(5L, "Hoang", "SA",  5000));
        passengers.add(new Passenger(6L, "D.C.S", "JV",  500));
        passengers.add(new Passenger(7L, "Ha", "mini",  500));
        passengers.add(new Passenger(8L, "Jinx", "cap cap",  100));
    }

}
