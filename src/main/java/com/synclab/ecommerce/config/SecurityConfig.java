package com.synclab.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.synclab.ecommerce.service.userDetails.UserDetailsServiceImplementation;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	// routes declaration
	
	// public routes and APIs
    private static final String[] PUBLIC_ENDPOINTS = {
         "/",
         "/user/test"
    };
    
    
    // logged users routes and APIs
    private static final String[] USER_OR_ABOVE_ENDPOINTS = {
         "/user/get/**",
         "/user/getAll/**"
    };
    
    // admin reserved routes and APIs
    private static final String[] ADMIN_ONLY_ENDPOINTS = {
        "/user/insert/**",
        "/user/update/**",
        "/user/delete/**"
    };
    
    
    // roles declaration
    
    private String ADMIN = "ADMIN";
    private String MANAGER = "MANAGER";
    private String USER = "USER";

    
// 	  users declaration in memory

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//    	
//        auth.inMemoryAuthentication().withUser("admin")
//            .password(passwordEncoder().encode("password"))
//            .roles(ADMIN);
//        
//        auth.inMemoryAuthentication().withUser("manager")
//        	.password(passwordEncoder().encode("password"))
//        	.roles(MANAGER);
//        
//        auth.inMemoryAuthentication().withUser("user")
//        	.password(passwordEncoder().encode("password"))
//        	.roles(USER);
//
//    }
    
    // user identification from db
    
    @Autowired
   	private UserDetailsServiceImplementation userDetailsServiceImplementation;
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    	daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImplementation);
    	return daoAuthenticationProvider;
    }
    
	@Override
	public void configure(AuthenticationManagerBuilder auth) {

		auth.authenticationProvider(authenticationProvider());

	}
    
    
    // encoder
    
    @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

    
    // authorizations
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
			.csrf().disable().formLogin().disable()
            .authorizeRequests()
            	.antMatchers(PUBLIC_ENDPOINTS).permitAll()
            	.antMatchers(USER_OR_ABOVE_ENDPOINTS).hasAnyRole(USER,MANAGER,ADMIN)
            	.antMatchers(ADMIN_ONLY_ENDPOINTS).hasRole(ADMIN)
         
            .and()
            	.httpBasic()
        ;

	}

}
