package com.filpcart.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.filpcart.customer.webToken.JwtService;
import com.filpcart.customer.webToken.LoginForm;

@RestController
public class ContentController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping("/home")
	public String handleWelcome() {
		return "Welcome to home!";
	}
	
	@GetMapping("/admin/home")
	public String handleAdminHome() {
		return "Welcome to ADMIN home!";
	}
	
	@GetMapping("/user/home")
	public String handleUserName() {
		return "Welcome to USER home!";
	}
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginForm.username(), loginForm.password()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(userDetailsService.loadUserByUsername(loginForm.username()));
		}else {
			throw new UsernameNotFoundException("invalid credentials");
		}
		
	}

}
