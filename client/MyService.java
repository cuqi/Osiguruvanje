
package client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MyService", targetNamespace = "http://myservice/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MyService {


    /**
     * 
     * @param password2
     * @param password1
     * @param username
     * @param ssn
     * @return
     *     returns java.lang.String
     * @throws NoSuchAlgorithmException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "register", targetNamespace = "http://myservice/", className = "client.Register")
    @ResponseWrapper(localName = "registerResponse", targetNamespace = "http://myservice/", className = "client.RegisterResponse")
    @Action(input = "http://myservice/MyService/registerRequest", output = "http://myservice/MyService/registerResponse", fault = {
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://myservice/MyService/register/Fault/NoSuchAlgorithmException")
    })
    public String register(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password1", targetNamespace = "")
        String password1,
        @WebParam(name = "password2", targetNamespace = "")
        String password2,
        @WebParam(name = "SSN", targetNamespace = "")
        String ssn)
        throws NoSuchAlgorithmException_Exception
    ;

    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "generateNum", targetNamespace = "http://myservice/", className = "client.GenerateNum")
    @ResponseWrapper(localName = "generateNumResponse", targetNamespace = "http://myservice/", className = "client.GenerateNumResponse")
    @Action(input = "http://myservice/MyService/generateNumRequest", output = "http://myservice/MyService/generateNumResponse")
    public int generateNum();

    /**
     * 
     * @param password2
     * @param password1
     * @param username
     * @return
     *     returns java.lang.String
     * @throws IOException_Exception
     * @throws NoSuchAlgorithmException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "unregister", targetNamespace = "http://myservice/", className = "client.Unregister")
    @ResponseWrapper(localName = "unregisterResponse", targetNamespace = "http://myservice/", className = "client.UnregisterResponse")
    @Action(input = "http://myservice/MyService/unregisterRequest", output = "http://myservice/MyService/unregisterResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://myservice/MyService/unregister/Fault/IOException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://myservice/MyService/unregister/Fault/NoSuchAlgorithmException")
    })
    public String unregister(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password1", targetNamespace = "")
        String password1,
        @WebParam(name = "password2", targetNamespace = "")
        String password2)
        throws IOException_Exception, NoSuchAlgorithmException_Exception
    ;

    /**
     * 
     * @param password
     * @param username
     * @return
     *     returns java.lang.String
     * @throws NoSuchAlgorithmException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "loginMethod", targetNamespace = "http://myservice/", className = "client.LoginMethod")
    @ResponseWrapper(localName = "loginMethodResponse", targetNamespace = "http://myservice/", className = "client.LoginMethodResponse")
    @Action(input = "http://myservice/MyService/loginMethodRequest", output = "http://myservice/MyService/loginMethodResponse", fault = {
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://myservice/MyService/loginMethod/Fault/NoSuchAlgorithmException")
    })
    public String loginMethod(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws NoSuchAlgorithmException_Exception
    ;

    /**
     * 
     * @param sessionID
     * @param householdInfo
     * @return
     *     returns client.HouseholdResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getHouseholdQuotation", targetNamespace = "http://myservice/", className = "client.GetHouseholdQuotation")
    @ResponseWrapper(localName = "getHouseholdQuotationResponse", targetNamespace = "http://myservice/", className = "client.GetHouseholdQuotationResponse")
    @Action(input = "http://myservice/MyService/getHouseholdQuotationRequest", output = "http://myservice/MyService/getHouseholdQuotationResponse")
    public HouseholdResponse getHouseholdQuotation(
        @WebParam(name = "HouseholdInfo", targetNamespace = "")
        HouseholdInfo householdInfo,
        @WebParam(name = "sessionID", targetNamespace = "")
        String sessionID);

    /**
     * 
     * @param travelInfo
     * @param sessionID
     * @return
     *     returns client.TravelResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTravelQuotation", targetNamespace = "http://myservice/", className = "client.GetTravelQuotation")
    @ResponseWrapper(localName = "getTravelQuotationResponse", targetNamespace = "http://myservice/", className = "client.GetTravelQuotationResponse")
    @Action(input = "http://myservice/MyService/getTravelQuotationRequest", output = "http://myservice/MyService/getTravelQuotationResponse")
    public TravelResponse getTravelQuotation(
        @WebParam(name = "travelInfo", targetNamespace = "")
        TravelInfo travelInfo,
        @WebParam(name = "sessionID", targetNamespace = "")
        String sessionID);

    /**
     * 
     * @param bookTravelInfo
     * @param sessionID
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "bookTravelPolicy", targetNamespace = "http://myservice/", className = "client.BookTravelPolicy")
    @ResponseWrapper(localName = "bookTravelPolicyResponse", targetNamespace = "http://myservice/", className = "client.BookTravelPolicyResponse")
    @Action(input = "http://myservice/MyService/bookTravelPolicyRequest", output = "http://myservice/MyService/bookTravelPolicyResponse")
    public String bookTravelPolicy(
        @WebParam(name = "BookTravelInfo", targetNamespace = "")
        TravelInfo bookTravelInfo,
        @WebParam(name = "sessionID", targetNamespace = "")
        String sessionID);

}