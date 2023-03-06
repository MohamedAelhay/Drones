package com.drones.repository;

import com.drones.entity.Drone;
import com.drones.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    List<Drone> findDistinctByDroneStateIn(List<State> states);
}
