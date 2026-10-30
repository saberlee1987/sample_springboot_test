package com.saber.sample_springboot_test.services;

import com.saber.sample_springboot_test.dto.FacilityDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {
    @SneakyThrows
    public FacilityDto getFacilityByCustomerId(Long customerId) {
        FacilityDto facility = new FacilityDto();
        facility.setFacilityName("vam test 1");
        facility.setFacilityPhone("0217789542");
        facility.setFacilityStatus(15);
        facility.setFacilityType("TRANSFER");
        facility.setFacilityAddress("test address Street 14");
        Thread.sleep(2000);
        return facility;
    }
}
