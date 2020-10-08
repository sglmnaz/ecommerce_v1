package com.synclab.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synclab.ecommerce.model.User;

@RestController
@RequestMapping("/")
public class GeneralPurposeController {

	@GetMapping("signup")
	public String getSignup(Model model) {
		return "<h1>Signup page</h1>";
	}

	@GetMapping("")
	public String getHome(Model model) {
		return "<h1>Home page</h1>";
	}

	@GetMapping("userpage")
	public String getUserPage(Model model) {
		return "<h1>user page</h1>";
	}

	@GetMapping("managerpage")
	public String getManagerPage(Model model) {
		return "<h1>Manager page</h1>";
	}

	@GetMapping("adminpage")
	public String getAdminPage(Model model) {
		return "<h1>Admin page</h1>";
	}
	

}