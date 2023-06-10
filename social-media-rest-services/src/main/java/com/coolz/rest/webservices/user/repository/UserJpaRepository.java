package com.coolz.rest.webservices.user.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.coolz.rest.webservices.beans.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * 
 * JPA Repository annotated as Repository which is used to interact with
 * database. JPA used to communicate with Database without writing complex code
 * as well as queries.
 * 
 * In JPA we do not write any SQL or JavaCode to fetch or insert data on
 * database.
 * 
 * @author Mahir
 *
 */
@Repository
@Transactional
public class UserJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public User addUser(User user) {
		return entityManager.merge(user);
	}

	public User fetchUserByUserID(long id) {
		return entityManager.find(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> fetchAllUser() {
		return entityManager.createNativeQuery("select * from `users`", User.class).getResultList();
	}

	public void deleteByUserId(long id) {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
	}

}
