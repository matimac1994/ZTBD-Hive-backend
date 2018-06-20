package com.maciejak.ztbdhive.dao;

import java.util.List;

import com.maciejak.ztbdhive.model.User;

public interface IUserDao {
	public List<User> getAllUsers();

	public User getUser(Long id);

	public User addUser(User user);

	public User editUser(User user);

	public void deleteUser(Long id);
	
	public boolean userExists(User user);
}
