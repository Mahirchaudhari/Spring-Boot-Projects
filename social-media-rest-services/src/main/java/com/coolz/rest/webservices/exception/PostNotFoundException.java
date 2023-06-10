package com.coolz.rest.webservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PostNotFoundException extends RuntimeException which is used to throw
 * exception if post not found or not available.
 * 
 * @author Mahir
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String message) {
		super(message);
	}
}
