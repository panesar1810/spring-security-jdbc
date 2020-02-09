package code.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import code.email.MailSender;

@Controller
public class EmailController {
	
	@Autowired MailSender mailSender;
	
	@GetMapping("/send-mail")
	public String sendMail() throws MessagingException {
		mailSender.sendMail("nameOfSender","emailOfReciever", "subject");
		return "redirect:/";
	}	
	
}
