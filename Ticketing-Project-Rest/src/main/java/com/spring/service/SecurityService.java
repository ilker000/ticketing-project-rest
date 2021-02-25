package com.spring.service;

import com.spring.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.swing.*;
import java.nio.file.AccessDeniedException;

public interface SecurityService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User loadUser(String param) throws AccessDeniedException;
}
