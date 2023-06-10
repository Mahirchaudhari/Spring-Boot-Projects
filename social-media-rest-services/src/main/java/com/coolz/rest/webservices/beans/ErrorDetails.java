package com.coolz.rest.webservices.beans;

import java.time.LocalDate;

/**
 * ErrorDetails POJO class to wrap error response in appropriate format.
 * 
 * @author Mahir
 *
 */
public class ErrorDetails {

	private LocalDate timestamp;
	private String message;
	private String details;

	public ErrorDetails(LocalDate timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
	}

}
