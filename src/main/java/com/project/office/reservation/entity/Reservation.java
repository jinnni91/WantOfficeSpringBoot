package com.project.office.reservation.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.project.office.member.dto.MemberDTO;
import com.project.office.member.entity.Member;
import com.project.office.room.dto.RoomDTO;
import com.project.office.room.entity.Room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_RESERVATION")
@SequenceGenerator(name = "RESERVATION_SEQ_GENERATOR",
					sequenceName = "SEQ_RESERVATION_NO",
					initialValue = 1,
					allocationSize = 1)
public class Reservation {

	@Id
	@Column(name = "RESERVATION_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVATION_SEQ_GENERATOR")
	private Long reservationNo;
	
	@Column(name = "RESERVATION_TIME")
	private Integer reservationTime;
	
	@Column(name = "RESERVATION_DATE")
	private Date reservationDate;
	
	@Column(name = "RESERVATION_STATUS")
	private String reservationStatus;
	
	@Column(name = "RESERVATION_PURPOSE")
	private String reservationPurpose;
	
	@Column(name = "RESERVATION_REMOVE_STATUS")
	private String reservationRemoveStatus;
	
	@ManyToOne
	@JoinColumn(name = "ROOM_NO")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;

	public void update(Long reservationNo, Integer reservationTime, String reservationStatus,
			String reservationPurpose, Room room, Member member) {
		
		this.reservationNo = reservationNo;
		this.reservationTime = reservationTime;
		this.reservationStatus = reservationStatus;
		this.reservationPurpose = reservationPurpose;
		this.room = room;
		this.member = member;
		
	}

		
	
	
}
