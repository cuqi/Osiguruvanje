package myservice;

public class QuotationResponse {
    public String message;
    public int code;
    public float premium;

    public QuotationResponse() {
    }

    public QuotationResponse(String message, int code, float premium) {
        this.message = message;
        this.code = code;
        this.premium = premium;
    }
}
