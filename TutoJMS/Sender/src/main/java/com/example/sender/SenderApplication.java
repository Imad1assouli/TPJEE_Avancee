package com.example.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SenderApplication implements CommandLineRunner {
    @Autowired
    private Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }

    @Override
    public void run(String... args) {
        sender.sendMessage("queue.test", "Hello, JMS!");
    }
}