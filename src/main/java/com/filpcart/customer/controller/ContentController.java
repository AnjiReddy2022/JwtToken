package com.filpcart.customer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {
	
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

}
