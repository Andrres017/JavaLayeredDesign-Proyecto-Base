package com.example.mycrud.service;

import com.example.mycrud.model.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User getUserById(Integer id);
    User createUser(User user);
    User updateUser(Integer id, User userDetails);
    void deleteUser(Integer id);
}
