package com.example.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublisherApplication implements CommandLineRunner {

    @Autowired
    private Publisher publisher;

    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        publisher.publishMessage("topic.example", "Hello, JMS Topic!");
        publisher.publishMessage("topic.example", "Second message to subscribers.");
    }
}

