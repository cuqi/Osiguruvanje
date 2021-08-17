package myservice;

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
    CLASSIC_PLUS
}

public class TravelInfo {
    public TypeTravelPolicy type;
    public TypeCover cover;
    public int days;
    public String country;
    

    public TravelInfo() {
    }

    public TravelInfo(TypeTravelPolicy type, TypeCover cover, int days, String country) {
        this.type = type;
        this.cover = cover;
        this.days = days;
        this.country = country;
    }

    public int getSize() {
        return 4;
    } 
}
