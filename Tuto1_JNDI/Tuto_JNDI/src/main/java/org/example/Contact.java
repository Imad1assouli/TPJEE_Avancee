package org.example;

import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.NamingException;
import javax.naming.StringRefAddr;
import java.io.Serializable;

public class Contact implements Serializable, Referenceable {
    private String id;
    private String name;
    private String email;
    private String department;

    public Contact(String id, String name, String email, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    // Getters et Setters

    @Override
    public String toString() {
        return "ID: " + id + ", Nom : " + name + ", Email : " + email + ", Département : " + department;
    }

    @Override
    public Reference getReference() throws NamingException {
        // Retourne une référence pour recréer l'objet
        return new Reference(Contact.class.getName(),
                new StringRefAddr("Contact", id),
                ContactFactory.class.getName(),
                null);
    }
}
