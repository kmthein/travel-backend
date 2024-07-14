package com.travelbackend.controllers;


import com.travelbackend.dto.ResponseTokenDTO;
import com.travelbackend.entity.User;
import com.travelbackend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@ModelAttribute User user) throws URISyntaxException {
      authService.register(user);
      URI uri = new URI("/api/login");
      return ResponseEntity.created(uri).build();
    }

    @PostMapping("login")
    public ResponseEntity<ResponseTokenDTO> login(@ModelAttribute User user){
        ResponseTokenDTO res = authService.login(user);
        return ResponseEntity.ok(res);
    }

}
