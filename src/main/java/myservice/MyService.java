package myservice;

import myservice.REST.Helpers;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Scanner;
import java.util.Base64.Encoder;

@WebService
public class MyService {

    @WebMethod // soap 1.1 
    public String loginMethod(@WebParam(name = "username")String username, @WebParam(name = "password")String password) throws NoSuchAlgorithmException {
        try {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            File file = new File("src\\main\\java\\data\\registeredUsers.txt");
            Scanner scanner = new Scanner(file);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8)
            );
            String hashedPassword = bytesToHex(encodedhash);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String userInfo[] = line.split(" ");
                if (username.equals(userInfo[0]) && hashedPassword.equals(userInfo[1])) {
                    scanner.close();
                    SecureRandom random = new SecureRandom();
                    byte bytes[] = new byte[20];
                    random.nextBytes(bytes);
                    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
                    String token = encoder.encodeToString(bytes);

                    FileWriter file2 = new FileWriter("src\\main\\java\\data\\sessions.txt", true);
                    file2.write("\n" + userInfo[2] + " " + token);
                    file2.close();
                    return token;
                } 
            }
            scanner.close();
            return "There was an error!";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "File cannot be found";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @WebMethod
    public String register(@WebParam(name = "username")String username, @WebParam(name = "password1")String password1, @WebParam(name = "password2")String password2, @WebParam(name = "email")String email) throws NoSuchAlgorithmException {
        try{
            FileWriter file = new FileWriter("src\\main\\java\\data\\registeredUsers.txt", true);
            String newUser = "";
            if (password1.equals(password2)) {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest(
                password1.getBytes(StandardCharsets.UTF_8)
                );
                String hashedPassword = bytesToHex(encodedhash);
                newUser = username + " " + hashedPassword + " " + email + "\r\n";
            } 
            file.write(newUser);
            file.close();
            return "Успешно се регистриравте!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Неуспешно, настана грешка!";
        }
    }

    @WebMethod
    public String unregister(@WebParam(name = "username")String username, @WebParam(name = "password1")String password1, @WebParam(name = "password2")String password2) throws IOException, NoSuchAlgorithmException{
        boolean flag = false;
        if(!password1.equals(password2)) {
            return "Внесените пасворди се различни";
        } else {
            try {
                File inputFile = new File("src\\main\\java\\data\\registeredUsers.txt");
                File tempFile = new File("src\\main\\java\\data\\tempusers.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                String currentLine;
                
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest(
                password1.getBytes(StandardCharsets.UTF_8)
                );
                String hashedPassword = bytesToHex(encodedhash);

                if((currentLine = reader.readLine()) != null) {
                    String[] entries = currentLine.split(" ");
                    // System.out.println(entries[0] + " " + entries[1]);
                    if(entries[0].equals(username) && entries[1].equals(hashedPassword)) {
                        flag = true;
                    } else {
                        writer.write(currentLine);
                    }
                }

                while((currentLine = reader.readLine()) != null) {
                    String[] entries = currentLine.split(" ");
                    if(entries[0].equals(username) && entries[1].equals(hashedPassword)) {
                        flag = true;
                        continue;
                    } 
                    writer.write(System.getProperty("line.separator") + currentLine);
                }
                writer.close(); 
                reader.close(); 
                
                inputFile.delete();
                tempFile.renameTo(inputFile);

                if (flag) {
                    return "User-от е избришан";
                } else {
                    return "Погрешен username или password";
                }

                } catch (FileNotFoundException e) {
                    return "Server error";
                }
        }
    }

    @WebMethod 
    public QuotationResponse getTravelQuotation(@WebParam(name = "travelInfo")TravelInfo travelInfo, @WebParam(name = "sessionID")String sessionID) {
        double premium = 0.0;
        if (sessionID.equals("")) {
            return new QuotationResponse("No sessionID", 101, 0);
        } else if (checkSessionID(sessionID) == 0) {
            return new QuotationResponse("Session ID e погрешно!", 102, 0);
        }

        if (travelInfo.type == null) {
            return new QuotationResponse("Ве молиме внесете го типот на патничка полиса(INDIVIDUAL, FAMILY, STUDENT, GROUP, BUSINESS)", 110, 0);
        } else {
            switch(travelInfo.type) {
                case INDIVIDUAL:
                    premium += 100;
                    break;
                case FAMILY:
                    premium += 110;
                    break;
                case STUDENT:
                    premium += 80;
                    break;
                case GROUP:
                    premium += 90;
                    break;
                case BUSINESS:
                    premium += 95;
                    break;
            }
        }
        
        if (travelInfo.cover == null) {
            return new QuotationResponse("Ве молиме внесете го покритието за патничката полиса(VISA, CLASSIC, CLASSIC_PLUS)", 111, 0);
        } else {
            switch(travelInfo.cover) {
                case VISA:
                    premium += travelInfo.days * 0.8;
                    break;
                case CLASSIC:
                    premium += travelInfo.days * 1;
                    break;
                case CLASSIC_PLUS:
                    premium += travelInfo.days * 1.25;
                    break;
            }
        }
        
        return new QuotationResponse("Премијата за полисата изнесува: " + String.valueOf((int)premium + "ЕУР"), 100, (float) premium);
    }

    @WebMethod 
    public QuotationResponse getHouseholdQuotation(@WebParam(name = "HouseholdInfo")HouseholdInfo householdInfo, @WebParam(name = "sessionID")String sessionID) {
        double premium = 0.0;
        if (sessionID.equals("")) {
            return new QuotationResponse("No session ID", 101, 0);
        } else if (checkSessionID(sessionID) == 0){
            return new QuotationResponse("Session ID e погрешно!", 102, 0);
        }

        if (householdInfo.typeObject == null) {
            return new QuotationResponse("Ве молиме внесете го типот на објектот(HOUSE, APARTMENT)", 111, 0);
        } else {
            if (householdInfo.typeObject.equals(TypeObject.HOUSE)) {
                premium += 12.0;
            } else if (householdInfo.typeObject.equals(TypeObject.APARTMENT)) {
                premium += 10.0;
            }
        }

        // dodavanje na premijata vo zavisnost od kvadraturata;
        premium += householdInfo.areaOfObject * 0.25;

        // dodavanje na premijata vo zavisnost od tipot na pokritieto
        if (householdInfo.typeHouseholdCover == null) {
            return new QuotationResponse("Ве молиме внесете го типот на покритието(STANDARD, COMFORT, MEGA)", 112, 0);
        } else {
            switch(householdInfo.typeHouseholdCover) {
                case STANDARD:
                    premium += 25;
                    break;
                case COMFORT:
                    premium += 40;
                    break;
                case MEGA:
                    premium += 50;
                    break;
            }
        }

        switch(householdInfo.contractLenght) {
            case 1:
                premium -= (premium*10)/100;
                break;
            case 3:
                premium -= (premium*15)/100;
                break;
            case 5:
                premium -= (premium*20)/100;
                break;
            default:
                return new QuotationResponse("Ве молиме внесете 1, 3 или 5 во полето за должина на договорот.", 113, 0);
        }
        
        return new QuotationResponse("Висината на премијата е: " + String.valueOf((int)premium) + " ЕУР", 100, (int) premium);
    }

    @WebMethod 
    public QuotationResponse getCascoQuotation(@WebParam(name = "CascoInfo")CascoInfo cascoInfo, @WebParam(name = "sessionID")String sessionID) {
        double premium = 0;
        if (sessionID.equals("")) {
            return new QuotationResponse("Ви недостасува број на сесија!", 101, 0);
        } else if (checkSessionID(sessionID) == 0){
            return new QuotationResponse("Session ID e погрешно!", 102, 0);
        }

        if (cascoInfo.typeCasco == null) {
            return new QuotationResponse("Ве молиме внесете го типот на каско полисата(FULL, PARTIAL, BASIC)", 111, 0);
        } else {
            if (cascoInfo.typeCasco.equals(TypeCasco.FULL)) {
                premium += 500;
            } else if (cascoInfo.typeCasco.equals(TypeCasco.PARTIAL)) {
                premium += 350;
            } else if (cascoInfo.typeCasco.equals(TypeCasco.BASIC)) {
                premium += 275;
            }
        }

        if (cascoInfo.typeValue == null ) {
            return new QuotationResponse("Ве молиме внесете по кој основ се осигурува сумата на возилото(NEW за новонабавна, MARKET за пазарна вредност)", 112, 0);
        } else {
            if (cascoInfo.typeValue.equals(TypeValue.NEW)) {
                premium += ((double) cascoInfo.vehiclePrice * 0.01)/60;
            } else if (cascoInfo.typeValue.equals(TypeValue.MARKET)) {
                premium += ((double) cascoInfo.vehiclePrice * 0.015)/60;
            }
        }

        if (cascoInfo.windows == true && !cascoInfo.typeCasco.equals(TypeCasco.FULL)) {
            premium += 50;
        } 

        switch(cascoInfo.franchise) {
            case 100:
                premium -= 10;
                break;
            case 200:
                premium -= 20;
                break;
            case 500:
                premium -= 50;
                break;
            case 1000:
                premium -= 100;
                break;
            default:
                return new QuotationResponse("Ве молиме внесете 100, 200, 500 или 1000 како можни вредности за франшиза", 113, 0);
        }

        return new QuotationResponse("Висината на премијата е: " + String.valueOf((int)premium) + " ЕУР", 100, (int) premium);
    }

    @WebMethod
    public BookResponse bookTravelPolicy(@WebParam(name = "BookTravelInfo")TravelInfo bookTravelInfo, @WebParam(name = "Insured")InsuredInfo insured, @WebParam(name = "sessionID")String sessionID) {
        String policyID = "";
        float premium = 0;
        String typePolicy = "TRA";
        QuotationResponse travelResponse =  getTravelQuotation(bookTravelInfo, sessionID);
        premium = travelResponse.premium;
        if (premium == 0) {
            return new BookResponse(travelResponse.message, travelResponse.code, "0");
        }

        if (insured.firstName.equals("")) {
            return new BookResponse("Ве молиме внесете го името на осигуреникот!", 111, "0");
        } 

        if (insured.lastName.equals("")) {
            return new BookResponse("Ве молиме внесете го презимето на осигуреникот!", 112, "0");
        } 

        int ageOfInsured = 0;
        if (insured.ssn.equals("")) {
            return new BookResponse("Ве молиме внесете го матичниот број на осигуреникот!", 113, "0");
        } else if (insured.ssn.length() != 13) {
            return new BookResponse("Ве молиме внесете за матичниот број внесете 13 цифри!", 113, "0");
        } 
        else {
            ageOfInsured = getAgeFromSSN(insured.ssn);
        }

        String todaysDateString = "";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        todaysDateString = format.format(date); 
        try{
            FileWriter file = new FileWriter("src\\main\\java\\data\\travelPolicies.txt", true);
            policyID = typePolicy + replaceSelected(typePolicy);
            String newPolicy = policyID + "|" + String.valueOf(premium) + "|" + bookTravelInfo.type.toString() + "|" + bookTravelInfo.cover.toString() + "|" + insured.firstName + "|" + insured.lastName + "|"+ sessionID + "|" + String.valueOf(ageOfInsured) + "|" + todaysDateString + "|OP" + "\n";
            file.write(newPolicy);
            file.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        return new BookResponse("Успешно е креирана полисата со сериски број: " + policyID, 100, policyID);
    }

    @WebMethod
    public BookResponse bookHouseholdPolicy(@WebParam(name = "HouseholdInfo")HouseholdInfo householdInfo, @WebParam(name = "Insured") InsuredInfo insured, @WebParam(name = "sessionID")String sessionID) {
        String policyID = "";
        float premium = 0;
        String typePolicy = "HHL";
        QuotationResponse householdResponse =  getHouseholdQuotation(householdInfo, sessionID);
        premium = householdResponse.premium;
        if (premium == 0) {
            return new BookResponse(householdResponse.message, householdResponse.code, "0");
        }

        if (insured.firstName.equals("")) {
            return new BookResponse("Ве молиме внесете го името на осигуреникот!", 111, "0");
        } 

        if (insured.lastName.equals("")) {
            return new BookResponse("Ве молиме внесете го презимето на осигуреникот!", 112, "0");
        } 

        int ageOfInsured = 0;
        if (insured.ssn.equals("")) {
            return new BookResponse("Ве молиме внесете го матичниот број на осигуреникот!", 113, "0");
        } else if (insured.ssn.length() != 13) {
            return new BookResponse("Ве молиме внесете за матичниот број внесете 13 цифри!", 113, "0");
        }
        else {
            ageOfInsured = getAgeFromSSN(insured.ssn);
        }

        String todaysDateString = "";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        todaysDateString = format.format(date); 

        try{
            FileWriter file = new FileWriter("src\\main\\java\\data\\householdPolicies.txt", true);
            policyID = typePolicy + replaceSelected(typePolicy);
            String newPolicy = policyID + "|" + String.valueOf(premium) + "|" + householdInfo.typeObject.toString() + "|" + householdInfo.typeHouseholdCover.toString() + "|" + insured.firstName + "|" + insured.lastName + "|" + sessionID + "|" + String.valueOf(ageOfInsured) + "|" + todaysDateString + "\n";
            file.write(newPolicy);
            file.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BookResponse("Успешно е креирана полисата со сериски број: " + policyID, 100, policyID);
    }

    @WebMethod
    public BookResponse bookCascoPolicy(@WebParam(name = "CascoInfo")CascoInfo cascoInfo, @WebParam(name = "Insured") InsuredInfo insured, @WebParam(name = "sessionID")String sessionID) {
        String policyID = "";
        float premium = 0;
        String typePolicy = "CSC";
        QuotationResponse cascoResponse =  getCascoQuotation(cascoInfo, sessionID);
        premium = cascoResponse.premium;
        if (premium == 0) {
            return new BookResponse(cascoResponse.message, cascoResponse.code, "0");
        }

        if (insured.firstName.equals("")) {
            return new BookResponse("Ве молиме внесете го името на осигуреникот!", 111, "0");
        } 

        if (insured.lastName.equals("")) {
            return new BookResponse("Ве молиме внесете го презимето на осигуреникот!", 112, "0");
        } 

        int ageOfInsured = 0;
        if (insured.ssn.equals("")) {
            return new BookResponse("Ве молиме внесете го матичниот број на осигуреникот!", 113, "0");
        } else if (insured.ssn.length() != 13) {
            return new BookResponse("Ве молиме внесете за матичниот број внесете 13 цифри!", 113, "0");
        }
        else {
            ageOfInsured = getAgeFromSSN(insured.ssn);
        }

        String todaysDateString = "";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        todaysDateString = format.format(date); 

        try{
            FileWriter file = new FileWriter("src\\main\\java\\data\\cascoPolicies.txt", true);
            policyID = typePolicy + replaceSelected(typePolicy);
            String newPolicy = policyID + "|" + String.valueOf(premium) + "|" + cascoInfo.typeCasco.toString() + "|" + cascoInfo.typeValue.toString() + "|" + insured.firstName + "|" + insured.lastName + "|" + sessionID + "|" + String.valueOf(ageOfInsured) + "|" + todaysDateString + "\n";
            file.write(newPolicy);
            file.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BookResponse("Успешно е креирана полисата со сериски број: " + policyID, 100, policyID);
    }

    @WebMethod
    public ConfirmResponse confirmPolicy(@WebParam(name = "policyID")String policyID, @WebParam(name = "creditCardInfo") CreditCardInfo creditCardInfo, @WebParam(name = "sessionID") String sessionID){
        
        if (checkSessionID(sessionID) == 0) {
            return new ConfirmResponse("Бројот на сесија е невалиден!", 117);
        }

        if (creditCardInfo.creditCardNumber.equals("")) {
            return new ConfirmResponse("Внесете број на картичка", 111);
        } else if (creditCardInfo.creditCardNumber.length() != 16) {
            return new ConfirmResponse("Внесете 16 цифри за број на картичка", 112);
        }
        
        if (creditCardInfo.expiryDate == null) {
            return new ConfirmResponse("Внесете го датумот на истекување на картичката(формат: mm/yyyy)", 113);
        } else {
            Date date = new Date();
            if (creditCardInfo.expiryDate.before(date)) {
                return new ConfirmResponse("Истечена ви е картичката", 114);
            }
        }
        
        if (creditCardInfo.CVV.length() != 3) {
            return new ConfirmResponse("Внесете 3 цифри за CVV", 115);
        }

        if (policyID.equals("")) {
            return new ConfirmResponse("Внесете број на полиса!", 110);
        } else {
            try {
                if (Helpers.getPolicies("all").contains(policyID)) {
                    
                } else {
                    return new ConfirmResponse("Бројот на полиса е невалиден!", 116);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ConfirmResponse("Полисата со број: " + policyID + " е успешно платена", 100);
    }



    @WebMethod
    public double converter(@WebParam(name = "EUR") String evra) {
        return Integer.valueOf(evra) * 61.5;
    }

    /*
        POMOSHNI FUNKCII / NE SE DEL OD SERVISOT
    */

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static int getAgeFromSSN(String ssn)
    {
        // 0812998
        int birthDay = Integer.parseInt(ssn.substring(0, 2));
        int birthMonth = Integer.parseInt(ssn.substring(2, 4));
        int birthYear = Integer.parseInt(ssn.substring(4, 7));
        birthYear += 1000;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String todaysDate = formatter.format(date); 

        // 07/06/2021
        int toDay = Integer.parseInt(todaysDate.substring(0, 2));
        int toMonth = Integer.parseInt(todaysDate.substring(3, 5));
        int toYear = Integer.parseInt(todaysDate.substring(6));

        if(toMonth > birthMonth)
        {
           return toYear - birthYear;
        }
        else if (toMonth == birthMonth)
        {
            if(toDay >= birthDay)
            {
                return toYear - birthYear;
            }
            else
            {
                return toYear - birthYear - 1;
            }
        }
        else
        {
            return toYear - birthYear - 1;
        }
    }

    public static String replaceSelected(String typePolicy) {
        String fileToOpen = "";
        if (typePolicy.equals("TRA")) {
            fileToOpen = "src\\main\\java\\data\\travelPolicyEvidence.txt";
        } else if (typePolicy.equals("HHL")) {
            fileToOpen = "src\\main\\java\\data\\householdPolicyEvidence.txt";
        } else if (typePolicy.equals("CSC")) {
            fileToOpen = "src\\main\\java\\data\\cascoPolicyEvidence.txt";
        }
            File fileToBeModified = new File(fileToOpen);
            String oldString = "";
            BufferedReader reader = null;
            FileWriter writer = null;
            try {
                reader = new BufferedReader(new FileReader(fileToBeModified));
                String line = reader.readLine();
                while (line != null) {
                    oldString = oldString + line;
                    line = reader.readLine();
                }
                String newString = String.valueOf(Integer.valueOf(oldString) + 1);
                writer = new FileWriter(fileToBeModified);
                writer.write(newString);
                return newString;
            } catch (IOException e) {
                e.printStackTrace();
                return "Error reading file!";
            } finally {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                
            }
    }   

    public static int checkSessionID(String sessionID) {
        int found = 0;
        try {
            File file = new File("src\\main\\java\\data\\sessions.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String sessionInfo[] = line.split(" ");
                if (sessionID.equals(sessionInfo[1])) {
                    found = 1;
                    break;
                } else {
                    found = 0;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            found = 0;
            e.printStackTrace();
        }
        return found;
    }

}