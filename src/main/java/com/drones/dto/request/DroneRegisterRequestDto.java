package com.drones.dto.request;

import com.drones.dto.BaseDto;
import com.drones.model.State;
import com.drones.model.Weight;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneRegisterRequestDto extends BaseDto {

    private UUID serialNumber;

    private Weight droneModel;

    private Long weightLimit;

    private Long batteryCapacity;

    private State droneState;
}
