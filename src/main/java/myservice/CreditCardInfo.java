package myservice;

import java.util.Date;

public class CreditCardInfo {
    public String creditCardNumber;
    public Date expiryDate;
    public String CVV;    

    public CreditCardInfo() {

    }

    public CreditCardInfo(String creditCardNumber, Date expiryDate, String CVV) {
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
    }
}
