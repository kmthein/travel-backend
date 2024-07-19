package com.travelbackend.services;

import com.travelbackend.dao.UserDAO;
import com.travelbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDAO.findByEmail(email);
    }
}
