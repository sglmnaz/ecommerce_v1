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
    User findById(Long id);

    User findByAccount(Account account);

    User findByAccount_username(String username);

    List<User> findByAddress(Address address);

    Optional<User> findByFirstName(String name);

    List<User> findAll();

    //U
    User UpdateById(Long id, User user) throws Exception;

    User PatchById(Long id, User user) throws Exception;

    //D
    void DeleteById(Long id);

    void deleteAll();

}
