package myservice;

enum TypeObject {
    RESIDENCE,
    BUSINESS,
    PORTABLE
}

enum TypeHouseholdCover {
    STANDARD,
    COMFORT,
    MEGA
}

public class HouseholdInfo {
    public TypeObject typeObject;                   // house, apartment 
    public float areaOfObject;                      // kvadratura na objektot
    public String dateOfObject;                     // datum na izgradba na objektot
    public TypeHouseholdCover typeHouseholdCover;   // tip na pokritieto
    public int contractLenght;                      // vremetraenje na dogovorot 1, 3 ili 5

    public HouseholdInfo() {

    }

    public HouseholdInfo(TypeObject typeObject, float areaOfObject, String dateOfObject, TypeHouseholdCover typeHouseholdCover, int contractLenght) {
        this.typeObject = typeObject;
        this.areaOfObject = areaOfObject;
        this.dateOfObject = dateOfObject;
        this.typeHouseholdCover = typeHouseholdCover;
        this.contractLenght = contractLenght;
    }
}
