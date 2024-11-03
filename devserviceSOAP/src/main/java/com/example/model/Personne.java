package com.example.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Personne {
    private int id;
    private String nom;
    private int age;
    
    // Default constructor required for JAXB
    public Personne() {}
    
    public Personne(int id, String nom, int age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
} 