package com.saber.sample_springboot_test.services;

import com.saber.sample_springboot_test.dto.AccountDto;
import com.saber.sample_springboot_test.dto.CustomerInfoDto;
import com.saber.sample_springboot_test.dto.FacilityDto;
import com.saber.sample_springboot_test.dto.GuaranteeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Service
public class CustomerService {
    private volatile int requestCount = 0;

    @Autowired
    private AccountService accountService;
    @Autowired
    private GuaranteeService  guaranteeService;
    @Autowired
    private FacilityService facilityService;
    private final ExecutorService ioExecutor =  Executors.newFixedThreadPool(10); // یک استخر مشترک برای IO

    public void processRequest() {
        //System.out.println("processRequest thread name "+ Thread.currentThread().getName());
        requestCount++;
//        System.out.println("requestCount ===> "+requestCount);
    }

    public CustomerInfoDto getCustomerInfoByCustomerId(Long customerId) {

        Supplier<AccountDto> supplierAccount = () -> accountService.getAccountByCustomerId(customerId);
        Supplier<GuaranteeDto> supplierGuarantee = () -> guaranteeService.getGuaranteeByCustomerId(customerId);
        Supplier<FacilityDto> supplierFacility = () -> facilityService.getFacilityByCustomerId(customerId);

        CompletableFuture<AccountDto> futureAccount = CompletableFuture.supplyAsync(supplierAccount, ioExecutor)
                .orTimeout(5, TimeUnit.SECONDS)
                .exceptionally(ex -> {
                    System.err.println(ex.getMessage());
                   return new AccountDto();
                });
        CompletableFuture<GuaranteeDto> futureGuarantee = CompletableFuture.supplyAsync(supplierGuarantee, ioExecutor)
                .orTimeout(5, TimeUnit.SECONDS)
                .exceptionally(ex -> {
                    System.err.println(ex.getMessage());
                   return new GuaranteeDto();
                });
        CompletableFuture<FacilityDto> futureFacility = CompletableFuture.supplyAsync(supplierFacility, ioExecutor)
                .orTimeout(5, TimeUnit.SECONDS)
                .exceptionally(ex -> {
                    System.err.println(ex.getMessage());
                    return new FacilityDto();
                });


        CompletableFuture<CustomerInfoDto> futureCombine = futureAccount.thenCombine(futureGuarantee, (accountDto, guaranteeDto) -> {
            CustomerInfoDto customerInfoDto = new CustomerInfoDto();
            customerInfoDto.setAccount(accountDto);
            customerInfoDto.setGuarantee(guaranteeDto);
            return customerInfoDto;
        }).thenCombine(futureFacility, ((customerInfoDto, facilityDto) -> {
            customerInfoDto.setFacility(facilityDto);
            return customerInfoDto;
        }));

//        CompletableFuture<CustomerInfoDto> futureCombine = CompletableFuture.allOf(futureAccount, futureGuarantee, futureFacility)
//                .thenApply(v -> {
//                    CustomerInfoDto customerInfoDto = new CustomerInfoDto();
//                    customerInfoDto.setAccount(futureAccount.join());
//                    customerInfoDto.setGuarantee(futureGuarantee.join());
//                    customerInfoDto.setFacility(futureFacility.join());
//                    return customerInfoDto;
//                });

        CustomerInfoDto customerInfoDto = futureCombine.join();
        customerInfoDto.setCustomerId(customerId);
        customerInfoDto.setCustomerName("saber");
           return customerInfoDto;
    }

    private CustomerInfoDto buildFalbakc(Long customerId) {
        return new CustomerInfoDto();
    }

    public int getRequestCount() {
        return requestCount;
    }
}
