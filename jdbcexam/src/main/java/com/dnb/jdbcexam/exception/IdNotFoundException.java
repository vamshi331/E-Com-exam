package com.dnb.jdbcexam.exception;

public class IdNotFoundException extends Exception{
    // Constructor to create the exception with a message
	public IdNotFoundException(String msg) {
		super(msg);
	}
    // Override toString() to include the message
	@Override
	public String toString() {
		return super.toString() + " " + getMessage();
	}
}
