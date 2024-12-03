package com.example.tutomessagerie;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.amqp.core.Queue;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class RabbitmqSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Qualifier("fileQueue")
    @Autowired
    private Queue queue2;

    public void send(String message) {
        template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent in queue '" + message + "'");
    }

    public void sendFile(MultipartFile file) throws IOException {
        Message message = MessageBuilder.withBody(file.getBytes())
                .setHeader("ContentType", file.getContentType()).build();
        this.template.convertAndSend(queue2.getName(), message);
        System.out.println(" [x] Sent in queue2 '" + message + "'");
    }
}
