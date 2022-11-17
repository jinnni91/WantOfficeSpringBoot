package com.project.office.customer.dto;

import com.project.office.member.dto.MemberDTO;

import lombok.Data;

@Data
public class CustomerDTO {
	
	private Long customerNo;
	private String customerName;
	private String customerEmployee;
	private String customerPhone;
	private String customerEmail;
	private String customerDelete;
	private String customerPosition;
	private String customerShare;
	private MemberDTO member;

}
