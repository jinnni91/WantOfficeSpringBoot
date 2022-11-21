package com.project.office.notice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.project.office.dept.entity.Dept;
import com.project.office.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_NOTICE")
@SequenceGenerator(name = "NOTICE_SEQ_GENERATOR", 
			sequenceName = "SEQ_NOTICE_NO", 
			initialValue = 1, allocationSize= 1)
public class Notice {

	@Id
	@Column(name = "NOTICE_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ_GENERATOR")
	private Long noticeNo;
	
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO") // MEMBER_ID 에서 MEMBER_NO로 변경 
	private Member memberNo;
	
	@Column(name = "NOTICE_TITLE")
	private String noticeTitle;
	
	@Column(name = "NOTICE_CONTENT")
	private String noticeContent;
	
	@Column(name = "NOTICE_DATE")
	private Date noticeDate;
	
	@Column(name = "NOTICE_UPDATE")
	private Date noticeUpdate;
	
	@Column(name = "NOTICE_DELETE")
	private Date noticeDelete;
	
	@Column(name = "NOTICE_STATUS")
	private String noticeStatus;
	
	@Column(name = "NOTICE_TYPE")
	private String noticeType;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_NO")
	private Dept dept;

	
}
