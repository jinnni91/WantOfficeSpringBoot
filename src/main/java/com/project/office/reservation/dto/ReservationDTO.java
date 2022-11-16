package com.project.office.reservation.dto;

import java.sql.Date;

import com.project.office.room.dto.RoomDTO;

import lombok.Data;

@Data
public class ReservationDTO {

	private Long reservationNo;
	private Integer reservationTime;
	private Date reservationDate;
	private String reservationStatus;
	private String reservationPurpose;
	private String reservationRemoveStatus;
	private RoomDTO room;
	//private MemberDTO member;   //memberDTO 없으므로 주석
}
