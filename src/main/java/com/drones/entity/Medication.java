package com.drones.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import static com.drones.entity.Medication.TABLE_NAME;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= TABLE_NAME)
public class Medication extends BaseEntity {

    public static final String TABLE_NAME = "medication";

    public static final String MEDICATIONS = "medications";

    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String IMAGE = "image";

    public static final String WEIGHT = "weight";

    public static final String DRONE_ID = "drone_id";

    @Column(name = NAME)
    private String name;

    @Column(name = CODE)
    private String code;

    @Column(name = IMAGE)
    private String image;

    @Column(name = WEIGHT)
    private Integer weight;

    @ManyToOne
    @JsonIgnoreProperties(MEDICATIONS)
    @JoinColumn(name = DRONE_ID, referencedColumnName = ID)
    private Drone drone;
}
