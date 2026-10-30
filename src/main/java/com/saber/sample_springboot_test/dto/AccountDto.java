package com.saber.sample_springboot_test.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String accountNo;
    private String accountName;
    private String accountOwnerName;
    private Byte accountStatus;
    private Integer accountType;
}
