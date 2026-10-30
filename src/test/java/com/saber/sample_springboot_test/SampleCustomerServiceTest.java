package com.saber.sample_springboot_test;

import com.saber.sample_springboot_test.dto.CustomerInfoDto;
import com.saber.sample_springboot_test.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class SampleCustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testRequestCount() {
        Runnable runnable = () -> customerService.processRequest();
        int count = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executorService.submit(runnable);
            System.out.println("requestCount in "+(i+1) +" ==> "+customerService.getRequestCount());
        }
        executorService.shutdown();
       // System.out.println("customerService.getRequestCount() ===> "+customerService.getRequestCount());
    }
    @Test
    public void testCustomerByCustomerId() {
        long startTime = System.currentTimeMillis();
        CustomerInfoDto customerInfo = customerService.getCustomerInfoByCustomerId(4785L);
        long endTime = System.currentTimeMillis();
        System.out.println("duration customer by customer id ===> "+(endTime-startTime)+" ms");
        System.out.println(customerInfo);
    }
}
