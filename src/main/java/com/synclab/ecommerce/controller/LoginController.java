package com.synclab.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.security.JWTProperties;
import com.synclab.ecommerce.service.user.UserServiceImplementation;

@RestController
@RequestMapping("/login/no")
@CrossOrigin
public class LoginController {
	
//	@Autowired
//	private UserServiceImplementation userServiceImp;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@PostMapping("")
//	public String login(@RequestParam(required = false) String username,
//						@RequestParam(required = false) String email,
//						@RequestParam String password,
//						@RequestParam( required = false, defaultValue = "false" )Boolean stayLogged) {
//
//		// find user
//		
//		User user = null;
//		
//		user = username != null ? userServiceImp.findByAccount_username(username) : userServiceImp.findByAccount_email(email);
//		if (user == null)
//			return "user not found";
//		
//		final String _username = user.getAccount().getUsername();
//		final String _password = user.getAccount().getPassword();
//
//		// check password
//		
//		String encodedPassword = passwordEncoder.encode(password) ;
//		
//		if (_password != encodedPassword)
//			return "password is incorrect";
//		
//		
//		
//		// generate access token
//		
//		String token = JWT.create()
//				.withSubject(_username)
//				.withExpiresAt(new Date (System.currentTimeMillis() + JWTProperties.EXPIRATION_TIME))
//				.sign(Algorithm.HMAC512(JWTProperties.SECRET.getBytes()));
//		
//		return token;
//		
//	}

}
