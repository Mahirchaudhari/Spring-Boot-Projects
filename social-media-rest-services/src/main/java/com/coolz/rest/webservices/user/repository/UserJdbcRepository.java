package com.coolz.rest.webservices.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.coolz.rest.webservices.beans.User;

/**
 * JDBC Repository annotated as Repository which is used to interact with
 * database. Spring JDBC used to communicate with Database without writing complex code.
 * 
 * The Spring JDBC template allows to clean-up the resources automatically, e.g.
 * release the database connections.
 * 
 * The Spring JDBC template converts the standard JDBC SQLExceptions into
 * RuntimeExceptions. This allows the programmer to react more flexible to the
 * errors. The Spring JDBC template converts also the vendor specific error
 * messages into better understandable error messages.
 * 
 * In Spring JDBC we still need to write queries but no need to write java code.
 * 
 * @author Mahir
 *
 */
@Repository
public class UserJdbcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static String USER_INSERT_QUERY = """
				insert into users (name, birth_date) values (?, ?);
			""";
	private static String USER_DELETE_QUERY = """
			delete from `users` where id = ?;
			""";

	private static String USER_SELECT_QUERY = """
			select * from `users` where id = ?;
			""";

	public void insert(User user) {
		jdbcTemplate.update(USER_INSERT_QUERY, user.getName(), user.getBirthDate());
	}

	public void deleteByUserId(long userId) {
		jdbcTemplate.update(USER_DELETE_QUERY, userId);
	}

	public User fetchUserByUserID(long userId) {
		return jdbcTemplate.queryForObject(USER_SELECT_QUERY, new BeanPropertyRowMapper<>(User.class), userId);
	}

}
