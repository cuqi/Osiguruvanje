package myservice;

import java.util.Date;

public class InsuredInfo {
    public String firstName;
    public String lastName;
    public Date dateOfBirth;
    public String ssn;

    public InsuredInfo() {}

    public InsuredInfo(String firstName, String lastName, Date dateOfBirth, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
    }
}
