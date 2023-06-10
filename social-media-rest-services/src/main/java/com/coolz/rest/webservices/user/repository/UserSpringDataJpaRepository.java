package com.coolz.rest.webservices.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coolz.rest.webservices.beans.User;

/**
 * UserSpringDataJpaRepository is interface to use the Spring Data JPA.
 * 
 * Spring Data JPA make JPA more simpler and provide wide variety of methods to
 * play with data.
 * 
 * To add custom method just add findBy following by entity name.
 * 
 * @author Mahir
 *
 */
public interface UserSpringDataJpaRepository extends JpaRepository<User, Long> {

	List<User> findByName(String name);
}
