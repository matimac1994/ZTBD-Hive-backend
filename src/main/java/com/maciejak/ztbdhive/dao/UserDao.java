package com.maciejak.ztbdhive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        List<Map<String, Object>> userRows = hiveJdbcTemplate.queryForList("select * from student");
        for (Map row: userRows){
        	User u = new User();
        	u.setId((Long) row.get("id"));
			u.setFirstName((String) row.get("firstName"));
			u.setLastName((String) row.get("lastName"));
			u.setAge((Integer) row.get("age"));
			u.setCity((String) row.get("city"));
			users.add(u);
		}
        return users;
    }

	@Override
	public User getUser(Long id) {

		return null;
	}

	@Override
	public User addUser(User user) {
		final String INSERT_SQL = "insert into student (firstName, lastName, age, city) values(?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		hiveJdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL,
						new String[] { "id" });
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setInt(3, user.getAge());
				ps.setString(4, user.getCity());
				return ps;
			}
		}, keyHolder);

		return getUser(keyHolder.getKey().longValue());
	}

	@Override
	public User editUser(User user) {
		final String UPDATE_SQL = "update student set firstName = ?, lastName = ?, age = ?, city = ? where id = ?";
		hiveJdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setInt(3, user.getAge());
				ps.setString(4, user.getCity());
				ps.setLong(5, user.getId());
				return ps;
			}
		});

		return getUser(user.getId());
	}

	@Override
	public void deleteUser(Long id) {
		String query = "delete from student where id = " + id;
		hiveJdbcTemplate.update(query);
	}

	@Override
	public boolean userExists(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
