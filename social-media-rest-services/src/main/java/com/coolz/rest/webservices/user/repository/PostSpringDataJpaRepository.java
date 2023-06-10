package com.coolz.rest.webservices.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coolz.rest.webservices.beans.Post;

/**
 * PostSpringDataJpaRepository is interface to use the Spring Data JPA.
 * 
 * Spring Data JPA make JPA more simpler and provide wide variety of methods to
 * play with data.
 * 
 * To add custom method just add findBy following by entity name.
 * 
 * @author Mahir
 *
 */
public interface PostSpringDataJpaRepository extends JpaRepository<Post, Long> {

}
