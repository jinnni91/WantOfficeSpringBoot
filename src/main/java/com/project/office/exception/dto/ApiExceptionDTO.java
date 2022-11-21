package com.project.office.exception.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiExceptionDTO {

	private int state;
	private String message;
	
	public ApiExceptionDTO(HttpStatus status, String message) {
		this.state = status.value();
		this.message = message;
	}
}
