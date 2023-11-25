package com.coolz.microservice.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coolz.microservice.todo.model.Todo;

/**
 * @author Mahir
 *
 */
public interface TodoJpaRepository extends JpaRepository<Todo, Integer> {
	
	public List<Todo> findByUserName(String userName);
}
