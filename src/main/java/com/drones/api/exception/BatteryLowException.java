package com.drones.api.exception;

public class BatteryLowException extends RuntimeException {

    public BatteryLowException(String message) {super(message);}
}
