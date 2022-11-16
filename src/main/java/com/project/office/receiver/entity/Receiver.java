package com.project.office.receiver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.project.office.message.entity.Message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_RECEIVER")
public class Receiver {

	@OneToOne
	@JoinColumn(name = "MSG_NO")
	private Message message;
	
	@Column(name = "RECYCLE_STATUS")
	private String recycleStatus;
	
	@Column(name = "REMOVED_STATUS")
	private String removedStatus;
	
}
