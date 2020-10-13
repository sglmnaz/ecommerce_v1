package com.synclab.ecommerce.config;

import com.synclab.ecommerce.security.JWTAuthorizationFilter;
import com.synclab.ecommerce.security.UserDetailsServiceImplementation;
import com.synclab.ecommerce.security.filters.JWTRequestFilter;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    // routes declaration

    // public routes and APIs

    private static final String[] PUBLIC_ENDPOINTS = {
            "/",
            "/index",
            "/user/api/login",
            "/user/api/signup",
            "/error/**",
            "/resources/**",
    };

    // logged users routes and APIs

    private static final String[] USER_OR_ABOVE_ENDPOINTS = {
            "/userpage/**",
            "/user/api/logout",
    };

    // managers routes and APIs

    private static final String[] MANAGER_OR_ABOVE_ENDPOINTS = {
            "/managerpage/**",
    };

    // admin reserved routes and APIs

    private static final String[] ADMIN_ONLY_ENDPOINTS = {
            "/adminpage/**",
            "/user/get/**",
            "/user/getAll/**",
            "/user/insert/**",
            "/user/update/**",
            "/user/delete/**",
    };


    // roles declaration

    private String ADMIN = "ADMIN";
    private String MANAGER = "MANAGER";
    private String USER = "USER";


    // user identification from db

    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Autowired
	private JWTRequestFilter jwtRequestFilter;

    // encoder

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
    
    // auth manager
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
    	return super.authenticationManagerBean();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImplementation);
        return daoAuthenticationProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(userDetailsServiceImplementation);

    }

    // authorizations

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .antMatchers(USER_OR_ABOVE_ENDPOINTS).hasAnyRole(USER, MANAGER, ADMIN)
                .antMatchers(MANAGER_OR_ABOVE_ENDPOINTS).hasAnyRole(MANAGER, ADMIN)
                .antMatchers(ADMIN_ONLY_ENDPOINTS).hasRole(ADMIN)

        ;

    }

}
