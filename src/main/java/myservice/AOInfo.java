package myservice;

public class AOInfo {
    public String regNum;
    public String chassis;
    public boolean isNew;
    public int KW;

    public AOInfo() {

    }

    public AOInfo(String regNum, String chassis, boolean isNew, int KW) {
        this.regNum = regNum;
        this.chassis = chassis;
        this.isNew = isNew;
        this.KW = KW;
    }
}
