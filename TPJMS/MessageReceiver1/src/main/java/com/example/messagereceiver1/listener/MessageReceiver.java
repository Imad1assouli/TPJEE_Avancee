package com.example.messagereceiver1.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @JmsListener(destination = "testQueue")
    public void receiveMessage(String message) {
        System.out.println("Message received by Receiver1 (Queue): " + message);
    }
}
