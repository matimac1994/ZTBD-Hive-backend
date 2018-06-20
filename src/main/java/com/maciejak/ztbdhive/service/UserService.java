package com.maciejak.ztbdhive.service;

import com.maciejak.ztbdhive.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void deleteUserById(Integer userId);
    User createOrUpdateUser(User user);
    User getUserById(Integer userId);
}
