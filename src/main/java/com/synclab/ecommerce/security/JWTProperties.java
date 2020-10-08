package com.synclab.ecommerce.security;

public class JWTProperties {
	
	public static final String SECRET = "JWTSecretCodeGen";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final Integer EXPIRATION_TIME = 3600*1000; // milliseconds
	
}
