package com.travelbackend.services;

import com.travelbackend.dao.UserDAO;
import com.travelbackend.dto.ResponseTokenDTO;
import com.travelbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);

    }

    public ResponseTokenDTO login(User user){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        User existUser = userDAO.findByEmail(user.getEmail());
        String token = jwtService.generateToken(existUser);
        return new ResponseTokenDTO(token);
    }
}
