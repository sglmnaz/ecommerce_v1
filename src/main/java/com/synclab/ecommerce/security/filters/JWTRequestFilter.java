package com.synclab.ecommerce.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.synclab.ecommerce.security.JWTUtils;
import com.synclab.ecommerce.security.UserDetailsServiceImplementation;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImplementation udsi;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String header = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		if (header != null && header.startsWith("Bearer ")) {
			jwt = header.substring(7);
			username = JWTUtils.getUsernameFromToken(jwt);
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = udsi.loadUserByUsername(username);
			//validate token
			if(JWTUtils.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
						userDetails,null,userDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		
		filterChain.doFilter(request,response);
		
		
	}

}
