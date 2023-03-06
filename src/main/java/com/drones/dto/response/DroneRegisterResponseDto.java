package com.drones.dto.response;

import com.drones.dto.BaseDto;
import com.drones.model.State;
import com.drones.model.Weight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneRegisterResponseDto extends BaseDto {

    private UUID serialNumber;

    private Weight droneModel;

    private Long weightLimit;

    private Long batteryCapacity;

    private State droneState;
}
