package com.travelbackend.controllers;


import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.dto.ResponseTokenDTO;
import com.travelbackend.entity.User;
import com.travelbackend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@ModelAttribute User user, @RequestParam(value = "imgUrl", required = false) List<String> imgUrls) throws URISyntaxException {
      ResponseDTO res = authService.register(user, imgUrls);
      if (res.getStatus().equals("403")) {
          return new ResponseEntity<>(res, HttpStatus.CONFLICT);
      }
      URI uri = new URI("/api/login");
      return ResponseEntity.created(uri).build();
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@ModelAttribute User user){
        Object res = authService.login(user);
        return ResponseEntity.ok(res);
    }

}
