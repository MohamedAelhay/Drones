package com.drones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto extends BaseDto {

    private String name;

    private String code;

    private MultipartFile imageMultiPart;

    private Long weight;
}