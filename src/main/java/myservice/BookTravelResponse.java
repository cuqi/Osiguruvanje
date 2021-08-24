package myservice;

public class BookTravelResponse {
    public String message; 
    public int code;
    public String policyID;

    public BookTravelResponse() {}

    public BookTravelResponse(String message, int code, String policyID) {
        this.message = message;
        this.code = code;
        this.policyID = policyID;
    }
}
