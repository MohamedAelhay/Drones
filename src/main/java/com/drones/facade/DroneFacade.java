package com.drones.facade;

import com.drones.dto.MedicationDto;
import com.drones.dto.request.DroneRegisterRequestDto;
import com.drones.dto.response.DroneRegisterResponseDto;

import java.util.List;

public interface DroneFacade {
    DroneRegisterResponseDto registerDrone(DroneRegisterRequestDto droneDto);

    List<DroneRegisterResponseDto> getAllAvailableDrones();

    DroneRegisterResponseDto getDroneById(Long id);

    String loadDroneById(Long droneId, MedicationDto medicationDto);

    String getBatteryLevelForDroneById(Long id);
}
