package com.example.subscriber;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {
    @JmsListener(destination = "topic.example", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Subscriber received: " + message);
    }
}

