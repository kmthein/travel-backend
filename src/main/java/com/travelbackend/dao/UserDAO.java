package com.travelbackend.dao;

import com.travelbackend.entity.User;

import java.util.List;

public interface UserDAO {

    void save(User user);

    User findUserById(int userId);

    List<User> findAll();

    void update(User user);

    void delete(int userId);
}
