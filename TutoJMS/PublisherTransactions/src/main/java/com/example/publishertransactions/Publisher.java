package com.example.publishertransactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Publisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public void publishMessages(String topicName) {
        try {
            jmsTemplate.convertAndSend(topicName, "Message 1 in transaction");
            jmsTemplate.convertAndSend(topicName, "Message 2 in transaction");
            System.out.println("All messages sent in a transaction.");
        } catch (Exception e) {
            System.err.println("Transaction failed: " + e.getMessage());
            throw e; //rollback
        }
    }
}
