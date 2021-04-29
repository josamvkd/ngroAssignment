package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS"; 
	
	@GetMapping("/")
	public String home() {
		return "statementDetails.jsp";
	}
	
	@GetMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth!=null && !ROLE_ANONYMOUS.equals(auth.getAuthorities().iterator().next().toString())) {
			return "statementDetails.jsp";
		}
		return "login.jsp";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout.jsp";
	}
}
