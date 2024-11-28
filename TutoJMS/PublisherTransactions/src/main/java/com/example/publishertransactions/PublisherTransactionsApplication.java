package com.example.publishertransactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublisherTransactionsApplication implements CommandLineRunner {

    @Autowired
    private Publisher publisher;

    public static void main(String[] args) {
        SpringApplication.run(PublisherTransactionsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        publisher.publishMessages("transaction.topic");
    }
}

