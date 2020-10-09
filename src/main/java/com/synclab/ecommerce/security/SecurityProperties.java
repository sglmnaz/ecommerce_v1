package com.synclab.ecommerce.security;

public class SecurityProperties {
	
	public static final String SECRET = "jwtSecretCodeKey";
	public static final Integer EXPIRATION = 900_000; //ms
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/api/user/signup";

}
