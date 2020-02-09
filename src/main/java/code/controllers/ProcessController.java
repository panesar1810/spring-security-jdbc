package code.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import code.beans.SecUser;
import code.components.PasswordValidator;
import code.database.SecurityRepository;

@Controller
@RequestMapping("/process")
public class ProcessController {

	@Autowired private SecurityRepository secRepo;
	@Autowired private PasswordValidator validator;
	
	@PostMapping("/sign-up")
	private String signUp(Model web, 
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String confirmedPassword,
			@RequestParam String role) {
		
		if ("password".equalsIgnoreCase(password))
			web.addAttribute("passwordIspassword", true);
		
		if (!confirmedPassword.equals(password))
			web.addAttribute("passwordMismatch", true);
		
		if (null != secRepo.findUserAccount(username))
			web.addAttribute("userExists", true);
		
		if (!validator.length(password))
			web.addAttribute("length", true);
		
		if (!validator.upperCase(password))
			web.addAttribute("uppercase", true);
		
		if (!validator.lowerCase(password))
			web.addAttribute("lowercase", true);
		
		if (!validator.space(password))
			web.addAttribute("space", true);
		
		if (!validator.specialCharacter(password))
			web.addAttribute("specialCharacter", true);
		
		if (!validator.number(password))
			web.addAttribute("number", true);
		
		// This condition must be true to successfully add user to database
		if (validator.check(password) && confirmedPassword.equals(password) &&
			null == secRepo.findUserAccount(username)) {
			
			secRepo.add(new SecUser(username, password, true));			
			SecUser user = secRepo.findUserAccount(username);
			
			if ("ADMIN".equalsIgnoreCase(role)) {
				secRepo.addUserRole(user.getUserId(), 1);
			}
			if ("USER".equalsIgnoreCase(role)) {
				secRepo.addUserRole(user.getUserId(), 2);
			}
			return "redirect:/";
		}
		return "sign-up.html";
	}
	
}
