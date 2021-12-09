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

        /*\
        Service jaxwsService = Service.create(wsdlURL, serviceName);
Dispatch<SOAPMessage> disp = jaxwsService.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);

//Add HTTP request Headers
Map<String, List<String>> requestHeaders = new HashMap<>();
requestHeaders.put("Auth-User", Arrays.asList("BILL_GATES"));
disp.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
        */
        final String url = "http://localhost:8000/myservice";
        System.out.println("Publishing Service at endpoint " + url);

        Endpoint.publish(url, new MyService());
        MyServiceService service = new MyServiceService();
        
        QName portQName = new QName("http://myservice/", "SOAPService");
        service.addPort(portQName, SOAPBinding.SOAP12HTTP_BINDING, "http://localhost:8000/myservice?wsdl");

        JAXBContext jc = null;

        try {
            jc = JAXBContext.newInstance(GetAOQuotation.class, GetAOQuotationResponse.class);
        } catch (JAXBException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Dispatch<Object> disp = service.createDispatch(portQName, jc, Service.Mode.MESSAGE);
        Map<String, List<String>> requestHeaders = new HashMap<>();
        requestHeaders.put("Content-Type", Arrays.asList("text/xml; charset=iso8859"));
        disp.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
        client2.MyService port = (client2.MyService) service.getMyServicePort();
        
        try{
            client2.AoInfo aoInfo = new client2.AoInfo();

            aoInfo.setChassis("WMAT325800M209024");
            aoInfo.setIsNew(false);
            aoInfo.setKW(100);
            aoInfo.setRegNum("SK-399-KO");
            client2.QuotationResponse qr = port.getAOQuotation(aoInfo, "0812998450009", "");
            System.out.println(qr.getMessage() + qr.getCode());
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        

        
        // try {
        //     AOInfo aoInfo = new AOInfo("SK-399-KO", "WMAT325800M209024", false, 100);
        //     port.getAOQuotation(aoInfo, "0812998450009", "");
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
    
    }    
}
