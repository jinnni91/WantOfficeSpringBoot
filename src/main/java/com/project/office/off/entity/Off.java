package com.project.office.off.entity;

import java.time.LocalDateTime;

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
@Table(name = "TBL_OFF")
@SequenceGenerator(name = "OFF_SEQ_GENERATOR",
	sequenceName = "SEQ_OFF_NO",
	initialValue = 1, allocationSize = 1)
public class Off {
	
	@Id
	@Column(name = "OFF_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFF_SEQ_GENERATOR")
	private Long offNo;
	
	@Column(name = "OFF_DATE")
	private java.util.Date offDate;
	
	@Column(name = "OFF_UPDATE")
	private LocalDateTime offUpdate;
	
	@Column(name = "OFF_START")
	private java.util.Date offStart;
	
	@Column(name = "OFF_END")
	private java.util.Date offEnd;
	
	@Column(name = "OFF_TITLE")
	private String offTitle;
	
	@Column(name = "OFF_REASON")
	private String offReason;
	
	@Column(name = "OFF_RESULT")
	private String offResult;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "APP_AUTH_NO")
	private Member approval;
	
	public void update(java.util.Date offStart, java.util.Date offEnd, String offTitle, String offReason) {
		
		this.offStart = offStart;
		this.offEnd = offEnd;
		this.offTitle = offTitle;
		this.offReason = offReason;
		
	}

}
