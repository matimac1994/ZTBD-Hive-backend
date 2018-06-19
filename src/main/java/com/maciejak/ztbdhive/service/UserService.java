package com.maciejak.ztbdhive.service;

import com.maciejak.ztbdhive.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void deleteUserById(Long userId);
    User createOrUpdateUser(User user);
    User getUserById(Long userId);
}
