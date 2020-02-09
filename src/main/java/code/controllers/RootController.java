package code.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import code.database.SecurityRepository;

@Controller
public class RootController {

	@Autowired @Lazy private SecurityRepository secRepo;
	
	@GetMapping("/")
	public String bootUp(Model web) {
		web.addAttribute("users", secRepo.findAll());
		web.addAttribute("count", secRepo.count());
		return "root.html";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user/user.html";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin/admin.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/sign-up")
	public String signUp() {
		return "sign-up.html";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied.html";
	}
	
}
