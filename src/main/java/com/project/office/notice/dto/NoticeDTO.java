package com.project.office.notice.dto;

import java.sql.Date;

import com.project.office.dept.entity.Dept;
import com.project.office.member.entity.Member;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private Long noticeNo;
	private Member member;	
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private Date noticeUpdate;
	private Date noticeDelete;
	private String noticeStatus;
	private String noticeType;
	private Dept dept;
}
