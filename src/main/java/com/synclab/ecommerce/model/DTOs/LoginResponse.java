package com.synclab.ecommerce.model.DTOs;

public class LoginResponse {
	
	private final String jwt;

	
	public LoginResponse(String jwt) {
		this.jwt = jwt;
	}


	public String getJwt() {
		return jwt;
	}

}
