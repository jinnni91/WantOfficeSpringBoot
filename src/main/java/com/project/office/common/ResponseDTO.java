package com.project.office.common;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseDTO {
	
	private int status;
	private String message;
	private Object data;
	
	public ResponseDTO(HttpStatus status, String message, Object data) {
		this.status = status.value();
		this.message = message;
		this.data = data;
	}
	
	
}
