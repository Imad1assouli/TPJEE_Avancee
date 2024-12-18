package org.example.soaspringexample;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request", namespace = "http://example.com/schema")
public class GetRequest {
    private String input;

    @XmlElement(namespace = "http://example.com/schema")
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
