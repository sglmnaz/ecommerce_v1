package com.synclab.ecommerce.repository;

import com.synclab.ecommerce.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByEmail(String email);

}