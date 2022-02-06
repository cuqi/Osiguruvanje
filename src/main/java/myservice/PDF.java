package myservice;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.mail.MessagingException;

import com.spire.doc.Document;
import com.spire.doc.DocumentBase;
import com.spire.doc.FileFormat;

import org.apache.tomcat.util.codec.binary.StringUtils;

public class PDF {
    public static void fillPDF(InsuredInfo contractor, InsuredInfo insured, String startDate, String endDate, String creationDate, String paymentRef, String sessionID, String username, String email, String policyID, String premium, String id, String policyType) throws FileNotFoundException {
        Document doc = new Document();

        doc.loadFromFile("src\\main\\java\\data\\pdfs\\invoice.docx");

        doc.replace(":username", username, true, true);
        doc.replace(":email", email, true, true);
        doc.replace(":payment_ref", paymentRef, true, true);
        doc.replace(":session_id", sessionID, true, true);
        doc.replace(":start_date", startDate, true, true);
        doc.replace(":expiry_date", endDate, true, true);
        doc.replace(":creation_date", creationDate, true, true);
        doc.replace(":id", id, true, true);
        doc.replace(":contractor_first_name", contractor.firstName, true, true);
        doc.replace(":contractor_last_name", contractor.lastName, true, true);
        doc.replace(":contractor_id", contractor.ssn, true, true);
        doc.replace(":contractor_address", contractor.address, true, true);
        doc.replace(":contractor_city", contractor.city, true, true);
        doc.replace(":insured_first_name", insured.firstName, true, true);
        doc.replace(":insured_last_name", insured.lastName, true, true);
        doc.replace(":insured_id", insured.ssn, true, true);
        doc.replace(":insured_address", insured.address, true, true);
        doc.replace(":insured_city", insured.city, true, true);
        doc.replace(":policy_id", policyID, true, true);
        doc.replace(":policy_type", policyType, true, true);
        doc.replace(":premium", premium, true, true);

        doc.saveToFile("src\\main\\java\\data\\docxs\\invoice" + policyID + ".docx", FileFormat.Docx);

        try {
            SendMail.sendmail(email, policyID);
        } catch (MessagingException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
