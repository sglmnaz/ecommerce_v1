package com.synclab.ecommerce.security;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;
    private Account account;

    public MyUserDetails(User user) {
        this.user = user;
        account = this.user.getAccount();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = account.getRole();

        printRoles(roles);

        for (Role role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            authorities.add(authority);
        }

        return authorities;
    }

    private void printRoles(List<Role> roles) {
        System.out.println("");
        System.out.print("Class " + this.getClass().getSimpleName() + " says: User ( " + account.getUsername() + " ) logged in with the following authorities: ");
        for (Role role : roles) {
            System.out.print(role.getName() + ", ");
        }
        System.out.println("");
        System.out.println("");
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return !account.getIsBanned() && !account.getIsSuspended();
    }

}
