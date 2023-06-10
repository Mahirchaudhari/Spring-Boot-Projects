package com.coolz.rest.webservices.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coolz.rest.webservices.beans.Post;
import com.coolz.rest.webservices.user.repository.PostSpringDataJpaRepository;

/**
 * PostDaoService class is used to communicate with repository to access user's
 * post data from database.
 * 
 * @author Mahir
 *
 */
@Component
public class PostDaoService {

	@Autowired
	private PostSpringDataJpaRepository springDataJpaRepository;

	/**
	 * Create post for specific user
	 * 
	 * @param post
	 * @return Post
	 */
	public Post createPost(Post post) {
		return springDataJpaRepository.save(post);
	}

	/**
	 * Fetch post by post Id
	 * 
	 * @param postId
	 * @return Post
	 */
	public Post findByPostId(long postId) {
		return springDataJpaRepository.findById(postId).get();
	}

}
