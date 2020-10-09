package com.synclab.ecommerce.security;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.user.UserServiceImplementation;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{

	private UserServiceImplementation userServiceImp;
	
	public UserDetailsServiceImplementation(UserServiceImplementation userServiceImp) {
		this.userServiceImp = userServiceImp;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userServiceImp.findByAccount_username(username);
		UserPrincipal up = new UserPrincipal(user);
		return up;
		
	}

}
