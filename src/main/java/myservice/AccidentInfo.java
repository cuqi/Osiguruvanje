package myservice;

import java.util.Date;

public class AccidentInfo {
    public Date startDate; 
    public boolean isStudent;
    public int pack; 

    public AccidentInfo() {

    } 

    public AccidentInfo(Date startDate, boolean isStudent, int pack) {
        this.startDate = startDate;
        this.isStudent = isStudent;
        this.pack = pack;
    }
}
