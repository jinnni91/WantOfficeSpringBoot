package com.project.office.message.dto;

import java.sql.Date;

import com.project.office.receiver.entity.Receiver;
import com.project.office.sender.entity.Sender;

import lombok.Data;

@Data
public class MessageDTO {

	private Long msgNo;
	private String msgTitle;
	private String msgContent;
	private Date msgSendDate;
	private Date msgReceivedDate;
	private String msgRemoveStatus;
	private Receiver receiver;
	private Sender sender;
	
}
