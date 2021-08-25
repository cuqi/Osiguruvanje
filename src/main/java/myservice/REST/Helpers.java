package myservice.REST;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helpers {

    public static String[] policiesList = {"casco", "household", "travel"};
    public static String urlBuilder = "src\\main\\java\\data\\";

    public static List<String> sessionsfromEmail(String email) throws NumberFormatException, IOException {

        java.util.List<String> returnsesh = new ArrayList<String>();

        File file = new File("src\\main\\java\\data\\sessions.txt");
        BufferedReader br;
        br = new BufferedReader(new FileReader(file));

        String line;

        while ((line = br.readLine()) != null) 
        {
            if((line.split("\\s+")[0]).equals(email))
            {
                returnsesh.add((line.split("\\s+")[1]));
            }
            
        }
        br.close();
        return returnsesh;
		
	}
    
    public static List<String> getPoliciesFromSession(String session) throws NumberFormatException, IOException {

        java.util.List<String> returnsesh = new ArrayList<String>();

        for(String s: policiesList)
        {
            File file = new File(urlBuilder + s + "Policies.txt");
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
    
            String line;
    
            while ((line = br.readLine()) != null) 
            {
                if((line.split("|")[6]).equals(session))
                {
                    returnsesh.add((line.split("|")[0]));
                }
            }
            br.close();
        }

        return returnsesh;
		
	}

    public static List<String> getPoliciesFromToday(String query) throws NumberFormatException, IOException {

        java.util.List<String> returnsesh = new ArrayList<String>();

        String date = query;
        if(query.equals("today"))
        {
            date = java.time.LocalDate.now().toString();
        }
        for(String s: policiesList)
        {
            File file = new File(urlBuilder + s + "Policies.txt");
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
    
            String line;
    
            while ((line = br.readLine()) != null) 
            {
                if((line.split("|")[8]).equals(date))
                {
                    returnsesh.add((line.split("|")[0]));
                }
            }
            br.close();
        }

        return returnsesh;
		
	}

    public static List<String> getPoliciesYearToDate() throws NumberFormatException, IOException {

        java.util.List<String> returnsesh = new ArrayList<String>();
        
        String year = java.time.LocalDate.now().toString();
        year = year.split("-")[0];
        for(String s: policiesList)
        {
            File file = new File(urlBuilder + s + "Policies.txt");
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
    
            String line;
    
            while ((line = br.readLine()) != null) 
            {
                if(((line.split("|")[8]).split("-")[0]).equals(year))
                {
                    returnsesh.add((line.split("|")[0]));
                }
            }
            br.close();
        }

        return returnsesh;
	}

    public static List<String> getPolicies(String policy) throws NumberFormatException, IOException {

        java.util.List<String> returnsesh = new ArrayList<String>();

        String[] whichPolicies = policiesList;
        if(!policy.equals("all"))
        {
            whichPolicies = new String[1];
            whichPolicies[0] = policy;
        }

        for(String s: whichPolicies)
        {
            File file = new File(urlBuilder + s + "Policies.txt");
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
    
            String line;
    
            while ((line = br.readLine()) != null) 
            {
                returnsesh.add(line + "</br>");
            }
            br.close();
        }

        return returnsesh;
	}

    public static String getPolicyNumber(String policy) throws NumberFormatException, IOException {

        File file = new File(urlBuilder + policy + "PolicyEvidence.txt");
        BufferedReader br;
        br = new BufferedReader(new FileReader(file));

        String line;

        line = br.readLine();
        br.close();

        return line;
	}

    public static List<String> getPaidOrUnpaidPolicies(String session, String status) throws NumberFormatException, IOException {

        java.util.List<String> returnsesh = new ArrayList<String>();

        for(String s: policiesList)
        {
            File file = new File(urlBuilder + s + "Policies.txt");
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
    
            String line;
    
            while ((line = br.readLine()) != null) 
            {
                if((line.split("|")[6]).equals(session) && (line.split("|")[1000]).equals(status)) //DA SE SMENI ZA KADE KJE PISUVA DALI E PLATENO ILI NE
                {
                    returnsesh.add((line.split("|")[0]));
                }
            }
            br.close();
        }

        return returnsesh;
	}

    public static int findPolicyFromPolicyID(String policyID) throws NumberFormatException, IOException {

        String line = null;
        int isOK = 0;
        for(String s: policiesList)
        {
            File file = new File(urlBuilder + s + "Policies.txt");
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
            StringBuffer inputBuffer = new StringBuffer();

            while ((line = br.readLine()) != null) 
            {
                if((line.split("\\|")[0]).equals(policyID)) 
                {
                    if (line.split("\\|")[9].equals("OP")) {
                        isOK = 1;
                        inputBuffer.append(line.replace("OP", "CL"));
                        inputBuffer.append("\n");
                    } else {
                        isOK = 0;
                    }
                } else {
                    inputBuffer.append(line);
                    inputBuffer.append("\n");
                }
            }
            if (isOK == 1) {
                FileWriter fileOut = new FileWriter(file);
                fileOut.write(inputBuffer.toString());
                fileOut.close();
            }
            
            br.close();
        }

        return isOK;
	}

}
