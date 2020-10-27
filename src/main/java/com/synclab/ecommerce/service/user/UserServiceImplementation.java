package com.synclab.ecommerce.service.user;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.repository.UserRepository;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CartServiceImplementation csi;
    
//    @Autowired
//    private EntityManager entityManager;

    @Override
    public Optional<User> findByFirstName(String name) {
        return userRepository.findByFirstName(name);
    }

    @Override
    public User insert(User user) {
    	User u = userRepository.save(user);
    	// creates an empty cart for the user
    	csi.insert(new Cart(u.getUserId()));
        return u;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByAccount_username(String username) {
        return userRepository.findByAccount_username(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User UpdateById(String id, User user) throws Exception {
    	
        User newUser = user;
        newUser.setUserId(id);

        return userRepository.save(newUser);

    }

    @Override
    public User PatchById(String id, User user) throws Exception {

        User newUser = findById(id);

        if (user.getUserId() != null)
            newUser.setUserId(user.getUserId());
        if (user.getAccount() != null)
            newUser.setAccount(user.getAccount());
        if (user.getAddress() != null)
            newUser.setAddress(user.getAddress());
        if (user.getFirstName() != null)
            newUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            newUser.setLastName(user.getLastName());
        if (user.getLastLoginDate() != null)
            newUser.setLastLoginDate(user.getLastLoginDate());
        if (user.getSignupDate() != null)
            newUser.setSignupDate(user.getSignupDate());

        userRepository.save(newUser);
        return newUser;

    }

    @Override
    public void DeleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();

    }

    @Override
    public User findByAccount(Account account) {
        return userRepository.findByAccount(account);
    }

	@Override
	public List<User> findByAddress(Address address) {
		System.err.println("not implemented yet");
		return null;
	}

//    // RSQL
//
//    @Override
//    public List<User> findByAddress(Address address) {
//        List<User> users = new ArrayList<>();
//        users = userRepository.findByAddress(address);
//        return users;
//    }
//
//    public List<User> rsqlQuery(String queryString) {
//        // We will need a JPA EntityManager
//
//        // Create the JPA Visitor
//        RSQLVisitor<CriteriaQuery<User>, EntityManager> visitor = new JpaCriteriaQueryVisitor<User>();
//
//        // Parse a RSQL into a Node
//        Node rootNode = new RSQLParser().parse(queryString);
//
//        // Visit the node to retrieve CriteriaQuery
//        CriteriaQuery<User> query = rootNode.accept(visitor, entityManager);
//
//        // Execute and get results
//        List<User> entities = entityManager.createQuery(query).getResultList();
//        return entities;
//    }


}
