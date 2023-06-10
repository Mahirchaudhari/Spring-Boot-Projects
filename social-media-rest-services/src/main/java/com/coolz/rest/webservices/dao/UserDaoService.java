package com.coolz.rest.webservices.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coolz.rest.webservices.beans.User;
import com.coolz.rest.webservices.user.repository.UserSpringDataJpaRepository;

/**
 * UserDaoService class is used to communicate with repository to access User
 * data from database.
 * 
 * @author Mahir
 *
 */
@Component
public class UserDaoService {

	@Autowired
	private UserSpringDataJpaRepository userSpringDataJpaRepository;

	/**
	 * Fetch All user from database
	 * 
	 * @return List of User
	 */
	public List<User> fetchAllUser() {
		return userSpringDataJpaRepository.findAll();
	}

	/**
	 * Create new user in database
	 * 
	 * @param user
	 * @return User
	 */
	public User createUser(User user) {
		return userSpringDataJpaRepository.save(user);
	}

	/**
	 * Fetch user based on user id from database
	 * 
	 * @param userId
	 * @return User
	 */
	public User findUser(long userId) {
		return userSpringDataJpaRepository.findById(userId).get();
	}

	/**
	 * Delete user from database
	 * 
	 * @param userId
	 */
	public void deleteUser(long userId) {
		userSpringDataJpaRepository.deleteById(userId);
	}

}
