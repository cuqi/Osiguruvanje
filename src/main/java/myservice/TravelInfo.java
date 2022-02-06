package myservice;

import java.sql.Date;

enum TypeTravelPolicy {
    INDIVIDUAL,
    FAMILY,
    STUDENT,
    GROUP,
    BUSINESS
}

enum TypeCover {
    VISA,
    CLASSIC,
    VIP
}

public class TravelInfo {
    public TypeTravelPolicy type;
    public TypeCover cover;
    public int days;
    public String country;
    public int numPeople;


    public TravelInfo() {
    }

    public TravelInfo(TypeTravelPolicy type, TypeCover cover, int days, String country, int numPeople) {
        this.type = type;
        this.cover = cover;
        this.days = days;
        this.country = country;
        this.numPeople = numPeople;
    }
}
