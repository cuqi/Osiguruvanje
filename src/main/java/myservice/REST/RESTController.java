package myservice.REST;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/myinsurance")
	public String sessionfromSSN(@RequestParam(value = "ssn", defaultValue = "-1") String ssn) throws NumberFormatException, IOException {

		long ssnInteger = Long.parseLong(ssn);
		if(ssnInteger == -1)
		{
			return "Please enter a valid SSN";
		}
		else
		{
			java.util.List<String> returnsesh = new ArrayList<String>();

			File file = new File("src\\main\\java\\myservice\\sessions.txt");
			BufferedReader br;
			br = new BufferedReader(new FileReader(file));

			String line;

			while ((line = br.readLine()) != null) 
			{
				if(Long.parseLong((line.split("\\s+")[0])) == ssnInteger)
				{
					returnsesh.add((line.split("\\s+")[1]));
				}
				
			}
			br.close();
			return new SessionFromSSN(ssnInteger, returnsesh).toString();
		}
		
	}

}
