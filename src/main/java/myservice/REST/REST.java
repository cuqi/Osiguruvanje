package myservice.REST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class REST {
    public static void main(String... args){

      //System.out.println("Working Directory = " + System.getProperty("user.dir"));
      System.out.println(java.time.LocalDate.now().toString());  
      System.out.println("PRED");
        SpringApplication.run(REST.class, args);
        System.out.println("POTOA");
      }
}
