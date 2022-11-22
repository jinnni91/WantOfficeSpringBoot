package com.project.office.room.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RoomDTO {

	private Long roomNo;
	private String roomName;
	private String roomLocation;
	private Integer roomCapacity;
	private String roomFileUrl;
	
	private MultipartFile roomImage;
	
	
}
