package com.saber.sample_springboot_test.services;

import com.saber.sample_springboot_test.dto.AccountDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @SneakyThrows
    public AccountDto getAccountByCustomerId(Long customerId) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNo("1456987956");
        accountDto.setAccountName("Account Test1");
        accountDto.setAccountOwnerName("Account name test");
        accountDto.setAccountType(14);
        Thread.sleep(2000);
        return accountDto;
    }
}
