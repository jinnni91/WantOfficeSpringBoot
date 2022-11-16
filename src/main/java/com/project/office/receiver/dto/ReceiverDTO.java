package com.project.office.receiver.dto;

import com.project.office.message.entity.Message;

import lombok.Data;

@Data
public class ReceiverDTO {

	private Message message;
	private String recycleStatus;
	private String removedStatus;
	
}
