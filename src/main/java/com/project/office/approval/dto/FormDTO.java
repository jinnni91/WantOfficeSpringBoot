package com.project.office.approval.dto;
import java.sql.Date;

import com.project.office.member.entity.Member;

import lombok.Data;
@Data
public class FormDTO {
	
	private Long dfNo;
	private String dfTitle;
	private String dfContent;
	private Date dfDate;
	private Date dfDelete;
	private String dfStatus;
	
	private Member member;

}
