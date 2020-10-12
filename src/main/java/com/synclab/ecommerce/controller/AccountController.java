package com.synclab.ecommerce.controller;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController()
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    // insert - takes a JSON body <- stores the object in the db -> outputs Response
    // entity
    @PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> insert(@RequestBody final Account requestBody) {

        if (requestBody == null) // return error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        final Account account = accountRepository.save(requestBody);

        return ResponseEntity.ok(account);
    }

    // get - takes a path variable <- find the object in the db -> outputs Response
    // entity
    @GetMapping(value = "/findById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> findById(@PathVariable(value = "id") Long id) {

        if (id == null) // return error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        final Account account = accountRepository.findById(id).get();
        return ResponseEntity.ok(account);
    }

    // update - takes a path variable and a request body <- find the object in the
    // db and updates -> outputs Response entity
    @PutMapping(value = "/updateById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> updateById(@PathVariable(value = "id") Long id,
                                              @RequestBody final Account requestBody) {

        if (id == null || requestBody == null) // returns an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        if (!accountRepository.findById(id).isPresent()) // returns an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        Account account = requestBody;
        account.setAccountId(id);
        accountRepository.save(account);

        return ResponseEntity.ok(account);
    }

    // patch - takes a path variable and a request body <- find the object in the
    // db and patches -> outputs Response entity
    @PatchMapping(value = "/patchById/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> patchById(@PathVariable(value = "id") Long id,
                                             @RequestBody final Account requestBody) {

        if (id == null || requestBody == null) // returns an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        Account oldAccount = accountRepository.findById(id).get();

        if (oldAccount == null) // returns an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        Account newAccount = oldAccount;

        // patching
        if (requestBody.getEmail() != null)
            newAccount.setEmail(requestBody.getEmail());
        if (requestBody.getUsername() != null)
            newAccount.setUsername(requestBody.getUsername());
        if (requestBody.getPassword() != null)
            newAccount.setPassword(requestBody.getPassword());
        if (requestBody.getPhone() != null)
            newAccount.setPhone(requestBody.getPhone());
        if (requestBody.getRole() != null)
            newAccount.setRole(requestBody.getRole());
        if (requestBody.getIsSuspended() != null)
            newAccount.setIsSuspended(requestBody.getIsSuspended());
        if (requestBody.getIsBanned() != null)
            newAccount.setIsBanned(requestBody.getIsBanned());

        newAccount.setAccountId(id);
        newAccount = accountRepository.save(newAccount);

        return ResponseEntity.ok(newAccount);
    }

    // delete - takes a path variable <- delete the object in the db -> outputs
    // Response entity
    @DeleteMapping(value = "/findById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {

        if (id == null) // return error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        accountRepository.deleteById(id);
        return ResponseEntity.ok(new Date() + " - Entity deleted succesfully.");
    }

}
