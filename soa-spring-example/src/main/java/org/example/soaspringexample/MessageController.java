package org.example.soaspringexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final List<String> messages = new ArrayList<>();

    @Autowired
    private MyGateway myGateway;

    @GetMapping
    public List<String> getMessages() {
        return messages;
    }

    @PostMapping
    public void addMessage(@RequestBody String message) {
        messages.add(message);

// Appel au service SOAP via le gateway
GetRequest request = new GetRequest();
        request.setInput(message);
GetResponse response = myGateway.processRequest(request);
        System.out.println("Response from SOAP: " + response.getOutput());
        }

@MessagingGateway
public interface MyGateway {
    @Gateway(requestChannel = "inputChannel")
    GetResponse processRequest(GetRequest request);
}
}
