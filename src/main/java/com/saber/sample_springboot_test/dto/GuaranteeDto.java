package com.saber.sample_springboot_test.dto;

import lombok.Data;

@Data
public class GuaranteeDto {
    private String guaranteeNo;
    private String guaranteeName;
    private String guaranteeAddress;
    private String guaranteePhone;
    private Integer guaranteeStatus;
}
