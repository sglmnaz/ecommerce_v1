package com.synclab.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.security.SecurityProperties;
import com.synclab.ecommerce.service.user.UserServiceImplementation;

import antlr.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserServiceImplementation usi;
	
	@Autowired
	private PasswordEncoder pe;
		
	@PostMapping("")
	public ResponseEntity<String> login(@RequestParam String username,
						@RequestParam String password,
						@RequestParam(required = false) Boolean stayLogged) {
		
		//cerca utente tramite username 
		
		User user = null;
		user = usi.findByAccount_username(username);
		
		// controllare se questo utente esiste
		
		if (user == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		// verificare password
		
		String encodedPassword = user.getAccount().getPassword();
		if (!pe.matches(password, encodedPassword))
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();

		// genera un token d accesso che verrà usato per le successive chiamate
		Date exp = new Date(System.currentTimeMillis() + SecurityProperties.EXPIRATION);
		String token = Jwts.builder().setSubject(username).signWith(SignatureAlgorithm.HS512, SecurityProperties.SECRET)
				.setExpiration(exp).compact();
		
		return ResponseEntity.ok(token);
	}

}
