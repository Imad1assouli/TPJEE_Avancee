package com.example.service;

import com.example.model.Personne;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.soap.SOAPFaultException;
import javax.xml.namespace.QName;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@HandlerChain(file = "handlers.xml")
@WebService(endpointInterface = "com.example.service.PersonneService")
public class PersonneServiceImpl implements PersonneService {
    private final ConcurrentHashMap<Integer, Personne> personnes = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);
    
    @Override
    public int addPersonne(String nom, int age) throws SOAPFaultException {
        if (nom == null || nom.trim().isEmpty()) {
            throwSOAPFault("Le nom ne peut pas être vide");
        }
        if (age < 0 || age > 150) {
            throwSOAPFault("L'âge doit être entre 0 et 150");
        }
        
        int id = idGenerator.getAndIncrement();
        personnes.put(id, new Personne(id, nom, age));
        return id;
    }
    
    @Override
    public Personne getPersonne(int id) throws SOAPFaultException {
        Personne personne = personnes.get(id);
        if (personne == null) {
            throwSOAPFault("Personne non trouvée avec l'ID: " + id);
        }
        return personne;
    }
    
    @Override
    public void updatePersonne(int id, String nom, int age) throws SOAPFaultException {
        if (!personnes.containsKey(id)) {
            throwSOAPFault("Personne non trouvée avec l'ID: " + id);
        }
        if (nom == null || nom.trim().isEmpty()) {
            throwSOAPFault("Le nom ne peut pas être vide");
        }
        if (age < 0 || age > 150) {
            throwSOAPFault("L'âge doit être entre 0 et 150");
        }
        
        personnes.put(id, new Personne(id, nom, age));
    }
    
    @Override
    public void deletePersonne(int id) throws SOAPFaultException {
        if (personnes.remove(id) == null) {
            throwSOAPFault("Personne non trouvée avec l'ID: " + id);
        }
    }
    
    private void throwSOAPFault(String message) throws SOAPFaultException {
        try {
            SOAPFault fault = SOAPFactory.newInstance().createFault();
            fault.setFaultString(message);
            fault.setFaultCode(new QName("http://example.com", "VALIDATION_ERROR"));
            throw new SOAPFaultException(fault);
        } catch (Exception e) {
            throw new SOAPFaultException(null);
        }
    }
} 