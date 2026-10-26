package com.example.demo.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	private LocalDateTime timestamp;
	private String message;
	private int status;
	private String path;
	
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ErrorResponse(LocalDateTime timestamp, String message, int status, String path) {
	    this.timestamp = timestamp;
	    this.message = message;
	    this.status = status;
	    this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}
