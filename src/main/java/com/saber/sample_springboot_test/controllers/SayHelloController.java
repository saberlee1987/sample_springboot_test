package com.saber.sample_springboot_test.controllers;

import com.saber.sample_springboot_test.dto.MessageDto;
import com.saber.sample_springboot_test.services.SayHelloService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SayHelloController {

    @Resource
    private  SayHelloService sayHelloService;

    @GetMapping("/sayHello")
    public ResponseEntity<MessageDto> sayHello(@RequestParam(name = "firstname") String firstname
            , @RequestParam(name = "lastname") String lastname) {
        return ResponseEntity.ok(sayHelloService.sayHello(firstname,lastname));
    }
}
