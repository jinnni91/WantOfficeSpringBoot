package com.project.office.approval.dto;

import java.sql.Date;
import java.util.List;


import com.project.office.member.dto.MemberDTO;

import lombok.Data;
@Data
public class DocumentDTO {
	
	private Long docNo;
	private String docTitle;
	private String docContent;
	private Date docDate;
	private Date docUpdate;
	private Date docDelete;
	private String docStatus;
	
	private MemberDTO member;
	private FormDTO form;
	private List<ProgressDTO> progress; 
}
