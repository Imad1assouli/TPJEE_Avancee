package org.example;

import java.io.Serializable;

public class Livre implements Serializable {
    private String titre;
    private String auteur;

    // Constructeur
    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    // Getters
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    // toString() pour afficher le livre
    @Override
    public String toString() {
        return "Livre [Titre=" + titre + ", Auteur=" + auteur + "]";
    }
}
