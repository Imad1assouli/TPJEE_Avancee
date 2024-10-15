 package org.example;
 import javax.naming.*;
 import java.util.Hashtable;

 import javax.naming.*;
 import java.util.Hashtable;

 public class ContactJNDIService {
     public static void main(String[] args) {
         // Configuration de l'environnement JNDI
         Hashtable<String, String> env = new Hashtable<>();
         env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
         env.put(Context.PROVIDER_URL, "file:///C:/tmp/contact_jndi");  // Assurez-vous que ce répertoire existe

         try {
             // Création du contexte initial
             Context ctx = new InitialContext(env);

             // Création du sous-contexte "contact"
             try {
                 ctx.createSubcontext("contact");
             } catch (NameAlreadyBoundException e) {
                 System.out.println("Le sous-contexte 'contact' existe déjà.");
             }

             // Création d’un objet Contact
             Contact contact = new Contact("C001", "Marie Curie", "marie.curie@example.com", "Recherche");

             // Utilisation de rebind() pour éviter l'erreur NameAlreadyBoundException
             String name = "contact/C001";
             ctx.rebind(name, contact);
             System.out.println("Contact lié avec succès : " + name);

             // Recherche de l’objet Contact dans le contexte
             Contact retrievedContact = (Contact) ctx.lookup(name);
             System.out.println("Contact récupéré : " + retrievedContact);

             // Liste des contacts dans le sous-contexte "contact"
             System.out.println("Liste des contacts dans le sous-contexte 'contact':");

             for (NamingEnumeration<NameClassPair> enumeration = ctx.list("contact"); enumeration.hasMore(); ) {
                 NameClassPair ncp = enumeration.next();
                 System.out.println(ncp.getName() + " : " + ncp.getClassName());
             }

             // Fermeture du contexte
             ctx.close();
         } catch (NamingException e) {
             e.printStackTrace();
         }
     }
 }
