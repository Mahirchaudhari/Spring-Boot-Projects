package com.coolz.rest.webservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coolz.rest.webservices.beans.User;
import com.coolz.rest.webservices.dao.UserDaoService;
import com.coolz.rest.webservices.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * UserResource is RestController class which will be directly called when user
 * try to access the REST URL.
 * 
 * @author Mahir
 *
 */
@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> retriveAllUser() {
		return userDaoService.fetchAllUser();
	}

	@GetMapping("/users/{userId}")
	public EntityModel<User> retriveUser(@PathVariable int userId) {

		User user = userDaoService.findUser(userId);
		if (user == null) {
			throw new UserNotFoundException(String.valueOf(userId));
		}

		/* Adding link to show all user link */
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUser());
		EntityModel<User> entityModel = EntityModel.of(user);
		entityModel.add(link.withRel(ALL_USER));

		return entityModel;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

		User updatedUser = userDaoService.createUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(updatedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userDaoService.deleteUser(userId);
	}

	private static final String ALL_USER = "all-user";

}
