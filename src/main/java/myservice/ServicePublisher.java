package myservice;

import javax.xml.ws.Endpoint;

import soapService.*;

public class ServicePublisher {
    public static void main(String[] args) {

        

        final String url = "http://localhost:8000/myservice";
        System.out.println("Publishing Service at endpoint " + url);

        Endpoint.publish(url, new MyService());
        
        // MyServiceService service = new MyServiceService();
        // soapService.MyService port = service.getMyServicePort();
        // System.out.println(port.converter("10"));

        // try {
        //     System.out.println(port.loginMethod("test_user", "pass123"));
        // } catch (NoSuchAlgorithmException_Exception e) {
        //     e.printStackTrace();
        // }
    
    }    
}
