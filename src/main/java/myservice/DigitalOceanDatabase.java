package myservice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class DigitalOceanDatabase {
    public static Connection connectToDatabase() throws SQLException, FileNotFoundException, IOException {
        Properties creds = new Properties();
        try (FileReader in = new FileReader("src\\main\\java\\config\\creds.properties")) {
            creds.load(in);
        }
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setUser(creds.getProperty("digitaloceancred1"));
        dataSource.setPassword(creds.getProperty("digitaloceancred2"));
        dataSource.setServerName("db-mysql-fra1-insurance-do-user-10143551-0.b.db.ondigitalocean.com");
        dataSource.setPort(25060);
        dataSource.setDatabaseName("defaultdb");
        dataSource.setUseSSL(true);
        dataSource.setTrustCertificateKeyStoreUrl("file:src\\main\\java\\config\\myTrustStore");
        dataSource.setTrustCertificateKeyStorePassword(creds.getProperty("digitaloceancred3"));

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static int GetVehicleCategory(String plateNumber, String chassis) throws FileNotFoundException, SQLException, IOException {
        Connection conn = DigitalOceanDatabase.connectToDatabase();
        int result = 0;
        if (conn != null) {
            ResultSet rs = null;
            PreparedStatement ps = null;
            String query = "select category from vehicles where plate_number = '" + plateNumber + "' and chassis = '"+ chassis +"';";

            try {
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                if (rs.next()) {
                    result = rs.getInt("category");
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.close();
                rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }  
        }
        

        
        return result;
    }

    public static int CheckSessionID(String sessionID) throws FileNotFoundException, SQLException, IOException {
        Connection conn = DigitalOceanDatabase.connectToDatabase();
        int result = 0;
        if (conn != null) {
            ResultSet rs = null;
            PreparedStatement ps = null;
            String query = "select count(*) as sessionFound from sessions where sessionid = '" + sessionID + "';";

            try {
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                if (rs.next()) {
                    result = rs.getInt("sessionFound");
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.close();
                rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }  
        }
        
        return result;
    }

    /* INSERT TRAVEL */
    public static int insertTravelPolicy(TravelInfo travelInfo, InsuredInfo contractorInfo, InsuredInfo insuredInfo, String startDate, String endDate, String sessionID, String policyID, int premium) throws FileNotFoundException, SQLException, IOException {
        Connection conn = DigitalOceanDatabase.connectToDatabase();
        int result = 0;
        if (conn != null) {

            PreparedStatement ps = null;

            String query = "insert into travelPolicy(policy_id, policy_type, status, premium, travel_type, travel_cover, insured_days, num_people, is_below_18, is_above_65, contractor_first_name, contractor_last_name, contractor_id, contractor_address, contractor_postal, contractor_city, insured_first_name, insured_last_name, insured_id, insured_address, insured_postal, insured_city, creation_date, start_date, end_date, session_id)";
            query += " values (";
            query += "'" + policyID + "'," + "1, " + "'OP', " + String.valueOf(premium) + "," + "'" + travelInfo.type.toString() + "', '" + travelInfo.cover.toString() + "', " + travelInfo.days + ", " + String.valueOf(travelInfo.numPeople) + ", false, false, '" + contractorInfo.firstName + "', '" + contractorInfo.lastName + "', '" + contractorInfo.ssn + "', '" + contractorInfo.address + "', '" + contractorInfo.postalCode + "', '" + contractorInfo.city + "', '" + insuredInfo.firstName + "', '" + insuredInfo.lastName + "', '" + insuredInfo.ssn + "', '" + insuredInfo.address + "', '" + insuredInfo.postalCode + "', '" + insuredInfo.city + "', " + " sysdate(), " + "str_to_date('"+ startDate +"', '%Y-%m-%d'), " + "str_to_date('" + endDate + "', '%Y-%m-%d'), " + "'" + sessionID + "');";
            System.out.println(query);
            try {
                ps = conn.prepareStatement(query);
                result = ps.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.close();
                //rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }  
        }

        return result;
    }

    /* INSERT HOUSEHOLD */
    public static int insertHouseholdPolicy(HouseholdInfo householdInfo, InsuredInfo contractorInfo, String startDate, String endDate, String sessionID, String policyID, int premium) throws FileNotFoundException, SQLException, IOException {
        Connection conn = DigitalOceanDatabase.connectToDatabase();
        int result = 0;
        if (conn != null) {

            PreparedStatement ps = null;
            String query = "INSERT INTO householdPolicy (policy_id, policy_type, status, premium, house_type, household_cover, contract_length, date_of_object, area_of_object, contractor_first_name, contractor_last_name, contractor_id, contractor_address, contractor_postal, contractor_city, creation_date, start_date, end_date, session_id)";
            query += "values (";
            query += "'" + policyID + "', 2, 'OP', " + premium + ", '" + householdInfo.typeObject+ "', '" + householdInfo.typeHouseholdCover + "', " + householdInfo.contractLenght + ", '" + householdInfo.dateOfObject + "', " + householdInfo.areaOfObject + ", '" + contractorInfo.firstName + "', '" + contractorInfo.lastName + "', '" + contractorInfo.ssn + "', '" + contractorInfo.address + "', '" + contractorInfo.postalCode + "', '" + contractorInfo.city + "', " + " sysdate(), " + "str_to_date('"+ startDate +"', '%Y-%m-%d'), " + "str_to_date('" + endDate + "', '%Y-%m-%d'), " + "'" + sessionID + "');";
            System.out.println(query);
            try {
                ps = conn.prepareStatement(query);
                result = ps.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.close();
                //rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }  
        }

        return result;
    }

    /* INSERT CASCO */
    public static int insertCascoPolicy(CascoInfo cascoInfo, InsuredInfo contractorInfo, String startDate, String endDate, String sessionID, String policyID, int premium) throws FileNotFoundException, SQLException, IOException {
        Connection conn = DigitalOceanDatabase.connectToDatabase();
        int result = 0;
        if (conn != null) {

            PreparedStatement ps = null;
            String query = "INSERT INTO cascoPolicy (policy_id, policy_type, status, premium, casco_type, car_value, vehicle_price, franchise, isWindows, contractor_first_name, contractor_last_name, contractor_id, contractor_address, contractor_postal, contractor_city, creation_date, start_date, end_date, session_id)";
            query += "values (";
            query += "'" + policyID + "', 3, 'OP', " + premium + ", '" + cascoInfo.typeCasco + "', '" + cascoInfo.typeValue + "', " + cascoInfo.vehiclePrice + ", " + cascoInfo.franchise + ", " + cascoInfo.windows + ", '" + contractorInfo.firstName + "', '" + contractorInfo.lastName + "', '" + contractorInfo.ssn + "', '" + contractorInfo.address + "', '" + contractorInfo.postalCode + "', '" + contractorInfo.city + "', " + " sysdate(), " + "str_to_date('"+ startDate +"', '%Y-%m-%d'), " + "str_to_date('" + endDate + "', '%Y-%m-%d'), " + "'" + sessionID + "');";
            System.out.println(query);
            try {
                ps = conn.prepareStatement(query);
                result = ps.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }  
        }

        return result;
    }

    /* INSERT AOL*/
    public static int insertAOPolicy(AOInfo aoInfo, InsuredInfo contractorInfo, String startDate, String endDate, String sessionID, String policyID, int premium) throws FileNotFoundException, SQLException, IOException {
        Connection conn = DigitalOceanDatabase.connectToDatabase();
        int result = 0;
        if (conn != null) {

            PreparedStatement ps = null;
            String query = "INSERT INTO aoPolicy (policy_id, policy_type, status, premium, reg_number, chassis, vehicle_power, isNew, contractor_first_name, contractor_last_name, contractor_id, contractor_address, contractor_postal, contractor_city, creation_date, start_date, end_date, session_id)";
            query += "values (";
            query += "'" + policyID + "', 4, 'OP', " + premium + ", '" + aoInfo.regNum + "', '" + aoInfo.chassis + "', " + aoInfo.KW + ", " + aoInfo.isNew + ", '" + contractorInfo.firstName + "', '" + contractorInfo.lastName + "', '" + contractorInfo.ssn + "', '" + contractorInfo.address + "', '" + contractorInfo.postalCode + "', '" + contractorInfo.city + "', " + " sysdate(), " + "str_to_date('"+ startDate +"', '%Y-%m-%d'), " + "str_to_date('" + endDate + "', '%Y-%m-%d'), " + "'" + sessionID + "');";
            System.out.println(query);
            try {
                ps = conn.prepareStatement(query);
                result = ps.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }  
        }

        return result;
    }

    /* INSERT ACC */
    public static int insertAccidentPolicy(AccidentInfo accidentInfo, InsuredInfo contractorInfo, String startDate, String endDate, String sessionID, String policyID, int premium) throws FileNotFoundException, SQLException, IOException {
        Connection conn = DigitalOceanDatabase.connectToDatabase();
        int result = 0;
        if (conn != null) {

            PreparedStatement ps = null;
            String query = "INSERT INTO accidentPolicy (policy_id, policy_type, status, premium, pack, isStudent, contractor_first_name, contractor_last_name, contractor_id, contractor_address, contractor_postal, contractor_city, creation_date, start_date, end_date, session_id)";
            query += "values (";
            query += "'" + policyID + "', 5, 'OP', " + premium + ", " + accidentInfo.pack + ", " + accidentInfo.isStudent + ", '" + contractorInfo.firstName + "', '" + contractorInfo.lastName + "', '" + contractorInfo.ssn + "', '" + contractorInfo.address + "', '" + contractorInfo.postalCode + "', '" + contractorInfo.city + "', " + " sysdate(), " + "str_to_date('"+ startDate +"', '%Y-%m-%d'), " + "str_to_date('" + endDate + "', '%Y-%m-%d'), " + "'" + sessionID + "');";
            System.out.println(query);
            try {
                ps = conn.prepareStatement(query);
                result = ps.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }  
        }

        return result;
    }

    public static int getPolicyData(String policyID, int policyType, String sessionID) throws FileNotFoundException, SQLException, IOException {
        Connection conn = DigitalOceanDatabase.connectToDatabase();
        int result = 0;
        InsuredInfo contractor = new InsuredInfo();
        InsuredInfo insured = new InsuredInfo();
        String startDate = ""; 
        String endDate = ""; 
        String creationDate = ""; 
        String paymentRef = ""; 
        String username = "";
        String email = ""; 
        String premium = ""; 
        String id = "";

        String pol = "";
        switch(policyType) {
            case 1:
                pol = "travelPolicy";
                break;
            case 2:
                pol = "householdPolicy";
                break;
            case 3:
                pol = "aoPolicy";
                break;
            case 4:
                pol = "cascoPolicy";
                break;
            case 5:
                pol = "accidentPolicy";
                break;
            default:
                pol = "";
                break;
        }
        System.out.println(pol);
        if (conn != null) {
            ResultSet rs = null;
            PreparedStatement ps = null;
            String query = "select * from " + pol + " inner join sessions on " + pol + ".session_id=sessions.sessionid where policy_id = '" + policyID + "' and session_id = '" + sessionID + "';";
            System.out.println(query);
            try {
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                if (rs.next()) {
                    id = String.valueOf(rs.getInt("id"));
                    premium = String.valueOf(rs.getDouble("premium"));

                    contractor.firstName = rs.getString("contractor_first_name");
                    contractor.lastName = rs.getString("contractor_last_name");
                    contractor.address = rs.getString("contractor_address");
                    contractor.city = rs.getString("contractor_city");
                    contractor.postalCode = rs.getString("contractor_postal");
                    contractor.ssn = rs.getString("contractor_id");

                    insured.firstName = rs.getString("insured_first_name");
                    insured.lastName = rs.getString("insured_last_name");
                    insured.address = rs.getString("insured_address");
                    insured.city = rs.getString("insured_city");
                    insured.postalCode = rs.getString("insured_postal");
                    insured.ssn = rs.getString("insured_id");

                    startDate = String.valueOf(rs.getDate("start_date"));
                    endDate = String.valueOf(rs.getDate("end_date"));
                    creationDate = String.valueOf(rs.getDate("creation_date"));

                    paymentRef = rs.getString("payment_ref");

                    username = rs.getString("username");
                    email = "krstik1212@gmail.com";
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.close();
                rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }  
        }
        System.out.println(premium + ", " + startDate + ", " + contractor.address);
        PDF.fillPDF(contractor, insured, startDate, endDate, creationDate, paymentRef, sessionID, username, email, policyID, premium, id, pol.toUpperCase());
        
        return result;
    }

}
