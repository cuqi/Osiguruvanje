package myservice;

public class InsuredInfo {
    public String firstName;
    public String lastName;
    public String ssn;
    public String address;
    public String postalCode;
    public String city;


    public InsuredInfo() {}

    public InsuredInfo(String firstName, String lastName, String ssn, String address, String postalCode, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }

    
}
