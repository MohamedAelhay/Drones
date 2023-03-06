package com.drones.service.impl;

import com.drones.api.exception.NotFoundException;
import com.drones.entity.Drone;
import com.drones.model.State;
import com.drones.repository.DroneRepository;
import com.drones.service.DroneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor
public class DroneServiceImpl implements DroneService {

    private static final Long MAX_DRONE_FLEET = 10L;

    private final DroneRepository droneRepository;

    @Override
    public Drone create(final Drone drone) {

        return droneRepository.save(drone);
    }

    @Override
    public boolean isDroneFleetComplete() {

        return droneRepository.count() >= MAX_DRONE_FLEET;
    }

    @Override
    public Drone findById(final Long id) {

        return droneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Drone with ID " + id));
    }

    @Override
    public void updateDroneState(final Drone drone, final State state) {

        drone.setDroneState(state);

        droneRepository.save(drone);
    }

    @Override
    public List<Drone> getAllAvailableDrones() {

        return droneRepository.findDistinctByDroneStateIn(asList(State.IDLE, State.LOADING));
    }

    @Override
    public List<Drone> findAll() {
        return droneRepository.findAll();
    }
}
