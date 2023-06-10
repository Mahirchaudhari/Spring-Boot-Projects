package com.coolz.rest.webservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coolz.rest.webservices.beans.Post;
import com.coolz.rest.webservices.beans.User;
import com.coolz.rest.webservices.dao.PostDaoService;
import com.coolz.rest.webservices.dao.UserDaoService;
import com.coolz.rest.webservices.exception.PostNotFoundException;
import com.coolz.rest.webservices.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * PostResource is RestController class which will be directly called when user
 * try to access the REST URL.
 * 
 * @author Mahir
 *
 */
@RestController
public class PostResource {

	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private PostDaoService postDaoService;

	@GetMapping("/users/{userId}/posts")
	public List<Post> fetchPostForUser(@PathVariable long userId) {

		User user = userDaoService.findUser(userId);
		if (user == null) {
			throw new UserNotFoundException(String.valueOf(userId));
		}
		return user.getPosts();
	}

	@GetMapping("/users/{userId}/posts/{postId}")
	public EntityModel<Post> fetchUserPost(@PathVariable long userId, @PathVariable long postId) {

		User user = userDaoService.findUser(userId);
		if (user == null) {
			throw new UserNotFoundException(String.valueOf(userId));
		}

		Post post = postDaoService.findByPostId(postId);

		if (post == null) {
			throw new PostNotFoundException(String.valueOf(userId));
		}

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).fetchPostForUser(userId));

		EntityModel<Post> entityModel = EntityModel.of(post);
		entityModel.add(link.withRel("all-user-posts"));

		return entityModel;
	}

	@PostMapping("/users/{userId}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int userId, @Valid @RequestBody Post post) {

		User user = userDaoService.findUser(userId);
		if (user == null) {
			throw new UserNotFoundException(String.valueOf(userId));
		}

		post.setUser(user);

		Post updatedPost = postDaoService.createPost(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(updatedPost.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
