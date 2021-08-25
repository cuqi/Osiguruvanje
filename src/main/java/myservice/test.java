package myservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;



public class test {
    public static void main(String[] args) {

        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        Date date = new Date();
        String todaysDate = formatter.format(date); 
        System.out.println(todaysDate);


        // BraintreeGateway gateway = new BraintreeGateway("access_token$sandbox$dz75b273p2gnb456$5af09ab9ecb4cdaef5b70a3f7f6bebfa");
        // TransactionRequest request = new TransactionRequest()
        // .amount(new BigDecimal(100))
        // .paymentMethodNonce("online");

        // Result<Transaction> saleResult = gateway.transaction().sale(request);

        // if (saleResult.isSuccess()) {
        //     Transaction transaction = saleResult.getTarget();
        //     System.out.println("SuccessID " + transaction.getId());
        // } else {
        //     System.out.println("Message: " + saleResult.getMessage());
        // }

    }
}
