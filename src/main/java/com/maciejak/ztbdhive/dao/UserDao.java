package com.maciejak.ztbdhive.dao;

import com.maciejak.ztbdhive.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;

    public List<User> getAllUsers(){
        StringBuilder builder = new StringBuilder();

        return null;

    }

}
