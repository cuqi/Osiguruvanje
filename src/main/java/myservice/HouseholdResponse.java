package myservice;

public class HouseholdResponse {
    public String message;
    public int code;

    public HouseholdResponse() {
    }

    public HouseholdResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
