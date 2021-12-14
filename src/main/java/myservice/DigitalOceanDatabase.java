package myservice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        }
        
        return result;
    }
}
