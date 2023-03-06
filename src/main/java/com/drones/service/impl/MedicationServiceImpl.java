package com.drones.service.impl;

import com.drones.entity.Medication;
import com.drones.repository.MedicationRepository;
import com.drones.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    @Override
    public void create(final Medication medication) {
        medicationRepository.save(medication);
    }
}
