package com.drones.jobs;

import com.drones.entity.Drone;
import com.drones.service.DroneService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Component
@AllArgsConstructor
public class DroneBatteryAuditJob {

    private final DroneService droneService;

    @Scheduled(cron = "0 0 0 * * *", zone = "GMT")
    public void batteryAudit() {

        log.info("cron job started at " + LocalDateTime.now());

        final List<Drone> drones = droneService.findAll();

        drones.forEach(drone ->
            log.info("Drone With serial number: " + drone.getSerialNumber() + " Battery Level is " + drone.getBatteryCapacity())
        );

        log.info("cron job ended at " + LocalDateTime.now());
    }
}
