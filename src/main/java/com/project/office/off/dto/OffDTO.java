package com.project.office.off.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.office.member.dto.MemberDTO;

import lombok.Data;

@Data
public class OffDTO {
	
	private Long offNo;
	private String offDate;
	private LocalDateTime offUpdate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private java.util.Date offStart;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private java.util.Date offEnd;
	private String offTitle;
	private String offReason;
	private String offResult;
	private MemberDTO member;
	private MemberDTO approval;

}
