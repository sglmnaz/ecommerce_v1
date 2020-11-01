package com.synclab.ecommerce.service.user;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //C
    User insert(User user);

    //R
    User findById(String id);

    User findByAccount(Account account);

    User findByAccount_username(String username);

    List<User> findByAddress(Address address);

    Optional<User> findByFirstName(String name);

    List<User> findAll();

    //U
    User UpdateById(String id, User user) throws Exception;

    User PatchById(String id, User user) throws Exception;

    //D
    void DeleteById(String id);

    void deleteAll();

	User findByAccount_email(String email);

}
