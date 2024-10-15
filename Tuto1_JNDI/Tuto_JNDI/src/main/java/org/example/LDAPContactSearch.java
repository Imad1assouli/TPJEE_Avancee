package org.example;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LDAPContactSearch {
    public static void main(String[] args) {
        // Configuration de l’ environnement JNDI pour LDAP
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com .sun . jndi . ldap . LdapCtxFactory ");
        env.put(Context.PROVIDER_URL, " ldap :// localhost :10389 ");
// Port par d f a u t d’ApacheDS
        env.put(Context.SECURITY_AUTHENTICATION, " simple ");
        env.put(Context.SECURITY_PRINCIPAL,
                "uid =admin ,ou= system "); // DN de l’ utilisateur admin
        env.put(Context.SECURITY_CREDENTIALS, " secret "); // Mot de passe


        try {
            // C r a t i o n du contexte initial
            DirContext ctx = new InitialDirContext(env);
            System.out.println(" Connexion au serveur LDAP r u s s i e .");


            // D f i n i r les c r i t r e s de recherche
            String searchBase = "ou= contacts ,dc= business ,dc= com ";
            String searchFilter = "( objectClass = inetOrgPerson )";

            // D f i n i r les attributs retourner
            String[] attrIDs = {"cn", "sn", " uid ", " mail ",
                    " department "};

            // C r a t i o n des p a r a m t r e s de recherche
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            searchControls.setReturningAttributes(attrIDs);
            // Effectuer la recherche
            NamingEnumeration<SearchResult> results = ctx.search(searchBase, searchFilter, searchControls);
            // Parcourir les r s u l t a t s
            while (results.hasMore()) {
                SearchResult sr = results.next();
                System.out.println("DN: " +
                        sr.getNameInNamespace());
                Attributes attrs = sr.getAttributes();
                for (NamingEnumeration<? extends Attribute> ae =
                     attrs.getAll(); ae.hasMore(); ) {
                    Attribute attr = ae.next();
                    System.out.println(attr.getID() + ": " +
                            attr.get());
                }
                System.out.println(" ---------------------------");
            }

            // Fermeture du contexte
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
