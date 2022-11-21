package com.project.office.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.office.exception.dto.ApiExceptionDTO;

@RestControllerAdvice
public class ApiExceptionAdvice {

	@ExceptionHandler(DuplicatedUsernameException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(DuplicatedUsernameException e) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
	
	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(LoginFailedException e) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(TokenException e) {
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new ApiExceptionDTO(HttpStatus.UNAUTHORIZED, e.getMessage()));
	}
}
