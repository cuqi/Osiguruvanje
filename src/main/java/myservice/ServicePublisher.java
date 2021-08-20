package myservice;
import client.*;

import javax.xml.ws.Endpoint;

public class ServicePublisher {
    public static void main(String[] args) {

        

        final String url = "http://localhost:8080/myservice";
        System.out.println("Publishing Service at endpoint " + url);

        Endpoint.publish(url, new MyService());
        
        // MyServiceService service = new MyServiceService();
        // client.MyService port = (client.MyService) service.getMyServicePort();
        // System.out.println(port.generateNum());

        // try {
        //     System.out.println(port.loginMethod("test_user", "pass123"));
        // } catch (NoSuchAlgorithmException_Exception e) {
        //     e.printStackTrace();
        // }
    
    }    
}
