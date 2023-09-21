package com.dnb.jdbcexam.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Importing custom exceptions
import com.dnb.jdbcexam.exception.IdNotFoundException;
import com.dnb.jdbcexam.exception.InvalidIdException;

//This class provides centralized exception handling across all methods in the application
	@ControllerAdvice
	public class Appadvice {
		
	    // This method handles HttpRequestMethodNotSupportedException
		@ExceptionHandler(HttpRequestMethodNotSupportedException.class)

		public ResponseEntity<Map<String,String>> handleException(HttpRequestMethodNotSupportedException e) {

			String provided = e.getMethod();
			List<String> supported = List.of(e.getSupportedMethods());
			String error = provided+" is one of the supported Http Methods (" +
			String.join(", ",supported)
			+")";

			Map<String, String> errorResponse = Map.of("error",error,"message",e.getLocalizedMessage(),
					"Status",HttpStatus.METHOD_NOT_ALLOWED.toString());
			return new ResponseEntity<>(errorResponse , HttpStatus.METHOD_NOT_ALLOWED);

		}
	    // This method handles IdNotFoundException
		@ExceptionHandler(IdNotFoundException.class)
		public ResponseEntity<?> IdNotFoundExceptionHandler (IdNotFoundException e){
			Map<String, String> map = new HashMap<>();

			map.put("Message", e.getMessage());
			map.put("HttpStatus", HttpStatus.NOT_FOUND + "");

			return new ResponseEntity(map, HttpStatus.NOT_FOUND);

		}
		
	    // This method handles InvalidIdException
		@ExceptionHandler(InvalidIdException.class)
		public ResponseEntity<?> InvalidIdExceptionHandler (InvalidIdException e){
			Map<String, String> map = new HashMap<>();

			map.put("Message", e.getMessage());
			map.put("HttpStatus", HttpStatus.NOT_FOUND + "");

			return new ResponseEntity(map, HttpStatus.NOT_FOUND);

		}
	}
