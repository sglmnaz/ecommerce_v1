package com.synclab.ecommerce.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.user.UserServiceImplementation;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	@Autowired
	private UserServiceImplementation userServiceImp;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(JWTProperties.HEADER_STRING);
		
		if (header == null || !header.startsWith(JWTProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		Authentication authentication =getUsernamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(request, response);
		
	}

	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JWTProperties.HEADER_STRING);
		
		if(token != null) {
			String username = JWT.require(Algorithm.HMAC512(JWTProperties.SECRET.getBytes()))
					.build()
					.verify(token.replace(JWTProperties.TOKEN_PREFIX,""))
					.getSubject();
		
			if (username != null) {
				User user = userServiceImp.findByAccount_username(username);
				UserPrincipal principal = new UserPrincipal(user);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,null,principal.getAuthorities());
				return auth;
			}
			
		return null;
		
		}
		
		return null;
		
	}

}
