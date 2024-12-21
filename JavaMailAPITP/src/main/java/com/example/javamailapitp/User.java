package com.example.javamailapitp;

import javax.mail.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public List<Task> listTasks() {
        List<Task> tasks = new ArrayList<>();
        Properties props = new Properties();

        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imap.connectiontimeout", "5000"); // 5s timeout
        props.put("mail.imap.timeout", "5000");
        props.put("mail.imap.writetimeout", "5000");

        try {
            System.out.println("Connecting to IMAP server...");
            Session session = Session.getInstance(props);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", email, password);

            System.out.println("Opening INBOX folder...");
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            System.out.println("Fetching messages...");
            Message[] messages = inbox.getMessages();
            System.out.println(messages.length + " messages found in INBOX.");

            for (Message message : messages) {
                if (message.getSubject() != null && message.getSubject().startsWith("[TACHE]")) {
                    String title = message.getSubject().replace("[TACHE] ", "");
                    String description = getTextFromMessage(message);
                    tasks.add(new Task(title, description));
                    System.out.println("Task retrieved: " + title);
                }
            }

            inbox.close(false);
            store.close();

        } catch (MessagingException e) {
            System.err.println();
            e.printStackTrace();
        }

        if (tasks.isEmpty()) {
            System.out.println("No tasks found in the user's inbox.");
        } else {
            System.out.println("Retrieved " + tasks.size() + " tasks.");
        }

        return tasks;
    }


    private String getTextFromMessage(Message message) throws MessagingException {
        try {
            if (message.getContent() instanceof String) {
                return (String) message.getContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getEmail() {
        return email;
    }
} 