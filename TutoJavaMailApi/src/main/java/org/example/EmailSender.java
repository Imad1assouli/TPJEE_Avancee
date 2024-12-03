package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSender {
    public static void main(String[] args) {
        String recipient = "mosaablachhab06@gmail.com"; // Remplacez par l'adresse de destination
        String sender = "mosaabcrespo06@gmail.com";       // Remplacez par votre adresse Gmail
        String password = "jlyt wrwt ozuv tvpx";       // Remplacez par votre mot de passe d'application

        // Propriétés pour la connexion à Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Créer une session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        try {
            // Créer un message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Sujet de test");
            message.setText("Ceci est un e-mail de test envoyé via JavaMail.");

            // Envoyer l'e-mail
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
