package com.example.tutomessagerie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private RabbitmqSender sender;

    @GetMapping("/send")
    public String sendMessage() {
        sender.send("Hello RabbitMQ!");
        return "Message sent";
    }
}
