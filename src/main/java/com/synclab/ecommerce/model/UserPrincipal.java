package com.synclab.ecommerce.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
	
	private User user;
	private Account account;
	
	public UserPrincipal(User user) {
		this.user = user;
		account = this.user.getAccount();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		//TODO: check here
		System.out.println("------------------------------------------------------------------------");
		
//		this.account.getRole().forEach(role -> {
//			GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
//			authorities.add(authority);
//		});
		
		List<Role> roles = account.getRole();
		System.out.println(roles);

		
		for (Role role : roles) {
			System.out.println(role.getName());
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
			authorities.add(authority);
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return !account.getIsBanned() && !account.getIsSuspended();
	}

}
