package com.travelbackend.controllers;


import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.User;
import com.travelbackend.exception.ResourceNotFoundException;
import com.travelbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping ("/user")
    public ResponseEntity<?> createNewUser(@ModelAttribute User user, @RequestParam(value = "img_urls",required = false) List<String> imgUrls){
        ResponseDTO res = userService.addNewUser(user,imgUrls);
        return new ResponseEntity<>(res.getMessage(), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        if (userList == null){
            ResponseDTO res = new ResponseDTO();
            res.setMessage("User not found");
            return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        User user = userService.getUserById(id);
        if(user == null){
            throw new ResourceNotFoundException("User id:"  + id + "is not found");
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@ModelAttribute User user,@PathVariable int id, @RequestParam(value = "img_urls", required = false) List<String> imgUrls,@RequestParam(value = "delete_ids", required = false) List<Integer> deleteImg){
        userService.updateUser(user,id,imgUrls,deleteImg);
        return  new ResponseEntity<>("User Updated",HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id){
        ResponseDTO res = userService.deleteById(id);
        return  new ResponseEntity<>("User Deleted",HttpStatus.OK);
    }
}