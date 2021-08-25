package myservice;

public class ConfirmResponse {
    public String message;
    public int code;
    
    public ConfirmResponse() {}

    public ConfirmResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
