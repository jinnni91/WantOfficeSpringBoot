package com.project.office.approval.dto;
import java.sql.Date;

import com.project.office.approval.entity.Document;
import com.project.office.member.dto.MemberDTO;

import lombok.Data;

@Data
public class ProgressDTO {
	
	private Long dpNo;
	private String dpStatus;
	private String dpcComment;
	private Date dpDate;
	private Date dpSignDate;
	private Long dpOrderNo;
	
	private MemberDTO member;
	//private Document document;
	private Long docNo;
}
