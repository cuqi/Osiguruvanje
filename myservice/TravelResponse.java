package myservice;

public class TravelResponse {
    public String message;
    public int code;
    public float premium;

    public TravelResponse() {
    }

    public TravelResponse(String message, int code, float premium) {
        this.message = message;
        this.code = code;
        this.premium = premium;
    }

}
