package com.synclab.ecommerce.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

     @Bean
     public BCryptPasswordEncoder passwordEncoder()
     {
         return new BCryptPasswordEncoder();
     }

     @Bean
     @Override
     public UserDetailsService userDetailsService ()
    {
        UserBuilder users = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        //Utente1
        manager.createUser(
            users
            .username("client")
            .password(new BCryptPasswordEncoder().encode("password"))
            .roles("ROLE_CLIENT")
            .build()
        );

        //Utente2
        manager.createUser(
            users
            .username("admin")
            .password(new BCryptPasswordEncoder().encode("password"))
            .roles("ROLE_CLIENT","ROLE_ADMIN")
            .build()
        );

        return manager;

    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth
            .userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder());

    }

    private static final String[] ADMIN_CLIENTI_MATCHER =
    {
        "/user/insert/**",
        "/user/update/**",
        "/user/delete/**"
    };

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
        .authorizeRequests()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/login/**").permitAll()
        .antMatchers("/").hasAnyRole("ROLE_GUEST","ROLE_CLIENT")
        .antMatchers(ADMIN_CLIENTI_MATCHER).hasRole("ROLE_ADMIN")
        .antMatchers("/user/**").hasRole("ROLE_CLIENT")
        .and()
            .formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/login")
                .failureUrl("/login/form?error")
                    .usernameParameter("userId")
                    .passwordParameter("password")
        .and()
            .exceptionHandling()
            .accessDeniedPage("/login/form?forbidden")
        .and()
            .logout()
            .logoutUrl("/login/form?logout")
  
        //.and().csrf().disable()
        ;

    }
    
}
