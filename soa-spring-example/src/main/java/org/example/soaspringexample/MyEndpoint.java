package org.example.soaspringexample;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MyEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/schema";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "request")
    @ResponsePayload
    public GetResponse handleRequest(@RequestPayload GetRequest request) {
        GetResponse response = new GetResponse();
        response.setOutput("Hello, " + request.getInput() + "!");
        return response;
    }
}
