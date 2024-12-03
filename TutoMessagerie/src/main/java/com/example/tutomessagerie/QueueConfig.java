package com.example.tutomessagerie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

@Configuration
public class QueueConfig {

    @Bean
    public Queue queue() {
        return new Queue("message", false);
    }

    @Bean
    public Queue fileQueue() {
        return new Queue("fileQueue", false);
    }
}
