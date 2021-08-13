package myservice;

public class TravelResponse {
    public String message;
    public int code;

    public TravelResponse() {
    }

    public TravelResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
