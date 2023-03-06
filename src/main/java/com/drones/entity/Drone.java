package com.drones.entity;

import com.drones.model.State;
import com.drones.model.Weight;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.drones.entity.Drone.TABLE_NAME;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= TABLE_NAME)
public class Drone extends BaseEntity {

    public static final String TABLE_NAME = "drone";

    public static final String DRONE_STATE = "drone_state";

    public static final String DRONE_MODEL = "drone_model";

    public static final String WEIGHT_LIMIT = "weight_limit";

    public static final String SERIAL_NUMBER = "serial_number";

    public static final String BATTERY_CAPACITY = "battery_capacity";

    @Column(name = SERIAL_NUMBER, nullable = false, unique = true)
    private UUID serialNumber;

    @Column(name = DRONE_MODEL)
    private Weight droneModel;

    @Column(name = WEIGHT_LIMIT)
    private Long weightLimit;

    @Column(name = BATTERY_CAPACITY)
    private Integer batteryCapacity;

    @Column(name = DRONE_STATE)
    private State droneState;

    @OneToMany(mappedBy = TABLE_NAME)
    @JsonIgnoreProperties(TABLE_NAME)
    private List<Medication> medications = new ArrayList<>();
}
