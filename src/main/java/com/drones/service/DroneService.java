package com.drones.service;

import com.drones.entity.Drone;
import com.drones.model.State;

import java.util.List;

public interface DroneService {
    Drone create(Drone drone);

    boolean isDroneFleetComplete();

    Drone findById(Long id);

    void updateDroneState(Drone drone, State state);

    List<Drone> getAllAvailableDrones();

    List<Drone> findAll();
}
