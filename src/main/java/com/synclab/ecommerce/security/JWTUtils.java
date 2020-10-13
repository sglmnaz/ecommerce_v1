package com.synclab.ecommerce.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	
	// generate token for user
    public static String generateToken(UserDetails userDetails) {
        return doGenerateToken(userDetails.getUsername());
    }
	
	//generate token
    public static String doGenerateToken(String subject) {
    	
    	 JwtBuilder builder = Jwts.builder()
        		.setSubject(subject)
        		.setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWTProperties.EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWTProperties.SECRET);
    	 
    	 return builder.compact();
    }

    // retrieve username from jwt token
    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // retrieve expiration date from jwt token
    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secret key
    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWTProperties.SECRET).parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // validate token
    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
