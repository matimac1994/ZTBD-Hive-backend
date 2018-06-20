package com.maciejak.ztbdhive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
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
	public List<User> getAllUsers() {
		String query = "select * from student";
		List<User> users = hiveJdbcTemplate.query(query, new UserRowMapper());
		return users;
	}

	@Override
	public User getUser(Long id) {
		User user = hiveJdbcTemplate.queryForObject(
				"select * from student where id = ?", new Object[] { id },
				new UserRowMapper());
		return user;
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
		Integer cnt = hiveJdbcTemplate.queryForObject(
				"SELECT count(*) FROM student WHERE id = ?", Integer.class,
				user.getId());
		return cnt != null && cnt > 0;
	}
	
	private Integer getNextId() {
		Integer max = hiveJdbcTemplate.queryForObject(
				"SELECT max(id) FROM student", Integer.class);
		return max != null ? max + 1 : 1;
	}

	public class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User u = new User();
			u.setId(rs.getLong("id"));
			u.setFirstName(rs.getString("firstName"));
			u.setLastName(rs.getString("lastName"));
			u.setAge(rs.getInt("age"));
			u.setCity(rs.getString("city"));
			return u;
		}
	}

}
