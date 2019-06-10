package com.example.pharmacy.service;

import com.example.pharmacy.model.Role;
import com.example.pharmacy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

public class MyUserPrincipal implements UserDetails {
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    private User user;

    public MyUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*
Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); // use list if you wish
for (AppRole role : user.getRoles()) {
    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
}
    }
         */
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        //System.out.println(user.getRoles().toString());
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String pass = encoder.encode(user.getPassword());

        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
