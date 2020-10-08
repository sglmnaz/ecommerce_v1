package com.synclab.ecommerce.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synclab.ecommerce.model.LoginModel;
import com.synclab.ecommerce.service.user.UserServiceImplementation;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	// triggered by POST to /login
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		//map credentials from request
		
		LoginModel credentials = null;
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(),LoginModel.class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//create token
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.getUsername(),
				credentials.getPassword(),
				new ArrayList<>());
		
		//authenticate user

		Authentication auth = authenticationManager.authenticate(authenticationToken);

		return auth;
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		// create user principal
		UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
		
		// create jwt token
		String token = JWT.create()
				.withSubject(principal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JWTProperties.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(JWTProperties.SECRET.getBytes()));
		
		System.out.println("token succesfully created and activated: ");
		System.out.println(token);
		
		// add token to response
		response.addHeader(JWTProperties.HEADER_STRING, JWTProperties.TOKEN_PREFIX+token);
		
	}

}
