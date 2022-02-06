package myservice;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.SOAPBinding;

import client2.*;

public class ServicePublisher {
    public static void main(String[] args) {
        final String url = "http://localhost:8000/myservice";
        System.out.println("Publishing Service at endpoint " + url);
        Endpoint.publish(url, new MyService());
    }    
}
