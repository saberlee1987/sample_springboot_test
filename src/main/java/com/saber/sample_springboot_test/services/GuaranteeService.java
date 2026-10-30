package com.saber.sample_springboot_test.services;

import com.saber.sample_springboot_test.dto.GuaranteeDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class GuaranteeService {
    @SneakyThrows
    public GuaranteeDto getGuaranteeByCustomerId(Long customerId) {
        GuaranteeDto guaranteeDto = new GuaranteeDto();
        guaranteeDto.setGuaranteeNo("4656597865");
        guaranteeDto.setGuaranteeName("Guarantee test");
        guaranteeDto.setGuaranteeAddress("test address");
        guaranteeDto.setGuaranteeStatus(5);
        guaranteeDto.setGuaranteePhone("09127895625");
        Thread.sleep(2000);
        return guaranteeDto;
    }
}
