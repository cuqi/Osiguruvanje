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
    VIP
}

public class TravelInfo {
    public TypeTravelPolicy type;
    public TypeCover cover;
    public int days;
    public String country;
    public int numPeople;
    boolean isbelow18;
    boolean isabove65;
    

    public TravelInfo() {
    }

    public TravelInfo(TypeTravelPolicy type, TypeCover cover, int days, String country, int numPeople, boolean isbelow18, boolean isabove65) {
        this.type = type;
        this.cover = cover;
        this.days = days;
        this.country = country;
        this.numPeople = numPeople;
        this.isabove65 = isabove65;
        this.isbelow18 = isbelow18;
    }
}
