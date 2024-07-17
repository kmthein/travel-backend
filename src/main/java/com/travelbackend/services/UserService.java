package com.travelbackend.services;

import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.dto.UserDTO;
import com.travelbackend.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    ResponseDTO addNewUser(User user,List<String> imgUrls);

    User getUserById(int id);

    ResponseDTO updateUser(User user, int id,List<String> imgUrls,List<Integer> deleteImg);

    ResponseDTO deleteById(int id);

    List<UserDTO> getAllNormalUsers();
}
