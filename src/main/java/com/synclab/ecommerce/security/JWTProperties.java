package com.synclab.ecommerce.security;

public class JWTProperties {

    public static final String SECRET = "jwtSecretCodeKey";
    public static final String PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final Integer EXPIRATION = 900_000; // ms

}
