package com.example.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void publishMessage(String topicName, String message) {
        System.out.println("Publishing message: " + message);
        jmsTemplate.convertAndSend(topicName, message);
    }
}
