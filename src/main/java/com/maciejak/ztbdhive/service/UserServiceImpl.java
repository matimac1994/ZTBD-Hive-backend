package com.maciejak.ztbdhive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maciejak.ztbdhive.dao.IUserDao;
import com.maciejak.ztbdhive.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	IUserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUserById(Long userId) {
    	userDao.deleteUser(userId);
    }

    @Override
    public User createOrUpdateUser(User user) {
    	User retUser = null;
    	if (userDao.userExists(user))
    		retUser = userDao.editUser(user);
    	else 
    		retUser = userDao.addUser(user);
        return retUser;
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.getUser(userId);
    }
}
