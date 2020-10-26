package com.synclab.ecommerce.service.account;

import com.synclab.ecommerce.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    //C
    Account insert(Account account);

    //R
    List<Account> findAll();

    Optional<Account> findById(String id);

    Account findByEmail(String email);

    //U
    Account UpdateById(String id, Account account) throws Exception;

    Account PatchById(String id, Account account) throws Exception;

    //D
    void DeleteById(String id);

    void deleteAll();

}
