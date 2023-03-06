package com.drones.controller;

import com.drones.dto.MedicationDto;
import com.drones.dto.request.DroneRegisterRequestDto;
import com.drones.facade.DroneFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.drones.api.response.ApiResponseHelper.OKApiResponse;
import static com.drones.controller.DispatchController.DRONES_ROOT_PATH;

@RestController
@AllArgsConstructor
@RequestMapping(DRONES_ROOT_PATH)
public class DispatchController {

    public static final String DRONES_ROOT_PATH = "/drones";

    private DroneFacade droneFacade;

    @PostMapping
    public ResponseEntity registerDrone(@RequestBody final DroneRegisterRequestDto droneDto) {
        return OKApiResponse(droneFacade.registerDrone(droneDto));
    }

    @PatchMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity loadDrone(@PathVariable final Long id,
                                    @RequestPart final MedicationDto medicationDto,
                                    @RequestPart final MultipartFile imageMultiPart) {

        medicationDto.setImageMultiPart(imageMultiPart);
        return OKApiResponse(droneFacade.loadDroneById(id, medicationDto));
    }

    @GetMapping
    public ResponseEntity getAllAvailableDrones() {
        return OKApiResponse(droneFacade.getAllAvailableDrones());
    }

    @GetMapping(value = "/{id}/battery")
    public ResponseEntity getBatteryLevelByDroneId(@PathVariable final Long id) {

        return OKApiResponse(droneFacade.getBatteryLevelForDroneById(id));
    }
}
