package com.example.javamailapitp;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.List;
import java.util.Properties;

public class Admin {
    private String email;
    private String password;

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void affectTask(Task task, User user) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, 
                InternetAddress.parse(user.getEmail()));
            message.setSubject("[TACHE] " + task.getTitle());
            message.setText(task.getDescription());

            Transport.send(message);
            System.out.println("Tâche envoyée avec succès à " + user.getEmail());

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void listAssignedTasks(List<Task> tasks) {
        System.out.println("Liste des tâches assignées :");
        for (Task task : tasks) {
            System.out.println("\n" + task.toString());
        }
    }
} 