package com.maciejak.ztbdhive.service;

import com.maciejak.ztbdhive.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUserById(Long userId) {

    }

    @Override
    public User createOrUpdateUser(User user) {
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }
}
