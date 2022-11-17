package com.project.office.member.dto;

import com.project.office.dept.dto.DeptDTO;
import com.project.office.position.dto.PositionDTO;

import lombok.Data;

@Data
public class MemberHistoryDTO {

	private MemberDTO memberNo;
	private PositionDTO positionNo;
	private DeptDTO deptNo;
	private java.util.Date memberModifyDate;
	
}
