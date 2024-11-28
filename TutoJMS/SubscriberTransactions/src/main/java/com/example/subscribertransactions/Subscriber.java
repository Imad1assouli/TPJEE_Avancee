package com.example.subscribertransactions;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Subscriber {

    @Transactional
    @JmsListener(destination = "transaction.topic", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received in transaction: " + message);
        if (message.contains("2")) {
             throw new RuntimeException("Simulated exception");
        }
    }
}

