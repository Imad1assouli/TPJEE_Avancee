package com.example.messagereceiver2.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @JmsListener(destination = "testTopic", containerFactory = "jmsListenerContainerTopic")
    public void receiveMessage(String message) {
        System.out.println("Message received by Receiver2 (Topic): " + message);
    }
}
