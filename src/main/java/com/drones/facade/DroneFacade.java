package com.drones.facade;

import com.drones.dto.MedicationDto;
import com.drones.dto.request.DroneRegisterRequestDto;

import java.util.List;

public interface DroneFacade {
    DroneRegisterRequestDto registerDrone(DroneRegisterRequestDto droneDto);

    List<DroneRegisterRequestDto> getAllAvailableDrones();

    String loadDroneById(Long droneId, MedicationDto medicationDto);

    String getBatteryLevelForDroneById(Long id);
}
