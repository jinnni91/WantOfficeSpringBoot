package com.project.office.notice.dto;

import java.util.Date;

import com.project.office.member.dto.MemberDTO;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private Long noticeNo;
	private MemberDTO member;	
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private Date noticeUpdate;
	private Date noticeDelete;
	private String noticeStatus;
}
