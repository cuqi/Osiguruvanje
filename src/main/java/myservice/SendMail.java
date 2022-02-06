package myservice;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;


public class SendMail {
    public static void sendmail(String emailTo, String policy) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication("osiguruvanje@outlook.com", "WebServisi2021");
           }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("osiguruvanje@outlook.com", false));
     
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
        msg.setSubject("Потврда за полиса број: " + policy);
      //   msg.setContent("PISI MI ODMAAA", "text/html");
        msg.setSentDate(new Date());
        
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Ви благодариме што не избравте нас! \n", "text/plain;charset=UTF-8");
    
        MimeBodyPart policyText = new MimeBodyPart();
        policyText.setContent(policy, "text/plain;charset=UTF-8");
     
        MimeBodyPart attachmentPolicy = new MimeBodyPart();
        attachmentPolicy.attachFile(new File("src\\main\\java\\data\\docxs\\invoice" + policy + ".docx"));

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(policyText);
        multipart.addBodyPart(attachmentPolicy);

        msg.setContent(multipart);
        Transport.send(msg);   


     }   

     public static void main(String[] args) {
         try {
            sendmail("krstik1212@gmail.com", "TRA1320013");
        } catch (MessagingException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }
}
