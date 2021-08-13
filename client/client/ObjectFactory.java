
package client.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LoginMethodResponse_QNAME = new QName("http://myservice/", "loginMethodResponse");
    private final static QName _LoginMethod_QNAME = new QName("http://myservice/", "loginMethod");
    private final static QName _GenerateNum_QNAME = new QName("http://myservice/", "generateNum");
    private final static QName _GenerateNumResponse_QNAME = new QName("http://myservice/", "generateNumResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LoginMethodResponse }
     * 
     */
    public LoginMethodResponse createLoginMethodResponse() {
        return new LoginMethodResponse();
    }

    /**
     * Create an instance of {@link LoginMethod }
     * 
     */
    public LoginMethod createLoginMethod() {
        return new LoginMethod();
    }

    /**
     * Create an instance of {@link GenerateNum }
     * 
     */
    public GenerateNum createGenerateNum() {
        return new GenerateNum();
    }

    /**
     * Create an instance of {@link GenerateNumResponse }
     * 
     */
    public GenerateNumResponse createGenerateNumResponse() {
        return new GenerateNumResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginMethodResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "loginMethodResponse")
    public JAXBElement<LoginMethodResponse> createLoginMethodResponse(LoginMethodResponse value) {
        return new JAXBElement<LoginMethodResponse>(_LoginMethodResponse_QNAME, LoginMethodResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "loginMethod")
    public JAXBElement<LoginMethod> createLoginMethod(LoginMethod value) {
        return new JAXBElement<LoginMethod>(_LoginMethod_QNAME, LoginMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateNum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "generateNum")
    public JAXBElement<GenerateNum> createGenerateNum(GenerateNum value) {
        return new JAXBElement<GenerateNum>(_GenerateNum_QNAME, GenerateNum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateNumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "generateNumResponse")
    public JAXBElement<GenerateNumResponse> createGenerateNumResponse(GenerateNumResponse value) {
        return new JAXBElement<GenerateNumResponse>(_GenerateNumResponse_QNAME, GenerateNumResponse.class, null, value);
    }

}
