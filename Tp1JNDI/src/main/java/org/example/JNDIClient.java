package org.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class JNDIClient {
    private static final int AJOUTER_LIVRE = 1;
    private static final int RECHERCHER_LIVRE = 2;
    private static final int LISTER_LIVRES = 3;
    private static final int QUITTER = 4;

    public static void main(String[] args) {
        try {
            // Configuration du contexte JNDI
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            env.put(Context.PROVIDER_URL, "file:///C:/tmp1/jndi");

            // Initialisation du contexte JNDI
            Context context = new InitialContext(env);

            // Recherche de l'objet Bibliothèque
            Object obj = context.lookup("bibliotheque");
            if (obj instanceof Bibliotheque) {
                Bibliotheque bibliotheque = (Bibliotheque) obj;

                // Menu interactif
                Scanner scanner = new Scanner(System.in);
                boolean running = true;

                while (running) {
                    afficherMenu();
                    int choix = obtenirChoix(scanner);

                    switch (choix) {
                        case AJOUTER_LIVRE:
                            ajouterLivre(bibliotheque, scanner);
                            break;
                        case RECHERCHER_LIVRE:
                            rechercherLivre(bibliotheque, scanner);
                            break;
                        case LISTER_LIVRES:
                            listerLivres(bibliotheque);
                            break;
                        case QUITTER:
                            running = false;
                            System.out.println("Merci d'avoir utilisé le système de gestion de bibliothèque. À bientôt !");
                            break;
                        default:
                            System.out.println("Choix invalide. Veuillez réessayer.");
                            break;
                    }
                }
            } else {
                System.out.println("Impossible de récupérer l'objet Bibliothèque.");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static void afficherMenu() {
        System.out.println("\n=======================");
        System.out.println("   Menu Principal      ");
        System.out.println("=======================");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Rechercher un livre");
        System.out.println("3. Lister tous les livres");
        System.out.println("4. Quitter");
        System.out.print("Choisissez une option : ");
    }

    private static int obtenirChoix(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrée invalide. Veuillez entrer un numéro : ");
            scanner.next(); // Consomme l'entrée invalide
        }
        return scanner.nextInt();
    }

    private static void ajouterLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.print("Titre du livre: ");
        String titre = scanner.next();
        System.out.print("Auteur du livre: ");
        String auteur = scanner.next();
        Livre livre = new Livre(titre, auteur);
        bibliotheque.ajouterLivre(livre);
        System.out.println("Livre ajouté.");
    }

    private static void rechercherLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.print("Titre du livre à rechercher: ");
        String titreRecherche = scanner.next();
        Livre livreRecherche = bibliotheque.rechercherLivre(titreRecherche);
        if (livreRecherche != null) {
            System.out.println("Livre trouvé: " + livreRecherche);
        } else {
            System.out.println("Livre non trouvé.");
        }
    }

    private static void listerLivres(Bibliotheque bibliotheque) {
        List<Livre> livres = bibliotheque.listerTousLesLivres();
        System.out.println("Liste des livres:");
        for (Livre l : livres) {
            System.out.println(l);
        }
    }
}
