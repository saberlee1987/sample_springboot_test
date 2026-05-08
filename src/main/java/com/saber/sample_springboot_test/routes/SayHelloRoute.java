package com.saber.sample_springboot_test.routes;

import com.saber.sample_springboot_test.dto.MessageDto;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SayHelloRoute extends AbstractRouteBuilder {
    @Override
    public void configure() throws Exception {
        super.configure();

        from(String.format("direct:%s",Routes.SAY_HELLO_ROUTE))
                .routeId(Routes.SAY_HELLO_ROUTE)
                .routeGroup(Routes.SAY_HELLO_ROUTE)
                .log("request for sayhello with firstname : ${header.firstname} , lastname : ${header.lastname}")
                .process(exchange -> {
                    String firstname = exchange.getIn().getHeader("firstname", String.class);
                    String lastname = exchange.getIn().getHeader("lastname", String.class);
                    String message =  String.format("Hello %s %s",firstname,lastname);
                    MessageDto messageDto = new MessageDto();
                    messageDto.setMessage(message);
                    exchange.getIn().setBody(messageDto);
                })
                .to(String.format("direct:%s",Routes.SAY_HELLO_ROUTE_GATEWAY));

        from(String.format("direct:%s",Routes.SAY_HELLO_ROUTE_GATEWAY))
                .routeId(Routes.SAY_HELLO_ROUTE_GATEWAY)
                .routeGroup(Routes.SAY_HELLO_ROUTE_GATEWAY)
                .log("response body ===> ${in.body}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200));
    }
}
