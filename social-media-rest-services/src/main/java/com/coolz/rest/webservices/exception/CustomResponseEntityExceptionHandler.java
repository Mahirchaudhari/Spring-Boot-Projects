package com.coolz.rest.webservices.exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.coolz.rest.webservices.beans.ErrorDetails;

/**
 * CustomResponseEntityExceptionHandler used to return appropriate message in
 * response
 * 
 * @author Mahir
 *
 */
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {

		return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {

		return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false)),
				HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), ex.getFieldError().getDefaultMessage(),
				request.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
}
