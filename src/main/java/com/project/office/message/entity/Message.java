package com.project.office.message.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

	
	
}
