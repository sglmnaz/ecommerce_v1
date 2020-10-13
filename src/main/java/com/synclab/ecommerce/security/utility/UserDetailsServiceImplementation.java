package com.synclab.ecommerce.security.utility;

import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private UserServiceImplementation usi;

    public UserDetailsServiceImplementation(UserServiceImplementation userServiceImp) {
        this.usi = userServiceImp;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = usi.findByAccount_username(username);
        return new MyUserDetails(user);

    }

}
