package org.example;

import javax.naming.spi.ObjectFactory;
import javax.naming.*;
import java.util.Hashtable;

public class ContactFactory implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        if (obj instanceof Reference) {
            Reference ref = (Reference) obj;
            String id = (String) ref.get("Contact").getContent();
            // Crée un nouvel objet Contact (tu pourrais ajouter d'autres paramètres)
            return new Contact(id, "Inconnu", "email@example.com", "Département Inconnu");
        }
        return null;
    }
}
