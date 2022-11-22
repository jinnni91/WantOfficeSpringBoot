package com.project.office.attendance.dto;

import java.time.LocalDateTime;

import com.project.office.member.dto.MemberDTO;

import lombok.Data;

@Data
public class AttendanceDTO {
	
	private Long attNo;
	private LocalDateTime attIn;
	private LocalDateTime attOut;
	private String attDate;
	private String attType;
	private String attTime;
	private MemberDTO member;
      
}
