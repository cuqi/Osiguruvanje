package myservice;

public class Vehicle {
    public String chassis;
    public String registration;
    public int cc;
    public int manufYear;

    public Vehicle(){}

    public Vehicle(String chassis, String registration, int cc, int manuYear) {
        this.chassis = chassis;
        this.registration = registration;
        this.cc = cc;
        this.manufYear = manuYear;
    }
}
