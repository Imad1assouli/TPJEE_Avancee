package com.example.service;

import com.example.model.Personne;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.soap.SOAPFaultException;

@WebService
public interface PersonneService {
    @WebMethod
    int addPersonne(@WebParam(name = "nom") String nom, @WebParam(name = "age") int age)
        throws SOAPFaultException;
    
    @WebMethod
    Personne getPersonne(@WebParam(name = "id") int id)
        throws SOAPFaultException;
    
    @WebMethod
    void updatePersonne(@WebParam(name = "id") int id, 
                       @WebParam(name = "nom") String nom, 
                       @WebParam(name = "age") int age) 
        throws SOAPFaultException;
    
    @WebMethod
    void deletePersonne(@WebParam(name = "id") int id) 
        throws SOAPFaultException;
} 