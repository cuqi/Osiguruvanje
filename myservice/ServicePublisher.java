package myservice;

import javax.xml.ws.Endpoint;

public class ServicePublisher {
    public static void main(String[] args) {
        final String url = "http://localhost:8080/myservice";
        System.out.println("Publishing Service at endpoint " + url);

        Endpoint.publish(url, new MyService());
        //Database db = Database.getInsance();
        
        
    }    
}
