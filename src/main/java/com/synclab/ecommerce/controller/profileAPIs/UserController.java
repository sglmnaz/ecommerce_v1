package com.synclab.ecommerce.controller.profileAPIs;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Address;
import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.account.AccountServiceImplementation;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import com.synclab.ecommerce.utility.pages.PageUtils;
import com.synclab.ecommerce.utility.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/user/api")
public class UserController {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private RoleServiceImplementation roleServiceImplementation;

    @Autowired
    private AccountServiceImplementation accountServiceImplementation;
    
    @Autowired
    private CartServiceImplementation cartServiceImplementation;


    // get

    @GetMapping(value = "/get/id/{id}", produces = "application/json")
    public ResponseEntity<User> findById(@PathVariable(value = "id") String id) {

        User newUser = userServiceImplementation.findById(id);

        return CustomResponse.getFindResponse(newUser, "user not found",
                "user with id: " + id + " could not be found.");

    }

    @GetMapping(value = "/get/all", produces = "application/json")
    public ResponseEntity<List<User>> findAll() {

        List<User> users = userServiceImplementation.findAll();

        return !users.isEmpty() ? ResponseEntity.ok(users)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

//    @GetMapping(value = "/get/query", produces = "application/json")
//    public ResponseEntity<Page<User>> findById(@RequestParam String query, @RequestParam(value = "page") Integer page,
//                                               @RequestParam(value = "size") Integer size) {
//
//        List<User> list = userServiceImplementation.rsqlQuery(query);
//        return PageUtils.listToPageResponseEntity(list, page, size);
//
//    }

    // patch
    @PatchMapping(value = "address/add/{id}",consumes = "application/json")
    public ResponseEntity<String> addAddress(@PathVariable(value = "id") String id,
    		@RequestBody Address address) {
    	
    	User user = userServiceImplementation.findById(id);
    	if (user == null)
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	user.addAddress(address);
    	user = userServiceImplementation.insert(user);
    	return ResponseEntity.ok("address changed");
    }
    
    @PatchMapping(value = "address/remove/{id}",consumes = "application/json")
    public ResponseEntity<String> removeAddress(@PathVariable(value = "id") String id,
    		@RequestBody Address address) {
    	
    	User user = userServiceImplementation.findById(id);
    	if (user == null)
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	user.removeAddress(address);
    	user = userServiceImplementation.insert(user);
    	return ResponseEntity.ok("address changed");
    }
    
    // delete

    @Transactional
    @DeleteMapping(value = "/delete/id/{id}")
    public ResponseEntity<User> delete(@PathVariable(name = "id") String id) {
       
    	User user = userServiceImplementation.findById(id);
        
    	cartServiceImplementation.deleteByUserId(id);
        userServiceImplementation.DeleteById(id);
        
        user = userServiceImplementation.findById(id);
        return CustomResponse.getDeleteResponse(user, "deletion failed", "could not delete entity with id: " + id);
    }

}
