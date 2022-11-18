package com.project.office.message.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.project.office.receiver.entity.Receiver;
import com.project.office.sender.entity.Sender;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_MESSAGE")
@SequenceGenerator(name = "MESSAGE_SEQ_GENERATOR",
					sequenceName = "SEQ_MSG_NO",
					initialValue = 1,
					allocationSize = 1)
public class Message {

	@Id
	@Column(name = "MSG_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Message_SEQ_GENERATOR")
	private Long messageNo;
	
	@Column(name = "MSG_TITLE")
	private String messageTitle;
	
	@Column(name = "MSG_CONTENT")
	private String messageContent;
	
	@Column(name = "MSG_SEND_DATE")
	private Date msgSendDate;
	
	@Column(name = "MSG_RECEIVED_DATE")
	private Date msgReceivedDate;
	
	@Column(name = "MSG_REMOVE_STATUS")
	private String msgRemoveStatus;
	
	@OneToOne
	@JoinColumn(name = "RECEIVER_NO")
	private Receiver receiver;
	
	@OneToOne
	@JoinColumn(name = "SENDER_NO")
	private Sender sender;
	
}
