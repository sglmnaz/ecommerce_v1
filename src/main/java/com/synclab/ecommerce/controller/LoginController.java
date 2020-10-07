package com.synclab.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("")
	public String login(@RequestParam String username,
						@RequestParam String password,
						@RequestParam Boolean stayLogged) {
		//TODO: this
		
		// controllare se questo utente esiste
		// se esiste si genera un token d accesso che verrà usato per le successive chiamate
		
		String token = "token";
		return token;
	}

}
