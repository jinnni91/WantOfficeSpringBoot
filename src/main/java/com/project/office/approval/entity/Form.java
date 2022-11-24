package com.project.office.approval.entity;
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

import com.project.office.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_FORM")
@SequenceGenerator(name = "DF_SEQ_GENERATOR",
sequenceName = "SEQ_DF_NO",
initialValue = 1, allocationSize = 1)
public class Form {
	
	@Id
	@Column(name = "DF_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DF_SEQ_GENERATOR")
	private Long dfNo;
	
	@Column(name = "DF_TITLE")
	private String dfTitle;
	
	@Column(name = "DF_CONTENT")
	private String dfContent;
	
	@Column(name = "DF_DATE")
	private Date dfDate;
	
	@Column(name = "DF_DELETE")
	private Date dfDelete;
	
	@Column(name = "DF_STATUS")
	private String dfStatus;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;	

}
