package com.project.office.dept.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DeptDTO {

	private Long deptNo;
	private String deptName;
	private String deptStatus;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private java.util.Date deptDate;
	
}
