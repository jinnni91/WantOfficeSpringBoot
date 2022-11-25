package com.project.office.off.dto;

import com.project.office.member.dto.MemberDTO;

import lombok.Data;

@Data
public class OffDTO {
	
	private Long offNo;
	private java.util.Date offDate;
	private java.util.Date offUpdate;
	private java.util.Date offStart;
	private java.util.Date offEnd;
	private String offTitle;
	private String offReason;
	private String offResult;
	private MemberDTO member;
	private MemberDTO approval;

}
