package com.example.security;

import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import org.w3c.dom.NodeList;
import javax.xml.namespace.QName;
import java.util.Set;
import jakarta.xml.ws.soap.SOAPFaultException;

public class SecurityInterceptor implements SOAPHandler<SOAPMessageContext> {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "Tirhc1mada";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!outbound) {
            try {
                SOAPMessage message = context.getMessage();
                SOAPHeader header = message.getSOAPHeader();
                
                if (header == null) {
                    throw new SecurityException("No SOAP header found");
                }

                NodeList userNameNodes = header.getElementsByTagNameNS(
                    "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
                    "Username"
                );
                NodeList passwordNodes = header.getElementsByTagNameNS(
                    "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
                    "Password"
                );

                if (userNameNodes.getLength() == 0 || passwordNodes.getLength() == 0) {
                    throw new SecurityException("Security header missing username or password");
                }

                String username = userNameNodes.item(0).getTextContent();
                String password = passwordNodes.item(0).getTextContent();

                if (!USERNAME.equals(username) || !PASSWORD.equals(password)) {
                    throw new SecurityException("Invalid credentials");
                }
            } catch (Exception e) {
                throw new SOAPFaultException(null);
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
} 