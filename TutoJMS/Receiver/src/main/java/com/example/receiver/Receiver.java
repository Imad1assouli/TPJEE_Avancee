package com.example.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @JmsListener(destination = "queue.test")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }
}
