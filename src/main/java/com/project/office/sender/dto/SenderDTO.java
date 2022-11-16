package com.project.office.sender.dto;

import com.project.office.message.entity.Message;

import lombok.Data;

@Data
public class SenderDTO {

	private Message message;
	private String recycleStatus;
	private String removedStatus;
	
}
