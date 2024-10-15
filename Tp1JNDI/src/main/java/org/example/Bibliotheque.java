package org.example;

import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

public class Bibliotheque implements Referenceable {
    private List<Livre> livres;

    public Bibliotheque() {
        livres = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public Livre rechercherLivre(String titre) {
        for (Livre livre : livres) {
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                return livre;
            }
        }
        return null;
    }

    public List<Livre> listerTousLesLivres() {
        return livres;
    }

    @Override
    public Reference getReference() throws NamingException {
        return new Reference(Bibliotheque.class.getName(), BibliothequeFactory.class.getName(), null);
    }
}
