package com.project.office.Calendar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter	
@Setter
@Entity
@Table(name = "TBL_SCHEDULE")
@SequenceGenerator(name = "SCHEDULE_SEQ_GENERATOR", sequenceName = "SEQ_SCHEDULE_NO", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Schedule {
	
	@Id
	@Column(name = "SCHEDULE_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_SEQ_GENERATOR")
	private Long scheduleNo;
	
	@Column(name = "SCHEDULE_TITLE")
	private String scheduleTitle;
	
	@Column(name = "SCHEDULE_START")
	private Date scheduleStart;
	
	@Column(name = "SCHEDULE_END")
	private Date scheduleEnd;
	
	@Column(name = "SCHEDULE_CRE")
	private Date scheduleCre;
	
	@Column(name = "SCHEDULE_UPD")
	private Date scheduleUpd;
	
	@Column(name = "SCHEDULE_SORT")
	private String scheduleSort;
	
	@Column(name = "SCHEDULE_COLOR")
	private String scheduleColor;
	
	@Column(name = "SCHEDULE_PLACE")
	private String schedulePlace;
	
	@Column(name = "SCHEDULE_CONTENT")
	private String scheduleContent;
	
	//	@ManyToOne
	//	@JoinColumn(name="MEMBER_NAME")
//	private Member memberName;
//	private Dept deptNo;

}
