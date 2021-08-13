package myservice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        // String ssn = "0812998451234";
        // int birthDay = Integer.parseInt(ssn.substring(0, 2));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String todaysDate = formatter.format(date); 
        System.out.println(todaysDate);
    }
}
