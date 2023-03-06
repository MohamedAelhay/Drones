package com.drones.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

import static com.drones.api.response.ApiResponseHelper.*;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler
    public ResponseEntity handleException(Exception exception){
        return BadRequestApiResponse(exception.getMessage(), Collections.singletonList(exception.getLocalizedMessage()));
    }

    @ExceptionHandler(value = {DroneMaxWeightException.class})
    public ResponseEntity handleException(){
        return ConflictResponse("Total medication weight exceed drone limit");
    }

    @ExceptionHandler
    public ResponseEntity handleException(NotFoundException notFoundException){
        return NotFound("Can't Find " + notFoundException.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleException(DroneFleetIsCompleteException droneFleetIsCompleteException){
        return ConflictResponse("Can't register more drones right now");
    }

    @ExceptionHandler
    public ResponseEntity handleException(BatteryLowException batteryLowException){
        return InValid("Can't load Drone with ID " + batteryLowException.getMessage() + " because battery lower than 25%");
    }

    @ExceptionHandler
    public ResponseEntity handleException(MedicationInvalidDataException medicationInvalidDataException){
        return InValid("Invalid Medication " + medicationInvalidDataException.getMessage());
    }
}
