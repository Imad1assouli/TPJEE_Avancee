package org.example;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.search.ComparisonTerm;
import jakarta.mail.search.ReceivedDateTerm;
import jakarta.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


public class EmailReceiver {
    // Constantes pour l'email et le mot de passe de l'application
    private static final String EMAIL = "mosaabcrespo06@gmail.com";
    private static final String APP_PASSWORD = "jlyt wrwt ozuv tvpx";

    public static void main(String[] args) {
        try (Store store = getStore()) {
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            // Rechercher les messages reçus dans les dernières 24 heures
            SearchTerm searchTerm = new ReceivedDateTerm(ComparisonTerm.GT, new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
            Message[] messages = folder.search(searchTerm);

            // Charger le contenu des messages
            FetchProfile fetchProfile = new FetchProfile();
            fetchProfile.add(FetchProfile.Item.ENVELOPE);
            folder.fetch(messages, fetchProfile);

            // Afficher les messages reçus
            for (Message message : messages) {
                printMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour se connecter à la boîte de réception en utilisant IMAP
    private static Store getStore() throws MessagingException {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imap");
        props.put("mail.imap.host", "imap.gmail.com"); // Connexion à Gmail
        props.put("mail.imap.port", "993");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.imap.timeout", "10000"); // Timeout (10 secondes)

        Session session = Session.getInstance(props);
        Store store = session.getStore("imap");
        store.connect("imap.gmail.com", EMAIL, APP_PASSWORD);

        return store;
    }


    // Méthode pour ouvrir un dossier spécifique du store IMAP (ici, la boîte de réception "INBOX")
    private static Folder openFolder(Store store, String folderName) throws MessagingException {
        Folder folder = store.getFolder(folderName);
        folder.open(Folder.READ_ONLY); // Ouvre le dossier en lecture seule
        return folder;
    }

    // Méthode pour afficher les infos d'un message (date de réception, expéditeur, sujet, et contenu)
    private static void printMessage(Message message) throws MessagingException, IOException {
        // Affiche les informations du message
        System.out.printf("RECEIVED ON: %s\nFROM: %s\nSUBJECT: %s\nTEXT: %s\n",
                message.getReceivedDate(), // Date de réception
                ((InternetAddress) message.getFrom()[0]).getAddress(), // Adresse de l'expéditeur
                message.getSubject(), // Sujet du message
                getMessageContent(message)); // Contenu du message (texte)
    }

    // Méthode récursive pour récupérer le contenu d'un message
    private static String getMessageContent(Part part) throws MessagingException, IOException {
        // Si la partie du message est de type texte brut, retourne son contenu
        if (part.isMimeType("text/plain")) {
            return (String) part.getContent();
        }
        // Si le message est multipart (par exemple, contenant plusieurs parties comme texte + images)
        else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent(); // Récupère le contenu multipart
            StringBuilder content = new StringBuilder();
            // Parcourt toutes les parties du multipart et ajoute leur contenu
            for (int i = 0; i < multipart.getCount(); i++) {
                content.append(getMessageContent(multipart.getBodyPart(i)));
            }
            return content.toString();
        }
        // Si le type MIME est inconnu, retourne une chaîne vide
        return "";
    }
}
