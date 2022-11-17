package com.project.office.rest.dto;

import com.project.office.member.dto.MemberDTO;

import lombok.Data;

@Data
public class RestDTO {
	
	private Long restNo;
	private java.util.Date restDate;
	private java.util.Date restUpdate;
	private java.util.Date restStart;
	private java.util.Date restEnd;
	private String restTitle;
	private String restReason;
	private String restResult;
	private MemberDTO member;
	private MemberDTO approval;

}
