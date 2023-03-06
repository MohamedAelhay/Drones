package com.drones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDto {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
