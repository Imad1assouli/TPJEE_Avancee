package org.example.soaspringexample;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response", namespace = "http://example.com/schema")
public class GetResponse {
    private String output;

    @XmlElement(namespace = "http://example.com/schema")
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
