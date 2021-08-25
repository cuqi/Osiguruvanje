package myservice;

import java.util.Date;

public class CreditCardInfo {
    public String creditCardNumber;
    public Date expiryDate;
    public int CVV;    

    public CreditCardInfo() {

    }

    public CreditCardInfo(String creditCardNumber, Date expiryDate, int CVV) {
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
    }
}
