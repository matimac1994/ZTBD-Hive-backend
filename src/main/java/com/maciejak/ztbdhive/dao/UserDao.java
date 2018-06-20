package com.maciejak.ztbdhive.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.maciejak.ztbdhive.model.User;

@Component
public class UserDao implements IUserDao {

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;

    @Override
    public List<User> getAllUsers(){
    	List<User> users = new ArrayList<>();
    	// TODO
    	
        StringBuilder builder = new StringBuilder();

        return users;
    }

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User editUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
