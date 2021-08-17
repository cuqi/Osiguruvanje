package myservice;

import java.util.Date;

public class Insured {
    public String firstName;
    public String lastName;
    public Date dateOfBirth;
    public String ssn;

    public Insured() {}

    public Insured(String firstName, String lastName, Date dateOfBirth, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
    }
}
