package myservice;

import myservice.REST.Helpers;

import javax.jws.WebService;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Base64.Encoder;

@WebService
public class MyService {

    // @WebMethod // soap 1.1 
    // public String loginMethod(@WebParam(name = "username")String username, @WebParam(name = "password")String password) throws NoSuchAlgorithmException {
    //     try {
    //         System.out.println("Working Directory = " + System.getProperty("user.dir"));
    //         File file = new File("src\\main\\java\\data\\registeredUsers.txt");
    //         Scanner scanner = new Scanner(file);
    //         MessageDigest digest = MessageDigest.getInstance("SHA-256");
    //         byte[] encodedhash = digest.digest(
    //             password.getBytes(StandardCharsets.UTF_8)
    //         );
    //         String hashedPassword = bytesToHex(encodedhash);
    //         while(scanner.hasNextLine()) {
    //             String line = scanner.nextLine();
    //             String userInfo[] = line.split(" ");
    //             if (username.equals(userInfo[0]) && hashedPassword.equals(userInfo[1])) {
    //                 scanner.close();
    //                 SecureRandom random = new SecureRandom();
    //                 byte bytes[] = new byte[20];
    //                 random.nextBytes(bytes);
    //                 Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    //                 String token = encoder.encodeToString(bytes);

    //                 FileWriter file2 = new FileWriter("src\\main\\java\\data\\sessions.txt", true);
    //                 file2.write("\n" + userInfo[2] + " " + token);
    //                 file2.close();
    //                 return token;
    //             } 
    //         }
    //         scanner.close();
    //         return "There was an error!";
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //         return "File cannot be found";
    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //         return e.getMessage();
    //     }
    // }

    // @WebMethod
    // public String register(@WebParam(name = "username")String username, @WebParam(name = "password1")String password1, @WebParam(name = "password2")String password2, @WebParam(name = "email")String email) throws NoSuchAlgorithmException {
    //     try{
    //         FileWriter file = new FileWriter("src\\main\\java\\data\\registeredUsers.txt", true);
    //         String newUser = "";
    //         if (password1.equals(password2)) {
    //             MessageDigest digest = MessageDigest.getInstance("SHA-256");
    //             byte[] encodedhash = digest.digest(
    //             password1.getBytes(StandardCharsets.UTF_8)
    //             );
    //             String hashedPassword = bytesToHex(encodedhash);
    //             newUser = username + " " + hashedPassword + " " + email + "\r\n";
    //         } 
    //         file.write(newUser);
    //         file.close();
    //         return "Успешно се регистриравте!";
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         return "Неуспешно, настана грешка!";
    //     }
    // }

    // @WebMethod
    // public String unregister(@WebParam(name = "username")String username, @WebParam(name = "password1")String password1, @WebParam(name = "password2")String password2) throws IOException, NoSuchAlgorithmException{
    //     boolean flag = false;
    //     if(!password1.equals(password2)) {
    //         return "Внесените пасворди се различни";
    //     } else {
    //         try {
    //             File inputFile = new File("registeredUsers.txt");
    //             File tempFile = new File("src\\main\\java\\data\\tempusers.txt");
    //             BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    //             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    //             String currentLine;
                
    //             MessageDigest digest = MessageDigest.getInstance("SHA-256");
    //             byte[] encodedhash = digest.digest(
    //             password1.getBytes(StandardCharsets.UTF_8)
    //             );
    //             String hashedPassword = bytesToHex(encodedhash);

    //             if((currentLine = reader.readLine()) != null) {
    //                 String[] entries = currentLine.split(" ");
    //                 // System.out.println(entries[0] + " " + entries[1]);
    //                 if(entries[0].equals(username) && entries[1].equals(hashedPassword)) {
    //                     flag = true;
    //                 } else {
    //                     writer.write(currentLine);
    //                 }
    //             }

    //             while((currentLine = reader.readLine()) != null) {
    //                 String[] entries = currentLine.split(" ");
    //                 if(entries[0].equals(username) && entries[1].equals(hashedPassword)) {
    //                     flag = true;
    //                     continue;
    //                 } 
    //                 writer.write(System.getProperty("line.separator") + currentLine);
    //             }
    //             writer.close(); 
    //             reader.close(); 
                
    //             inputFile.delete();
    //             tempFile.renameTo(inputFile);

    //             if (flag) {
    //                 return "User-от е избришан";
    //             } else {
    //                 return "Погрешен username или password";
    //             }

    //             } catch (FileNotFoundException e) {
    //                 return "Server error";
    //             }
    //     }
    // }

    @WebMethod 
    public QuotationResponse getTravelQuotation(@WebParam(name = "travelInfo")TravelInfo travelInfo, @WebParam(name = "sessionID")String sessionID) {
        double premium = 0.0;
        if (sessionID.equals("")) {
            return new QuotationResponse("No sessionID", 101, 0);
        } else if (checkSessionID(sessionID) == 0) {
            return new QuotationResponse("Session ID e погрешно!", 102, 0);
        }

        if (travelInfo.days < 3 || travelInfo.days > 365) {
            return new QuotationResponse("Минимален број на денови е 3, максимален е 365", 109, 0);
        }

        if (travelInfo.numPeople > 10) {
            return new QuotationResponse("Максимален број на осигурани лица е 10", 106, 0);
        }

        if (travelInfo.type == null) {
            return new QuotationResponse("Ве молиме внесете го видот на патничкото осигурување(INDIVIDUAL, FAMILY, STUDENT, GROUP, BUSINESS)", 110, 0);
        } else {
            switch(travelInfo.type) {
                case INDIVIDUAL:
                    premium += travelInfo.days * 41;
                    break;
                case FAMILY:
                    if (travelInfo.numPeople < 2) {
                        return new QuotationResponse("За семејно патничко осигурување ве молиме внесете над едно лице", 108, 0);
                    }
                    premium += travelInfo.days * 35;
                    break;
                case STUDENT:
                    premium += travelInfo.days * 34;
                    break;
                case GROUP:
                if (travelInfo.numPeople < 5) {
                    return new QuotationResponse("За групно патничко осигурување ве молиме внесете над четири лице", 107, 0);
                }
                    premium += travelInfo.days * 33;
                    break;
                case BUSINESS:
                    premium += travelInfo.days * 41;
                    break;
            }
        }
        
        if (travelInfo.cover == null) {
            return new QuotationResponse("Ве молиме внесете го типот на покритие(CLASSIC, VISA, VIP)", 111, 0);
        } else {
            switch(travelInfo.cover) {
                case CLASSIC:
                    break;
                case VISA:
                    premium += premium * 0.1;
                    break;
                case VIP:
                    premium += premium * 0.27;
                    break;
            }
        }

        if (travelInfo.isbelow18) {
            premium -= premium * 0.1;
        }

        if (travelInfo.isabove65) {
            premium += premium * 0.05;
        }
        
        return new QuotationResponse("Премијата изнесува: " + String.valueOf((int)premium + " денари."), 100, (int) Math.abs(premium));
    }

    @WebMethod 
    public QuotationResponse getHouseholdQuotation(@WebParam(name = "HouseholdInfo")HouseholdInfo householdInfo, @WebParam(name = "sessionID")String sessionID) {
        double premium = 0.0;
        if (sessionID.equals("")) {
            return new QuotationResponse("No session ID", 101, 0);
        } else if (checkSessionID(sessionID) == 0){
            return new QuotationResponse("Session ID e погрешно!", 102, 0);
        }

        if (householdInfo.areaOfObject < 30) {
            return new QuotationResponse("Ве молиме внесете повеќе од 30 квадратни метри", 114, 0);
        }

        if (householdInfo.typeObject == null) {
            return new QuotationResponse("Ве молиме внесете го типот на објектот(RESIDENCE, APARTMENT, PORTABLE)", 111, 0);
        } else {
            if (householdInfo.typeObject.equals(TypeObject.RESIDENCE)) {
                premium += (householdInfo.areaOfObject * 20) - householdInfo.areaOfObject * 3/10;
            } else if (householdInfo.typeObject.equals(TypeObject.BUSINESS)) {
                premium += householdInfo.areaOfObject * 23 - householdInfo.areaOfObject * 3/10;
            } else if (householdInfo.typeObject.equals(TypeObject.PORTABLE)) {
                premium += householdInfo.areaOfObject * 24 - householdInfo.areaOfObject * 3/10;
            }
        }

        // dodavanje na premijata vo zavisnost od tipot na pokritieto
        if (householdInfo.typeHouseholdCover == null) {
            return new QuotationResponse("Ве молиме внесете го типот на покритието(STANDARD, COMFORT, MEGA)", 112, 0);
        } else {
            switch(householdInfo.typeHouseholdCover) {
                case STANDARD:
                    premium += premium * 0.1;
                    break;
                case COMFORT:
                    premium += premium * 0.4;
                    break;
                case MEGA:
                    premium += premium * 0.8;
                    break;
            }
        }

        switch(householdInfo.contractLenght) {
            case 1:
                premium -= (premium*5)/100;
                break;
            case 3:
                premium -= (premium*7.5)/100;
                break;
            case 5:
                premium -= (premium*10)/100;
                break;
            default:
                return new QuotationResponse("Ве молиме внесете 1, 3 или 5 во полето за должина на договорот.", 113, 0);
        }
        
        return new QuotationResponse("Премијата изнесува: " + String.valueOf((int)premium) + " денари.", 100, (int) premium);
    }

    @WebMethod 
    public QuotationResponse getAOQuotation(@WebParam(name = "AOInfo")AOInfo aoInfo, @WebParam(name = "ssn") String ssn,  @WebParam(name = "sessionID")String sessionID) {
        double premium = 0;
        
        if (aoInfo.regNum == null) {
           return new QuotationResponse("Внесете ја регистрацијата на возилото", 101, Float.valueOf(0));
        }

        if (aoInfo.chassis == null) {
            return new QuotationResponse("Внесете ја шасијата на возилото", 102, Float.valueOf(0));
        }
        int age = getAgeFromSSN(ssn);
        try {
            int cat = DigitalOceanDatabase.GetVehicleCategory(aoInfo.regNum, aoInfo.chassis);

            switch(cat) {
                case 5:
                    premium += 6000;
                    break;
                case 6: 
                    premium += 7000;
                    break;
                case 7:
                    premium += 8000;
                    break;
                case 8:
                    premium += 9000;
                    break;
                case 9:
                    premium += 10000;
                    break;
                default:
                    premium = 0;
                    return new QuotationResponse("Error", 999, 0);
            } 

            if (age < 22 || age > 70) {
                premium += premium * 0.15;
            } else {
                premium += premium * 0.1;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new QuotationResponse("Премијата изнесува: " + String.valueOf((int)premium) + " денари.", 100, (int) premium);
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
    public QuotationResponse getAccidentQuotation(@WebParam(name = "AccidentInfo")AccidentInfo accidentInfo, @WebParam(name = "sessionID")String sessionID) {
        double premium = 0;
        if (sessionID.equals("")) {
            return new QuotationResponse("Ви недостасува број на сесија!", 101, 0);
        } else if (checkSessionID(sessionID) == 0){
            return new QuotationResponse("Session ID e погрешно!", 102, 0);
        }
        Date now = new Date();
        if (accidentInfo.startDate.before(now)) {
            return new QuotationResponse("Не можете да внесете датум пред денешниот!", 132, (int) premium);
        }
        switch(accidentInfo.pack) {
            case 1:
                premium = 150;
                break;
            case 2:
                premium = 200;
                break;
            case 3:
                premium = 300;
                break;
            default:
                premium = 0;
                return new QuotationResponse("Внесете 1, 2 или 3 за пакет на осигурување на несреќен случај!", 131, (int) premium);
        }

        if (accidentInfo.isStudent) {
            premium -= premium * 0.2;
        }
        
        return new QuotationResponse("Премијата изнесува: " + String.valueOf((int)premium) + " денари" + accidentInfo.startDate.toString(), 100, (int) premium);
    }

    @WebMethod
    public BookResponse bookTravelPolicy(@WebParam(name = "BookTravelInfo")TravelInfo bookTravelInfo, @WebParam(name = "Owner")InsuredInfo owner, @WebParam(name = "Insured")InsuredInfo insured, @WebParam(name = "sessionID")String sessionID, @WebParam(name = "startDate")Date startDate) {
        String policyID = "";
        float premium = 0;
        String typePolicy = "TRA";
        QuotationResponse travelResponse =  getTravelQuotation(bookTravelInfo, sessionID);
        premium = travelResponse.premium;
        if (premium == 0) {
            return new BookResponse(travelResponse.message, travelResponse.code, "0");
        }

        if (owner.firstName.equals("")) {
            return new BookResponse("Ве молиме внесете го името на осигуреникот!", 111, "0");
        } 

        if (owner.lastName.equals("")) {
            return new BookResponse("Ве молиме внесете го презимето на осигуреникот!", 112, "0");
        } 

        int ageOfOwner = 0;
        if (owner.ssn.equals("")) {
            return new BookResponse("Ве молиме внесете го матичниот број на осигуреникот!", 113, "0");
        } else if (owner.ssn.length() != 13) {
            return new BookResponse("Ве молиме внесете за матичниот број внесете 13 цифри!", 113, "0");
        } else {
            ageOfOwner = getAgeFromSSN(owner.ssn);
        }

        if (insured.firstName.equals("")) {
            return new BookResponse("Ве молиме внесете го името на осигуреникот!", 111, "0");
        } 

        if (insured.lastName.equals("")) {
            return new BookResponse("Ве молиме внесете го презимето на осигуреникот!", 112, "0");
        } 

        Date endDate;
        String startDateString = "", endDateString = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("startDate: " + format.format(startDate));

        startDateString = format.format(startDate); 
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DAY_OF_MONTH, bookTravelInfo.days);
        endDate = c.getTime();
        endDateString = format.format(endDate);

        System.out.println("startDate: " + startDateString + ", endDate: " + endDateString);
        policyID = typePolicy + replaceSelected(typePolicy);
        System.out.println(policyID);
        int prem = (int) premium;
        int result = 0;
        try {
            result = DigitalOceanDatabase.insertTravelPolicy(bookTravelInfo, owner, insured, startDateString, endDateString, sessionID, policyID, prem);
            if (result != 1) {
                return new BookResponse("Настана грешка! Проверете си ги податоците!", 199, "0");
            }
            System.out.println("result is" + result);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return new BookResponse("Успешно е креирана полисата со сериски број: " + policyID, 100, policyID);
    }

    @WebMethod
    public BookResponse bookHouseholdPolicy(@WebParam(name = "HouseholdInfo")HouseholdInfo householdInfo, @WebParam(name = "Insured") InsuredInfo insured, @WebParam(name = "startDate")Date startDate, @WebParam(name = "sessionID")String sessionID) {
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

        Date endDate;
        String startDateString = "", endDateString = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("startDate: " + format.format(startDate));

        startDateString = format.format(startDate); 
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.YEAR, 1);
        endDate = c.getTime();
        endDateString = format.format(endDate);

        System.out.println("startDate: " + startDateString + ", endDate: " + endDateString);
        policyID = typePolicy + replaceSelected(typePolicy);
        System.out.println(policyID);
        int result = 0;
        try {
            result = DigitalOceanDatabase.insertHouseholdPolicy(householdInfo, insured, startDateString, endDateString, sessionID, policyID, (int)premium);
            if (result != 1) {
                return new BookResponse("Настана грешка! Проверете си ги податоците!", 199, "0");
            }
            System.out.println("result is" + result);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return new BookResponse("Успешно е креирана полисата со сериски број: " + policyID, 100, policyID);
    }

    @WebMethod
    public BookResponse bookCascoPolicy(@WebParam(name = "CascoInfo")CascoInfo cascoInfo, @WebParam(name = "Insured") InsuredInfo insured, @WebParam(name = "startDate")Date startDate, @WebParam(name = "sessionID")String sessionID) {
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

        Date endDate;
        String startDateString = "", endDateString = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("startDate: " + format.format(startDate));

        startDateString = format.format(startDate); 
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.YEAR, 1);
        endDate = c.getTime();
        endDateString = format.format(endDate);

        System.out.println("startDate: " + startDateString + ", endDate: " + endDateString);
        policyID = typePolicy + replaceSelected(typePolicy);
        System.out.println(policyID);
        int result = 0;
        
        try {
            result = DigitalOceanDatabase.insertCascoPolicy(cascoInfo, insured, startDateString, endDateString, sessionID, policyID, (int)premium);
            if (result != 1) {
                return new BookResponse("Настана грешка! Проверете си ги податоците!", 199, "0");
            }
            System.out.println("result is" + result);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return new BookResponse("Успешно е креирана полисата со сериски број: " + policyID, 100, policyID);
    }

    @WebMethod
    public BookResponse bookAOPolicy(@WebParam(name = "AOInfo")AOInfo aoInfo, @WebParam(name = "Insured") InsuredInfo insured, @WebParam(name = "startDate")Date startDate, @WebParam(name = "sessionID")String sessionID) {
        String policyID = "";
        float premium = 0;
        String typePolicy = "AOL";
        QuotationResponse aoResponse =  getAOQuotation(aoInfo, insured.ssn, sessionID);
        premium = aoResponse.premium;
        if (premium == 0) {
            return new BookResponse(aoResponse.message, aoResponse.code, "0");
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

        Date endDate;
        String startDateString = "", endDateString = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("startDate: " + format.format(startDate));

        startDateString = format.format(startDate); 
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.YEAR, 1);
        endDate = c.getTime();
        endDateString = format.format(endDate);

        System.out.println("startDate: " + startDateString + ", endDate: " + endDateString);
        policyID = typePolicy + replaceSelected(typePolicy);
        System.out.println(policyID);
        int result = 0;
        
        try {
            result = DigitalOceanDatabase.insertAOPolicy(aoInfo, insured, startDateString, endDateString, sessionID, policyID, (int)premium);
            if (result != 1) {
                return new BookResponse("Настана грешка! Проверете си ги податоците!", 199, "0");
            }
            System.out.println("result is" + result);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return new BookResponse("Успешно е креирана полисата со сериски број: " + policyID, 100, policyID);
    }

    @WebMethod
    public BookResponse bookAccidentPolicy(@WebParam(name = "AccidentInfo")AccidentInfo accidentInfo, @WebParam(name = "Insured") InsuredInfo insured, @WebParam(name = "sessionID")String sessionID) {
        String policyID = "";
        float premium = 0;
        String typePolicy = "ACC";
        QuotationResponse accidentResponse =  getAccidentQuotation(accidentInfo, sessionID);
        premium = accidentResponse.premium;
        if (premium == 0) {
            return new BookResponse(accidentResponse.message, accidentResponse.code, "0");
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

        Date endDate;
        String startDateString = "", endDateString = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("startDate: " + format.format(accidentInfo.startDate));

        startDateString = format.format(accidentInfo.startDate); 
        Calendar c = Calendar.getInstance();
        c.setTime(accidentInfo.startDate);
        c.add(Calendar.YEAR, 1);
        endDate = c.getTime();
        endDateString = format.format(endDate);

        System.out.println("startDate: " + startDateString + ", endDate: " + endDateString);
        policyID = typePolicy + replaceSelected(typePolicy);
        System.out.println(policyID);
        int result = 0;
        
        try {
            result = DigitalOceanDatabase.insertAccidentPolicy(accidentInfo, insured, startDateString, endDateString, sessionID, policyID, (int)premium);
            if (result != 1) {
                return new BookResponse("Настана грешка! Проверете си ги податоците!", 199, "0");
            }
            System.out.println("result is" + result);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return new BookResponse("Успешно е креирана полисата со сериски број: " + policyID, 100, policyID);
    }
    // @WebMethod
    // public ConfirmResponse confirmPolicy(@WebParam(name = "policyID")String policyID, @WebParam(name = "creditCardInfo") CreditCardInfo creditCardInfo, @WebParam(name = "sessionID") String sessionID){
        
    //     if (checkSessionID(sessionID) == 0) {
    //         return new ConfirmResponse("Бројот на сесија е невалиден!", 117);
    //     }

    //     if (creditCardInfo.creditCardNumber.equals("")) {
    //         return new ConfirmResponse("Внесете број на картичка", 111);
    //     } else if (creditCardInfo.creditCardNumber.length() != 16) {
    //         return new ConfirmResponse("Внесете 16 цифри за број на картичка", 112);
    //     }
        
    //     if (creditCardInfo.expiryDate == null) {
    //         return new ConfirmResponse("Внесете го датумот на истекување на картичката(формат: mm/yyyy)", 113);
    //     } else {
    //         Date date = new Date();
    //         if (creditCardInfo.expiryDate.before(date)) {
    //             return new ConfirmResponse("Истечена ви е картичката", 114);
    //         }
    //     }
        
    //     if (creditCardInfo.CVV.length() != 3) {
    //         return new ConfirmResponse("Внесете 3 цифри за CVV", 115);
    //     }

    //     if (policyID.equals("")) {
    //         return new ConfirmResponse("Внесете број на полиса!", 110);
    //     } else {
    //         try {
    //             List<String> policyData = Helpers.payForPolicy(policyID);
    //             if (policyData.get(0).equals("NOK")) {
    //                 return new ConfirmResponse("Полисата е веќе платена!", 117);
    //             } else {
    //                 PDF.createPDF(policyID, policyData, sessionID);
    //             }
    //         } catch (NumberFormatException e) {
    //             e.printStackTrace();
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     return new ConfirmResponse("Полисата со број: " + policyID + " е успешно платена", 100);
    // }

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
        } else if (typePolicy.equals("AOL")) {
            fileToOpen = "src\\main\\java\\data\\aoPolicyEvidence.txt";
        } else if (typePolicy.equals("ACC")) {
            fileToOpen = "src\\main\\java\\data\\accidentPolicyEvidence.txt";
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

    // public static int checkSessionID(String sessionID) {
    //     int found = 0;
    //     try {
    //         File file = new File("src\\main\\java\\data\\sessions.txt");
    //         Scanner scanner = new Scanner(file);
    //         while(scanner.hasNextLine()) {
    //             String line = scanner.nextLine();
    //             String sessionInfo[] = line.split(" ");
    //             if (sessionID.equals(sessionInfo[1])) {
    //                 found = 1;
    //                 break;
    //             } else {
    //                 found = 0;
    //             }
    //         }
    //         scanner.close();
    //     } catch (FileNotFoundException e) {
    //         found = 0;
    //         e.printStackTrace();
    //     }
    //     return found;
    // }

    public static int checkSessionID(String sessionID) {
        int found = 0;
        try {
            found = DigitalOceanDatabase.CheckSessionID(sessionID);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return found;
    }


}