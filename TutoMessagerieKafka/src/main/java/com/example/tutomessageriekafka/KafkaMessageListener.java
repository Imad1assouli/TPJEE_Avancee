package com.example.tutomessageriekafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {
    @KafkaListener(topics = "greeting", groupId = "group_id")
    public void listen(String message) {
        System.out.println("Message received: " + message);
    }
}

