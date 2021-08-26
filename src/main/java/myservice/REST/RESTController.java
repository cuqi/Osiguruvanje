package myservice.REST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

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
	public String myInsurances(@RequestParam(value = "email", defaultValue = "-1") String email) throws NumberFormatException, IOException {

		List<String> policies = new ArrayList<String>();
		if(email.equals("-1"))
		{
			return "Внесете валидна е-пошта";
		}
		else
		{
			List<String> sessions = Helpers.sessionsfromEmail(email);
			for (String s : sessions)
			{
				System.out.println(s);
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

		if(!policy.equals("all") && Helpers.policiesList.toString().contains(policy))
		{
			return "Внесете валидно име на полиса";
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

		if(Helpers.policiesList.toString().contains(policy))
		{
			return "Внесете валидно име на полиса";
		}
		else
		{
			return Helpers.getPolicyNumber(policy);
		}	
	}

	@GetMapping("/paid")
	public String paidPolicies(@RequestParam(value = "email", defaultValue = "-1") String email) throws NumberFormatException, IOException {

		List<String> policies = new ArrayList<String>();
		if(email.equals("-1"))
		{
			return "Внесете валидна е-пошта";
		}
		else
		{
			List<String> sessions = Helpers.sessionsfromEmail(email);
			for (String s : sessions)
			{
				policies.addAll(Helpers.getPaidOrUnpaidPolicies(s, "CL")); //I tuka da se smeni!
			}

		}
		//ako treba da smenam nacin za printanje tuka toa
		return policies.toString();	
	}

	@GetMapping("/unpaid")
	public String unpaidPolicies(@RequestParam(value = "email", defaultValue = "-1") String email) throws NumberFormatException, IOException {

		List<String> policies = new ArrayList<String>();
		if(email.equals("-1"))
		{
			return "Внесете валидна е-пошта";
		}
		else
		{
			List<String> sessions = Helpers.sessionsfromEmail(email);
			for (String s : sessions)
			{
				policies.addAll(Helpers.getPaidOrUnpaidPolicies(s, "OP")); //I tuka da se smeni!
			}

		}
		//ako treba da smenam nacin za printanje tuka toa
		return policies.toString();	
	}

	@GetMapping("email")
	public String sendConformationEmail(@RequestParam(value = "policyID", defaultValue = "-1") String policyID) throws NumberFormatException, IOException, AddressException, MessagingException
	{
		String email = "";
		if(policyID.equals("-1"))
		{
			return "Внесете валиден код на полисата";
		}
		else
		{
			String policy = Helpers.findPolicyFromPolicyID(policyID);
			email = Helpers.findEmailFromSession(policy.split("\\|")[6]);
			Mail.sendmail(email, policy);
		}
		return "Провери си го сандачето: " + email + " :)";
	}	

}
