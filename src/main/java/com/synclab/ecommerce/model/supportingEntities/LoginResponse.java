package com.synclab.ecommerce.model.supportingEntities;

public class LoginResponse {
	
	private final String jwt;

	
	public LoginResponse(String jwt) {
		this.jwt = jwt;
	}


	public String getJwt() {
		return jwt;
	}

}