package com.example.tutomessagerie;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitmqReceiver {

    @RabbitListener(queues = "message")
    public void receive(String in) throws InterruptedException {
        System.out.println(" [x] Received in queue1 '" + in + "'");
        Thread.sleep(3000);
        System.out.println("Done queue1");
    }

    @RabbitListener(queues = "fileQueue")
    public void receiveFile(Message message) {
        byte[] fileBytes = message.getBody();
        String contentType = (String) message.getMessageProperties().getHeaders().get("ContentType");
        System.out.println("Received file with content type: " + contentType);
    }
}
