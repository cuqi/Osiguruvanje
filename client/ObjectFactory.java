
package client;

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

    private final static QName _GetHouseholdQuotation_QNAME = new QName("http://myservice/", "getHouseholdQuotation");
    private final static QName _RegisterResponse_QNAME = new QName("http://myservice/", "registerResponse");
    private final static QName _GetTravelQuotationResponse_QNAME = new QName("http://myservice/", "getTravelQuotationResponse");
    private final static QName _GenerateNumResponse_QNAME = new QName("http://myservice/", "generateNumResponse");
    private final static QName _Register_QNAME = new QName("http://myservice/", "register");
    private final static QName _GetHouseholdQuotationResponse_QNAME = new QName("http://myservice/", "getHouseholdQuotationResponse");
    private final static QName _LoginMethodResponse_QNAME = new QName("http://myservice/", "loginMethodResponse");
    private final static QName _UnregisterResponse_QNAME = new QName("http://myservice/", "unregisterResponse");
    private final static QName _BookTravelPolicy_QNAME = new QName("http://myservice/", "bookTravelPolicy");
    private final static QName _LoginMethod_QNAME = new QName("http://myservice/", "loginMethod");
    private final static QName _IOException_QNAME = new QName("http://myservice/", "IOException");
    private final static QName _NoSuchAlgorithmException_QNAME = new QName("http://myservice/", "NoSuchAlgorithmException");
    private final static QName _BookTravelPolicyResponse_QNAME = new QName("http://myservice/", "bookTravelPolicyResponse");
    private final static QName _GenerateNum_QNAME = new QName("http://myservice/", "generateNum");
    private final static QName _Unregister_QNAME = new QName("http://myservice/", "unregister");
    private final static QName _GetTravelQuotation_QNAME = new QName("http://myservice/", "getTravelQuotation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UnregisterResponse }
     * 
     */
    public UnregisterResponse createUnregisterResponse() {
        return new UnregisterResponse();
    }

    /**
     * Create an instance of {@link GetHouseholdQuotationResponse }
     * 
     */
    public GetHouseholdQuotationResponse createGetHouseholdQuotationResponse() {
        return new GetHouseholdQuotationResponse();
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
     * Create an instance of {@link BookTravelPolicy }
     * 
     */
    public BookTravelPolicy createBookTravelPolicy() {
        return new BookTravelPolicy();
    }

    /**
     * Create an instance of {@link Unregister }
     * 
     */
    public Unregister createUnregister() {
        return new Unregister();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link NoSuchAlgorithmException }
     * 
     */
    public NoSuchAlgorithmException createNoSuchAlgorithmException() {
        return new NoSuchAlgorithmException();
    }

    /**
     * Create an instance of {@link BookTravelPolicyResponse }
     * 
     */
    public BookTravelPolicyResponse createBookTravelPolicyResponse() {
        return new BookTravelPolicyResponse();
    }

    /**
     * Create an instance of {@link GenerateNum }
     * 
     */
    public GenerateNum createGenerateNum() {
        return new GenerateNum();
    }

    /**
     * Create an instance of {@link GetTravelQuotation }
     * 
     */
    public GetTravelQuotation createGetTravelQuotation() {
        return new GetTravelQuotation();
    }

    /**
     * Create an instance of {@link GetHouseholdQuotation }
     * 
     */
    public GetHouseholdQuotation createGetHouseholdQuotation() {
        return new GetHouseholdQuotation();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link GetTravelQuotationResponse }
     * 
     */
    public GetTravelQuotationResponse createGetTravelQuotationResponse() {
        return new GetTravelQuotationResponse();
    }

    /**
     * Create an instance of {@link GenerateNumResponse }
     * 
     */
    public GenerateNumResponse createGenerateNumResponse() {
        return new GenerateNumResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link TravelResponse }
     * 
     */
    public TravelResponse createTravelResponse() {
        return new TravelResponse();
    }

    /**
     * Create an instance of {@link TravelInfo }
     * 
     */
    public TravelInfo createTravelInfo() {
        return new TravelInfo();
    }

    /**
     * Create an instance of {@link HouseholdInfo }
     * 
     */
    public HouseholdInfo createHouseholdInfo() {
        return new HouseholdInfo();
    }

    /**
     * Create an instance of {@link HouseholdResponse }
     * 
     */
    public HouseholdResponse createHouseholdResponse() {
        return new HouseholdResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHouseholdQuotation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "getHouseholdQuotation")
    public JAXBElement<GetHouseholdQuotation> createGetHouseholdQuotation(GetHouseholdQuotation value) {
        return new JAXBElement<GetHouseholdQuotation>(_GetHouseholdQuotation_QNAME, GetHouseholdQuotation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTravelQuotationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "getTravelQuotationResponse")
    public JAXBElement<GetTravelQuotationResponse> createGetTravelQuotationResponse(GetTravelQuotationResponse value) {
        return new JAXBElement<GetTravelQuotationResponse>(_GetTravelQuotationResponse_QNAME, GetTravelQuotationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateNumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "generateNumResponse")
    public JAXBElement<GenerateNumResponse> createGenerateNumResponse(GenerateNumResponse value) {
        return new JAXBElement<GenerateNumResponse>(_GenerateNumResponse_QNAME, GenerateNumResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHouseholdQuotationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "getHouseholdQuotationResponse")
    public JAXBElement<GetHouseholdQuotationResponse> createGetHouseholdQuotationResponse(GetHouseholdQuotationResponse value) {
        return new JAXBElement<GetHouseholdQuotationResponse>(_GetHouseholdQuotationResponse_QNAME, GetHouseholdQuotationResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link UnregisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "unregisterResponse")
    public JAXBElement<UnregisterResponse> createUnregisterResponse(UnregisterResponse value) {
        return new JAXBElement<UnregisterResponse>(_UnregisterResponse_QNAME, UnregisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookTravelPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "bookTravelPolicy")
    public JAXBElement<BookTravelPolicy> createBookTravelPolicy(BookTravelPolicy value) {
        return new JAXBElement<BookTravelPolicy>(_BookTravelPolicy_QNAME, BookTravelPolicy.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchAlgorithmException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "NoSuchAlgorithmException")
    public JAXBElement<NoSuchAlgorithmException> createNoSuchAlgorithmException(NoSuchAlgorithmException value) {
        return new JAXBElement<NoSuchAlgorithmException>(_NoSuchAlgorithmException_QNAME, NoSuchAlgorithmException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookTravelPolicyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "bookTravelPolicyResponse")
    public JAXBElement<BookTravelPolicyResponse> createBookTravelPolicyResponse(BookTravelPolicyResponse value) {
        return new JAXBElement<BookTravelPolicyResponse>(_BookTravelPolicyResponse_QNAME, BookTravelPolicyResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Unregister }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "unregister")
    public JAXBElement<Unregister> createUnregister(Unregister value) {
        return new JAXBElement<Unregister>(_Unregister_QNAME, Unregister.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTravelQuotation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice/", name = "getTravelQuotation")
    public JAXBElement<GetTravelQuotation> createGetTravelQuotation(GetTravelQuotation value) {
        return new JAXBElement<GetTravelQuotation>(_GetTravelQuotation_QNAME, GetTravelQuotation.class, null, value);
    }

}
