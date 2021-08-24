package myservice;

enum TypeCasco {
    FULL,           // site rizici
    PARTIAL,        // rizici od pozhar, krazhba i razbojnishtvo   
    BASIC
}

enum TypeValue {
    MARKET,
    NEW
}
public class CascoInfo {
    public TypeCasco typeCasco;
    public boolean windows;         // dali se osiguruvaat i staklata?
    public int vehiclePrice;        // vrednosta na voziloto, premijata zavisi od ova
    public TypeValue typeValue;     // dali stanuva zbor za pazarna ili novonabavna vrednost 
    public int franchise;           // visina na franshiza mozhe da e 100 200 500 1000

    public CascoInfo() {

    }

    public CascoInfo(TypeCasco typeCasco, boolean windows, int vehiclePrice, TypeValue typeValue, int franchise) {
        this.typeCasco = typeCasco;
        this.windows = windows;
        this.vehiclePrice = vehiclePrice;
        this.typeValue = typeValue; 
        this.franchise = franchise;
    }
}
