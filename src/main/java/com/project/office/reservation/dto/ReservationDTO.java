package com.project.office.reservation.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.project.office.member.entity.Member;
import com.project.office.room.entity.Room;

import lombok.Data;

@Data
public class ReservationDTO {

	private Long reservationNo;
	private Integer reservationTime;
	private Date reservationDate;
	private String reservationStatus;
	private String reservationPurpose;
	private String reservationRemoveStatus;
	private Room room;
	private Member member;
	
	private MultipartFile roomImage;
	
	private String roomImageUrl;
	
}
