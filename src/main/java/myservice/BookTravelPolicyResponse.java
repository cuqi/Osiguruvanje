package myservice;

public class BookTravelPolicyResponse {
    public String message; 
    public int code;

    public BookTravelPolicyResponse() {}

    public BookTravelPolicyResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
