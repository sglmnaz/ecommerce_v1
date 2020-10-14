package com.synclab.ecommerce.controller;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.security.JWTProperties;
import com.synclab.ecommerce.security.utility.AuthenticationRequest;
import com.synclab.ecommerce.security.utility.AuthenticationResponse;
import com.synclab.ecommerce.security.utility.JWTUtils;
import com.synclab.ecommerce.security.utility.MyUserDetails;
import com.synclab.ecommerce.security.utility.UserDetailsServiceImplementation;
import com.synclab.ecommerce.service.account.AccountServiceImplementation;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth/api")
public class AuthenticationController {

    @Autowired
    private PasswordEncoder pe;
    
    @Autowired
    private AuthenticationManager am;
    
    @Autowired
    private UserDetailsServiceImplementation udsi;

    @Autowired
    private UserServiceImplementation usi;

    @Autowired
    private RoleServiceImplementation rsi;

    @Autowired
    private AccountServiceImplementation asi;

    // allows the user to access get an authentication token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest req) throws Exception {

    	try {
        	am.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
    	
    	final UserDetails userDetails = udsi.loadUserByUsername(req.getUsername());
    	final String jwt = JWTUtils.generateToken(userDetails);
    	
    	return ResponseEntity.ok(new AuthenticationResponse(jwt));
    	
    }

    // allows clients to register users 
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> signup(@RequestBody User request) {

        if (request == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        User user = request;
        Account account = user.getAccount();

        // initialize fields with default value and add tthem to db
        account.getRole().add(rsi.findByName("ROLE_CLIENT"));
        account.setPassword(pe.encode(account.getPassword()));
        account = asi.insert(account);

        // assign fields to entity
        user.setAccount(account);
        user.setSignupDate(new Date());

        // add entity to db
        user = usi.insert(user);

        return ResponseEntity.ok(account.getUsername() + " successfully registered");

    }

}
