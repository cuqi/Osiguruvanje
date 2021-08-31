package myservice.REST;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class REST {
    public static void main(String... args) throws AddressException, MessagingException, IOException{
      //Path path1 = Paths.get("src\\main\\java\\osiguruvanje.jks");
      //System.out.println("The path is " + path1);
      System.out.println("Working Directory = " + System.getProperty("user.dir"));
      SpringApplication app = new SpringApplication(REST.class);
      // app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
      // app.setDefaultProperties(Collections.singletonMap("server.ssl.enabled", "true"));
      // app.setDefaultProperties(Collections.singletonMap("server.ssl.key-alias", "osiguruvanje"));
      // app.setDefaultProperties(Collections.singletonMap("server.ssl.key-store", "file:///Users/krsti/Desktop/uni/Osiguruvanje/src/main/java/osiguruvanje.jks"));
      // app.setDefaultProperties(Collections.singletonMap("server.ssl.key-store-password", "kakoebrat?"));
      // app.setDefaultProperties(Collections.singletonMap("server.ssl.key-store-type", "jks"));
      // app.setDefaultProperties(Collections.singletonMap("server.ssl.key-password", "kakoebrat?"));
      app.run(args);
      }
}
