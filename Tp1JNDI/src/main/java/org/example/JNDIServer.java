package org.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class JNDIServer {
    public static void main(String[] args) {
        try {
            // Configuration du contexte JNDI
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            env.put(Context.PROVIDER_URL, "file:///C:/tmp1/jndi");

            // Initialisation du contexte JNDI
            Context context = new InitialContext(env);

            // Créer et lier l'objet Bibliothèque
            Bibliotheque bibliotheque = new Bibliotheque();
            String name = "bibliotheque";

            // Supprimer l'objet lié s'il existe
            try {
                context.unbind(name);
                System.out.println("L'objet Bibliothèque existant a été supprimé.");
            } catch (NamingException e) {
                // Ignorer si l'objet n'existe pas
                System.out.println("Aucun objet Bibliothèque existant à supprimer.");
            }

            // Liez le nouvel objet Bibliothèque
            context.bind(name, bibliotheque);
            System.out.println("Serveur JNDI prêt. Bibliothèque liée.");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
