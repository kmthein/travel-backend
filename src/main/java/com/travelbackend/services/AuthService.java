package com.travelbackend.services;

import com.travelbackend.dao.UserDAO;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.dto.ResponseTokenDTO;
import com.travelbackend.dto.UserDTO;
import com.travelbackend.entity.Image;
import com.travelbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ResponseDTO register(User user, List<String> imgUrls){
        System.out.println(user);
        User userExist = userDAO.findByEmail(user.getEmail());
        System.out.println(userExist);
        ResponseDTO responseDTO = new ResponseDTO();
        if (userExist != null) {
            responseDTO.setMessage("Email already existed");
            responseDTO.setStatus("403");
            return responseDTO;
        } else {
        List<Image> imageList = new ArrayList<>();
        if (imgUrls != null) {
            for (String img : imgUrls) {
                Image image = new Image();
                image.setImgUrl(img);
                image.setUser(user);
                imageList.add(image);
            }
            user.setImageList(imageList);
        }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.save(user);
            responseDTO.setMessage("User register successfully");
            responseDTO.setStatus("201");
            return responseDTO;
        }
    }

    public Object login(User user){
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
            User existUser = userDAO.findByEmail(user.getEmail());
            String token = jwtService.generateToken(existUser);
            ResponseTokenDTO res = new ResponseTokenDTO();
            res.setToken(token);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(existUser.getId());
            userDTO.setUsername(existUser.getUsername());
            userDTO.setEmail(existUser.getEmail());
            userDTO.setContactNumber(existUser.getContactNumber());
            userDTO.setDob(existUser.getDob());
            userDTO.setImage(existUser.getImageList());
            userDTO.setRole(existUser.getRole());
            userDTO.setEmailReceived(existUser.isEmailReceive());
            res.setUserDetails(userDTO);
            return res;
        } catch (UsernameNotFoundException | BadCredentialsException ex) {
            return new ResponseEntity<>("Incorrect email or password", HttpStatus.UNAUTHORIZED);
        }
    }
}
