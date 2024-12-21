package com.example.soap.endpoint;

import com.example.soap.dto.CreateOrderRequest;
import com.example.soap.dto.CreateOrderResponse;
import com.example.soap.dto.GetOrderRequest;
import com.example.soap.dto.GetOrderResponse;
import com.example.soap.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class OrderServiceEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.org/orders/v1";

    @Autowired
    private DirectChannel soapToRestChannel;

    // Handle Create Order request (SOAP)
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createOrderRequest")
    @ResponsePayload
    public CreateOrderResponse createOrder(@RequestPayload CreateOrderRequest request) {
        CreateOrderResponse response = new CreateOrderResponse();
        response.setStatus("Order created successfully");

        // Convert SOAP request into an Order and send via integration flow
        Order order = new Order(request.getProductId(), "Customer123", 150.00);
        soapToRestChannel.send(MessageBuilder.withPayload(order).build());

        return response;
    }

    // Handle Get Order request (SOAP)
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request) {
        GetOrderResponse response = new GetOrderResponse();
        response.setOrder(new Order(request.getId(), "Sample Product", 1.0));
        return response;
    }
}
