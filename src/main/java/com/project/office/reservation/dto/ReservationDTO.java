package com.project.office.reservation.dto;


import java.time.LocalDateTime;
//import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.office.member.entity.Member;
import com.project.office.room.entity.Room;

import lombok.Data;

@Data
public class ReservationDTO {

	private Long reservationNo;
	private Integer reservationUseTime;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime reservationDate;
	private String reservationStatus;
	private String reservationPurpose;
	private String reservationSetting;
	private Room room;
	private Member member;
	private String reservationTimeIn;
	private String reservationTimeOut;

	

	
}
