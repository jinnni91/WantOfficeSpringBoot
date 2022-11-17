package com.project.office.attendance.entity;

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
@Table(name = "TBL_ATT")
@SequenceGenerator(name = "ATT_SEQ_GENERATOR",
		sequenceName = "SEQ_ATT_NO",
		initialValue = 1, allocationSize = 1)
public class Attendance {
	
	@Id
	@Column(name = "ATT_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATT_SEQ_GENERATOR")
	private Long attNo;
	
	@Column(name = "ATT_IN")
	private java.util.Date attIn;
	
	@Column(name = "ATT_OUT")
	private java.util.Date attOut;
	
	@Column(name = "ATT_DATE")
	private java.util.Date attDate;
	
	@Column(name = "ATT_TYPE")
	private String attType;
	
	@Column(name = "ATT_TIME")
	private Long attTime;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;

}
