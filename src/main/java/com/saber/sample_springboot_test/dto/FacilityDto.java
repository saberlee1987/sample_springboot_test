package com.saber.sample_springboot_test.dto;

import lombok.Data;

@Data
public class FacilityDto {
    private String  facilityName;
    private String facilityAddress;
    private String facilityPhone;
    private Integer facilityStatus;
    private String facilityType;
}
