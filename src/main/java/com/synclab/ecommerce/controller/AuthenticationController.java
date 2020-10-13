package com.synclab.ecommerce.controller;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.security.JWTProperties;
import com.synclab.ecommerce.security.JWTUtils;
import com.synclab.ecommerce.service.account.AccountServiceImplementation;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user/api")
public class AuthenticationController {

    @Autowired
    private PasswordEncoder pe;

    @Autowired
    private UserServiceImplementation usi;

    @Autowired
    private RoleServiceImplementation rsi;

    @Autowired
    private AccountServiceImplementation asi;

    // allows the user to access get an authentication token
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password) {

        //cerca utente tramite username

        User user = null;
        user = usi.findByAccount_username(username);

        // controllare se questo utente esiste

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        // verificare password

        String encodedPassword = user.getAccount().getPassword();
        if (!pe.matches(password, encodedPassword))
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();

        // genera un token d accesso che verrà usato per le successive chiamate

        String token = JWTUtils.doGenerateToken(username);
        System.out.println(JWTUtils.getUsernameFromToken(token));

        return ResponseEntity.ok(token);
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
