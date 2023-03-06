package com.drones.utils;

import com.drones.dto.request.DroneRegisterRequestDto;
import com.drones.dto.MedicationDto;
import com.drones.dto.response.DroneRegisterResponseDto;
import com.drones.entity.Drone;
import com.drones.entity.Medication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataMapperUtil {

    private ModelMapper modelMapper;

    DataMapperUtil() {
        this.modelMapper = new ModelMapper();
    }

    public Drone convertToEntity(final DroneRegisterRequestDto droneDto) {

        return modelMapper.map(droneDto, Drone.class);
    }

    public DroneRegisterResponseDto convertToDto(final Drone drone) {

        return modelMapper.map(drone, DroneRegisterResponseDto.class);
    }

    public List<DroneRegisterResponseDto> convertAllDronesToDto(final List<Drone> drones) {

        return drones.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Medication convertToEntity(final MedicationDto medicationDto) {
        return modelMapper.map(medicationDto, Medication.class);
    }

    public MedicationDto convertToDto(final Medication medication) {
        return modelMapper.map(medication, MedicationDto.class);
    }

//    public List<Medication> convertAllMedicationDtosToEntity(final List<MedicationDto> medicationDtos) {
//
//        return medicationDtos.stream()
//                .map(this::convertToEntity)
//                .collect(Collectors.toList());
//    }

//    public List<MedicationDto> convertAllMedicationsToDto(final List<Medication> medications) {
//
//        return medications.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
}
