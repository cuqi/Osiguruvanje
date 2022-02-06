package myservice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import myservice.REST.Helpers;



public class test {
    public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
        
        int cat = DigitalOceanDatabase.GetVehicleCategory("SK-399-KO", "WMAT325800M209024");
        System.out.println("THE vehCat is: " + cat);

        
    }


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
}
