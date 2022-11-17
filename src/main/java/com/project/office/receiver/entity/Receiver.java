package com.project.office.receiver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Receiver implements Serializable {
	
	@Id
	@OneToOne
	@JoinColumn(name = "MSG_NO")
	private Message message;
	
	@Column(name = "RECYCLE_STATUS")
	private String recycleStatus;
	
	@Column(name = "REMOVED_STATUS")
	private String removedStatus;
	
}
