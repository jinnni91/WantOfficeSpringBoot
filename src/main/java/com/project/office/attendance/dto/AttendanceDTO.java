package com.project.office.attendance.dto;

import com.project.office.member.dto.MemberDTO;

import lombok.Data;

@Data
public class AttendanceDTO {
	
	private Long attNo;
	private java.util.Date attIn;
	private java.util.Date attOut;
	private java.util.Date attDate;
	private String attType;
	private Long attTime;
	private MemberDTO member;

}
