package com.synclab.ecommerce.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    // admin reserved routes and APIs
    private static final String[] ADMIN_ONLY_ENDPOINTS = {
        "/user/insert/**",
        "/user/update/**",
        "/user/delete/**"
    };
    
    // logged users routes and APIs
    private static final String[] CLIENT_OR_ABOVE_ENDPOINTS = {
         "/user/get/**",
         "/user/getAll/**"
    };
    
    // public routes and APIs
    private static final String[] GUEST_OR_ABOVE_ENDPOINTS = {
         "/",
         "/resources/**",
         "/login/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth
            .inMemoryAuthentication()

                .withUser("admin")
                .password(passwordEncoder().encode("password")).roles("ROLE_ADMIN")
                .and()

                .withUser("client")
                .password(passwordEncoder().encode("password")).roles("ROLE_CLIENT")

        ;

    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
            .antMatchers(GUEST_OR_ABOVE_ENDPOINTS).permitAll()
            .antMatchers(CLIENT_OR_ABOVE_ENDPOINTS).hasAnyRole("ROLE_CLIENT","ROLE_ADMIN")
            .antMatchers(ADMIN_ONLY_ENDPOINTS).hasRole("ROLE_ADMIN")

            .and().formLogin()
               .loginPage("/login/form")
               .loginProcessingUrl("/login")
               .failureUrl("/login/form?error")
                   .usernameParameter("username")
                   .passwordParameter("password")

            .and()
                .exceptionHandling()
                .accessDeniedPage("/login/form?forbidden")

            .and()
                .logout()
                .logoutUrl("/login/form?logout")

//		    .and()
//               .csrf().disable()
        ;

	}

}
