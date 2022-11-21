package com.project.office.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.office.exception.dto.ApiExceptionDTO;
import com.project.office.member.exception.LoginFailedException;

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
}
