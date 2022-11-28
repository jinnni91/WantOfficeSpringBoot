package com.project.office.room.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_ROOM")
@SequenceGenerator(name = "ROOM_SEQ_GENERATOR", 
					sequenceName = "SEQ_ROOM_NO", 
					initialValue = 1, 
					allocationSize = 1)
public class Room {

	@Id
	@Column(name = "ROOM_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ROOM_SEQ_GENERATOR")
	private Long roomNo;
	
	@Column(name = "ROOM_NAME")
	private String roomName;
	
	@Column(name = "ROOM_LOCATION")
	private String roomLocation;
	
	@Column(name = "ROOM_CAPACITY")
	private Integer roomCapacity;
	
	@Column(name = "ROOM_FILE_URL")
	private String roomFileUrl;
	
	// 수정 용도 메소드 
	public void update(String roomName, String roomLocation, Integer roomCapacity,
			String roomFileUrl) {
		this.roomName = roomName;
		this.roomLocation = roomLocation;
		this.roomCapacity = roomCapacity;
		this.roomFileUrl = roomFileUrl;
	}

}
