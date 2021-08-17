package myservice;

import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import javafx.scene.chart.AreaChart;
import jdk.nashorn.internal.ir.BreakableNode;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;
import java.util.Base64.Encoder;

import javax.servlet.ServletContext;

@WebService
public class MyService {
    // @Resource private WebServiceContext wsContext;
    // private ServletContext sctx;
    
    // public void setServletContext(ServletContext sctx) {
    //     this.sctx = sctx;
    // }

    // public ServletContext getServletContext() {
    //     return this.sctx;
    // }

    @WebMethod
    public int generateNum() {
        return new Random().nextInt();
    }

    @WebMethod // soap 1.1 
    public String loginMethod(@WebParam(name = "username")String username, @WebParam(name = "password")String password) throws NoSuchAlgorithmException {
        try {
            File file = new File("myservice/registeredUsers.txt");
            Scanner scanner = new Scanner(file);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8)
            );
            String hashedPassword = bytesToHex(encodedhash);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                String userInfo[] = line.split(" ");
                System.out.println(userInfo[0] + " " + userInfo[1]);
                if (username.equals(userInfo[0]) && hashedPassword.equals(userInfo[1])) {
                    scanner.close();
                    SecureRandom random = new SecureRandom();
                    byte bytes[] = new byte[20];
                    random.nextBytes(bytes);
                    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
                    String token = encoder.encodeToString(bytes);

                    FileWriter file2 = new FileWriter("myservice/sessions.txt", true);
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
    public String register(@WebParam(name = "username")String username, @WebParam(name = "password1")String password1, @WebParam(name = "password2")String password2, @WebParam(name = "SSN")String ssn ) throws NoSuchAlgorithmException {
        try{
            FileWriter file = new FileWriter("myservice/registeredUsers.txt", true);
            String newUser = "";
            if (password1.equals(password2)) {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest(
                password1.getBytes(StandardCharsets.UTF_8)
                );
                String hashedPassword = bytesToHex(encodedhash);
                newUser = username + " " + hashedPassword + " " + ssn + "\r\n";
            } 
            file.write(newUser);
            file.close();
            return "Успешно се регистриравте!";
        } catch (IOException e) {
            System.out.println("Cannot open file");
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
                File inputFile = new File("myservice/registeredUsers.txt");
                File tempFile = new File("myservice/tempusers.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                String currentLine;
                
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest(
                password1.getBytes(StandardCharsets.UTF_8)
                );
                String hashedPassword = bytesToHex(encodedhash);;

                if((currentLine = reader.readLine()) != null) {
                    if(currentLine.equals(username + " " + hashedPassword)) {
                        flag = true;
                    } else {
                        writer.write(currentLine);
                    }
                }

                while((currentLine = reader.readLine()) != null) {
                    if(currentLine.equals(username + " " + hashedPassword)) {
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
                    System.out.println("There is no file with that name");
                    return "Server error";
                }
        }
    }

    @WebMethod 
    public TravelResponse getTravelQuotation(@WebParam(name = "travelInfo")TravelInfo travelInfo, @WebParam(name = "sessionID")String sessionID) {
        int ageOfUser = 0;
        double premium = 0.0;
        if (sessionID.equals("")) {
            return new TravelResponse("No sessionID", 101);
        } else {
            try {
                File file = new File("myservice/sessions.txt");
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String sessionInfo[] = line.split(" ");
                    System.out.println(sessionInfo[1] + " " + sessionInfo[0]);
                    if (sessionID.equals(sessionInfo[1])) {
                        ageOfUser = getAgeFromSSN(sessionInfo[0]);  // ja zemame vozrasta spored matichniot
                        System.out.println(ageOfUser);  
                    } 
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (travelInfo.type == null) {
            return new TravelResponse("Ве молиме внесете го типот на патничка полиса(INDIVIDUAL, FAMILY, STUDENT, GROUP, BUSINESS)", 110);
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
            return new TravelResponse("Ве молиме внесете го покритието за патничката полиса(VISA, CLASSIC, CLASSIC_PLUS)", 111);
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
        
        return new TravelResponse("Премијата за полисата изнесува: " + String.valueOf((int)premium + "ЕУР"), 100);
    }

    @WebMethod 
    public HouseholdResponse getHouseholdQuotation(@WebParam(name = "HouseholdInfo")HouseholdInfo householdInfo, @WebParam(name = "sessionID")String sessionID) {
        int ageOfUser = 0;
        double premium = 0.0;
        if (sessionID.equals("")) {
            return new HouseholdResponse("No session ID", 101);
        } else {
            try {
                File file = new File("myservice/sessions.txt");
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String sessionInfo[] = line.split(" ");
                    System.out.println(sessionInfo[1] + " " + sessionInfo[0]);
                    if (sessionID.equals(sessionInfo[1])) {
                        ageOfUser = getAgeFromSSN(sessionInfo[0]);  // ja zemame vozrasta spored matichniot
                        System.out.println(ageOfUser);  
                    } 
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (householdInfo.typeObject == null) {
            return new HouseholdResponse("Ве молиме внесете го типот на објектот(HOUSE, APARTMENT)", 111);
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
            return new HouseholdResponse("Ве молиме внесете го типот на покритието(STANDARD, COMFORT, MEGA)", 112);
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
                return new HouseholdResponse("Ве молиме внесете 1, 3 или 5 во полето за должина на договорот.", 113);
        }
        
        return new HouseholdResponse("Висината на премијата е: " + String.valueOf((int)premium) + " ЕУР", 100);
    }

    @WebMethod
    public String bookTravelPolicy(@WebParam(name = "BookTravelInfo")TravelInfo bookTravelInfo, @WebParam(name = "sessionID")String sessionID) {
        String policyID = "";
        String currentID = "";            
        System.out.println("good1");
        replaceSelected("1000", "0");
        // try {
        //     File file = new File("myservice/policyNumberEvidence.txt");
        //     FileWriter fileWriter = new FileWriter("myservice/policyNumberEvidence.txt", true);

        //     Scanner scanner = new Scanner(file);
        //     while(scanner.hasNextLine()) {
        //         String line = scanner.nextLine();
        //         String policyIDType[] = line.split(" ");
        //         if (policyIDType[0].equals("TRA")) {
        //             policyID = "TRA" + policyIDType[1];
        //             currentID = policyIDType[1];
        //             int nextID = Integer.valueOf(currentID) + 1;
        //             fileWriter.write("TRA" + " " + String.valueOf(nextID) + "\r\n");
        //             break;
        //         } 
        //     }
        //     scanner.close();
        //     fileWriter.close();
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        return "OK";
        //return new BookTravelPolicyResponse("Успешно е креирана полиса: " + policyID, 100);
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

    public static void replaceSelected(String replaceWith, String type) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("myservice/policyNumberEvidence.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            System.out.println("good1");
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            int nextID = Integer.valueOf(inputStr) + 1;
            System.out.println("Before:" + inputStr); // display the original file for debugging
            inputStr = inputStr.replace(inputStr, String.valueOf(nextID));
            System.out.println("After:" + inputStr); // display the original file for debugging
    

            // logic to replace lines in the string (could use regex here to be generic)
            // if (type.equals("0")) {
            //     inputStr = inputStr.replace(replaceWith + "1", replaceWith + "0"); 
            // } else if (type.equals("1")) {
            //     inputStr = inputStr.replace(replaceWith + "0", replaceWith + "1");
            // }
    
            // display the new file for debugging
            //System.out.println("----------------------------------\n" + inputStr);
    
            // write the new string with the replaced line OVER the same file
            // FileOutputStream fileOut = new FileOutputStream("myservice/policyNumberEvidence.txt");
            // fileOut.write(inputStr.getBytes());
            // fileOut.close();
    
        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
