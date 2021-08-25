package myservice.REST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/myinsurance")
	public String myInsurances(@RequestParam(value = "ssn", defaultValue = "-1") String ssn) throws NumberFormatException, IOException {

		long ssnInteger = Long.parseLong(ssn);
		List<String> policies = new ArrayList<String>();
		if(ssnInteger == -1)
		{
			return "Please enter a valid SSN";
		}
		else
		{
			List<String> sessions = Helpers.sessionsfromSSN(ssn);
			for (String s : sessions)
			{
				policies.addAll(Helpers.getPoliciesFromSession(s));
			}

		}
		//ako treba da smenam nacin za printanje tuka toa
		return policies.toString();
		
	}

	@GetMapping("/thisyear")
	public String policyFromThisYear() throws NumberFormatException, IOException {

		List<String> policies = new ArrayList<String>();
		policies = Helpers.getPoliciesYearToDate();
		//ako treba da smenam nacin za printanje tuka toa
		return policies.toString();
	}

	@GetMapping("/thisdate")
	public String policyFromToday(@RequestParam(value = "date", defaultValue = "today") String date) throws NumberFormatException, IOException {

		List<String> policies = new ArrayList<String>();
		policies = Helpers.getPoliciesFromToday(date);
		//ako treba da smenam nacin za printanje tuka toa
		return policies.toString();
	}


	@GetMapping("/policies")
	public String policies(@RequestParam(value = "policy", defaultValue = "all") String policy) throws NumberFormatException, IOException {

		List<String> policies = new ArrayList<String>();

		if(policy != "all" && !Helpers.policiesList.toString().contains(policy))
		{
			return "Enter a valid policy name";
		}
		else
		{
			policies = Helpers.getPolicies(policy);
		}
		//ako treba da smenam nacin za printanje tuka toa
		return policies.toString();
		
	}

	@GetMapping("/policyNumber")
	public String policyNumber(@RequestParam(value = "policy", defaultValue = "casco") String policy) throws NumberFormatException, IOException {

		if(!Helpers.policiesList.toString().contains(policy))
		{
			return "Enter a valid policy name";
		}
		else
		{
			return Helpers.getPolicyNumber(policy);
		}	
	}

	@GetMapping("/paid")
	public String paidPolicies(@RequestParam(value = "ssn", defaultValue = "-1") String ssn) throws NumberFormatException, IOException {

		long ssnInteger = Long.parseLong(ssn);
		List<String> policies = new ArrayList<String>();
		if(ssnInteger == -1)
		{
			return "Please enter a valid SSN";
		}
		else
		{
			List<String> sessions = Helpers.sessionsfromSSN(ssn);
			for (String s : sessions)
			{
				policies.addAll(Helpers.getPaidOrUnpaidPolicies(s, "paid")); //I tuka da se smeni!
			}

		}
		//ako treba da smenam nacin za printanje tuka toa
		return policies.toString();	
	}

	@GetMapping("/unpaid")
	public String unpaidPolicies(@RequestParam(value = "ssn", defaultValue = "-1") String ssn) throws NumberFormatException, IOException {

		long ssnInteger = Long.parseLong(ssn);
		List<String> policies = new ArrayList<String>();
		if(ssnInteger == -1)
		{
			return "Please enter a valid SSN";
		}
		else
		{
			List<String> sessions = Helpers.sessionsfromSSN(ssn);
			for (String s : sessions)
			{
				policies.addAll(Helpers.getPaidOrUnpaidPolicies(s, "unpaid")); //I tuka da se smeni!
			}

		}
		//ako treba da smenam nacin za printanje tuka toa
		return policies.toString();	
	}

}
