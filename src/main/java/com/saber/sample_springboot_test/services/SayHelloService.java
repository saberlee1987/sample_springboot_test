package com.saber.sample_springboot_test.services;

import com.saber.sample_springboot_test.dto.MessageDto;
import com.saber.sample_springboot_test.routes.Routes;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SayHelloService {

    private final ProducerTemplate producerTemplate;

    public MessageDto sayHello(String firstname, String lastname) {
        Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.SAY_HELLO_ROUTE), exchange -> {
            exchange.getIn().setHeader("firstname", firstname);
            exchange.getIn().setHeader("lastname", lastname);
        });
        MessageDto messageDto = null;
        if (responseExchange != null && responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class) == 200) {
            messageDto = responseExchange.getIn().getBody(MessageDto.class);
        }
        return messageDto;
    }
}
