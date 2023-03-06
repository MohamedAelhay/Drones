package com.drones.facade.impl;

import com.drones.api.exception.BatteryLowException;
import com.drones.api.exception.DroneFleetIsCompleteException;
import com.drones.api.exception.DroneMaxWeightException;
import com.drones.api.exception.MedicationInvalidDataException;
import com.drones.dto.MedicationDto;
import com.drones.dto.request.DroneRegisterRequestDto;
import com.drones.entity.Drone;
import com.drones.entity.Medication;
import com.drones.facade.DroneFacade;
import com.drones.model.State;
import com.drones.service.DroneService;
import com.drones.service.MedicationService;
import com.drones.utils.DataMapperUtil;
import com.drones.utils.ImageUploadUtil;
import com.drones.utils.RegexValidator;
import lombok.AllArgsConstructor;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Component
@AllArgsConstructor
public class DroneFacadeImpl implements DroneFacade {

    private static final Long DRONE_MINIMUM_BATTERY_PERCENT = 25L;

    private static final String DRONE_CANNOT_BE_NULL_ERROR = "Can't register drone";

    private static final String MEDICATION_IMAGE_CANNOT_BE_NULL_ERROR = "Medication image can't be Null";

    private final DroneService droneService;

    private final DataMapperUtil dataMapperUtil;

    private final RegexValidator regexValidator;

    private final MedicationService medicationService;

    @Override
    public DroneRegisterRequestDto registerDrone(final DroneRegisterRequestDto droneDto) {

        Preconditions.checkArgument(Objects.nonNull(droneDto), DRONE_CANNOT_BE_NULL_ERROR);

        final Drone drone = dataMapperUtil.convertToEntity(droneDto);

        if(droneService.isDroneFleetComplete()) {
            throw new DroneFleetIsCompleteException();
        }

        drone.setSerialNumber(UUID.randomUUID());

        return dataMapperUtil.convertToDto(droneService.create(drone));
    }

    @Override
    public List<DroneRegisterRequestDto> getAllAvailableDrones() {

        return dataMapperUtil.convertAllDronesToDto(droneService.getAllAvailableDrones());
    }

    @Override
    public String loadDroneById(final Long droneId, final MedicationDto medicationDto) {

        final Drone drone = droneService.findById(droneId);

        validateDroneBattery(drone);

        validateMedications(medicationDto);

        validateDroneMaxWeight(drone, medicationDto.getWeight());

        final Medication medications = prepareMedications(medicationDto, drone);

        medicationService.create(medications);

        if(drone.getBatteryCapacity() > 25){

            droneService.updateDroneState(drone, State.LOADING);
        }

        return "Drone Loaded";
    }

    @Override
    public String getBatteryLevelForDroneById(final Long id) {
        final Drone drone = droneService.findById(id);

        return drone.getBatteryCapacity() + "%";
    }

    private void validateDroneBattery(final Drone drone) {
        if(drone.getBatteryCapacity() <= DRONE_MINIMUM_BATTERY_PERCENT) {
            throw new BatteryLowException(drone.getId().toString());
        }
    }

    private void validateMedications(final MedicationDto medicationDto) {

        validateMedicationName(medicationDto.getName());

        validateMedicationCode(medicationDto.getCode());

        Preconditions.checkArgument(Objects.nonNull(medicationDto.getImageMultiPart()), MEDICATION_IMAGE_CANNOT_BE_NULL_ERROR);
    }

    private void validateMedicationName(final String name) {
        if(!regexValidator.isValidMedicationName(name)) {
            throw new MedicationInvalidDataException("name " + name);
        }
    }

    private void validateMedicationCode(final String code) {
        if(!regexValidator.isValidMedicationCode(code)) {
            throw new MedicationInvalidDataException("code " + code);
        }
    }

    private void validateDroneMaxWeight(final Drone drone, final Long medicationWeight) {

        AtomicReference<Long> totalLoadedWeight = new AtomicReference<>(medicationWeight);

        drone.getMedications().forEach(medication ->
            totalLoadedWeight.updateAndGet(v -> v + medication.getWeight())
        );

        if(totalLoadedWeight.get() > drone.getWeightLimit()) {
            throw new DroneMaxWeightException();
        }
    }

    private Medication prepareMedications(final MedicationDto medicationDto, final Drone drone) {


        final String imagePath = ImageUploadUtil.uploadImage(medicationDto.getImageMultiPart());

        final Medication medication = dataMapperUtil.convertToEntity(medicationDto);

        medication.setImage(imagePath);

        medication.setDrone(drone);

        return medication;
    }
}
