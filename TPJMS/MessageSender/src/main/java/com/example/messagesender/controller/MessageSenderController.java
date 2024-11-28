package com.example.messagesender.controller;

import com.example.messagesender.service.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageSenderController {
    @Autowired
    private MessageSenderService messageSenderService;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String destination, @RequestParam String message, @RequestParam boolean isTopic) {
        if (isTopic) {
            jmsTemplate.setPubSubDomain(true); // topic
        } else {
            jmsTemplate.setPubSubDomain(false); // queue
        }
        jmsTemplate.convertAndSend(destination, message);
        return "Message sent!";
    }
}
