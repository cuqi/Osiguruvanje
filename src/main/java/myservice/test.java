package myservice;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.glassfish.pfl.basic.tools.argparser.Help;

import myservice.REST.Helpers;



public class test {
    public static void main(String[] args) throws NumberFormatException, IOException {

        String policyID = "CSC1001";
        // String [] elements = policyID.split("\\|");
        // System.out.println(elements[0] + " " + elements[1]);

        List<String> arrList = Helpers.getPolicies("all");
        for(int i = 0; i < arrList.size(); i++) {
            String [] temp = arrList.get(i).split("\\|");
            if (temp[0].equals(policyID)) {
                System.out.println("IT IS OK");
            }
        }
        // System.out.println(arrList.contains("CSC1001|616.0|FULL|NEW|Vladimir|Krstikj|1998-12-08</br>"));
        // boolean ok = Helpers.getPolicies("casco").contains(policyID);
        // System.out.println(ok);
        // try {
        //     int isOK = Helpers.findPolicyFromPolicyID(policyID);
        //     System.out.println(isOK);
        //     // if (Helpers.getPolicies("all").contains(policyID)) {
        //     //     String pol1 = Helpers.findPolicyFromPolicyID(policyID);
        //     //     System.out.println(pol1);
        //     // } else {
        //     //     System.out.println("kurac");
        //     // }
        // } catch (NumberFormatException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }


        // SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        // Date date = new Date();
        // String todaysDate = formatter.format(date); 
        // System.out.println(todaysDate);


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
