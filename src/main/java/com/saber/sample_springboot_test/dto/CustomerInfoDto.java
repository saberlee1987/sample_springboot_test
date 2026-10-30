package com.saber.sample_springboot_test.dto;

import lombok.Data;

@Data
public class CustomerInfoDto {
    private Long customerId;
    private String customerName;
    private AccountDto account;
    private GuaranteeDto guarantee;
    private FacilityDto facility;
}
