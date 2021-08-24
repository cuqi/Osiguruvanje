package myservice;

public class BookResponse {
    public String message; 
    public int code;
    public String policyID;

    public BookResponse() {}

    public BookResponse(String message, int code, String policyID) {
        this.message = message;
        this.code = code;
        this.policyID = policyID;
    }
}
