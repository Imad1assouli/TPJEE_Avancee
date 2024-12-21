package com.example.integration;

import com.example.soap.dto.CreateOrderRequest;
import com.example.soap.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;



import java.util.HashMap;
import java.util.Map;

@Configuration
public class IntegrationConfig {

    // Define channels for communication
    @Bean
    public MessageChannel soapChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel restChannel() {
        return MessageChannels.direct().get();
    }

    // Define the integration flow for transforming SOAP to REST and making the REST call
    @Bean
    public IntegrationFlow soapToRestFlow() {
        return IntegrationFlows.from("soapChannel")
                // Transform the SOAP request (Order) into a map to send via REST
                .transform((GenericTransformer<Order, Map<String, Object>>) order -> {
                    Map<String, Object> payload = new HashMap<>();
                    payload.put("id", order.getId());
                    payload.put("customerId", order.getCustomerId());
                    payload.put("amount", order.getAmount());
                    return payload;
                })
                // Send HTTP request to REST endpoint
                .handle(Http.outboundGateway("http://localhost:8080/api/v1/products")
                        .httpMethod(HttpMethod.POST)
                        .expectedResponseType(String.class))
                .channel("restChannel")
                .get();
    }
}
