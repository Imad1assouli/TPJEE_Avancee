package org.example;

import javax.naming.spi.ObjectFactory;
import javax.naming.Reference;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import java.util.Hashtable;

public class BibliothequeFactory implements ObjectFactory {
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        if (obj instanceof Reference) {
            Reference ref = (Reference) obj;
            if ("org.example.Bibliotheque".equals(ref.getClassName())) {
                return new Bibliotheque();  // Recréez l'objet Bibliotheque
            }
        }
        return null;
    }
}
