package com.project.office.rest.entity;

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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_REST")
@SequenceGenerator(name = "REST_SEQ_GENERATOR",
	sequenceName = "SEQ_REST_NO",
	initialValue = 1, allocationSize = 1)
public class Rest {
	
	@Id
	@Column(name = "REST_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REST_SEQ_GENERATOR")
	private Long restNo;
	
	@Column(name = "REST_DATE")
	private java.util.Date restDate;
	
	@Column(name = "REST_UPDATE")
	private java.util.Date restUpdate;
	
	@Column(name = "REST_START")
	private java.util.Date restStart;
	
	@Column(name = "REST_END")
	private java.util.Date restEnd;
	
	@Column(name = "REST_TITLE")
	private String restTitle;
	
	@Column(name = "REST_REASON")
	private String restReason;
	
	@Column(name = "REST_RESULT")
	private String restResult;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO1")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO2")
	private Member approval;

}
