package com.project.office.member.dto;

import com.project.office.auth.dto.AuthDTO;
import com.project.office.dept.dto.DeptDTO;
import com.project.office.position.dto.PositionDTO;

import lombok.Data;

@Data
public class MemberDTO {

	private Long memberNo;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private String memberStatus;
	private Long memberRest;
	private PositionDTO positionNo;
	private DeptDTO deptNo;
	private java.util.Date memberJoinDate;
	private String memberFileUrl;
	private AuthDTO authNo;
	
}
