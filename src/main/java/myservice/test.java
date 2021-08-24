package myservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Request;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;


public class test {
    public static void main(String[] args) {
        // String ssn = "0812998451234";
        // int birthDay = Integer.parseInt(ssn.substring(0, 2));
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-yy");
        // Date date = new Date();
        // String todaysDate = formatter.format(date); 
        // System.out.println(todaysDate);


        BraintreeGateway gateway = new BraintreeGateway("access_token$sandbox$dz75b273p2gnb456$5af09ab9ecb4cdaef5b70a3f7f6bebfa");
        TransactionRequest request = new TransactionRequest()
        .amount(new BigDecimal(100))
        .paymentMethodNonce("online");

        Result<Transaction> saleResult = gateway.transaction().sale(request);

        if (saleResult.isSuccess()) {
            Transaction transaction = saleResult.getTarget();
            System.out.println("SuccessID " + transaction.getId());
        } else {
            System.out.println("Message: " + saleResult.getMessage());
        }

    }
}
