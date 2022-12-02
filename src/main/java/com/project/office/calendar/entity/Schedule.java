package com.project.office.calendar.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.project.office.dept.entity.Dept;
import com.project.office.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 
@Getter	
@Setter
@ToString
@Entity
@Table(name = "TBL_SCHEDULE")
@SequenceGenerator(name = "SCHEDULE_SEQ_GENERATOR", sequenceName = "SEQ_SCHEDULE_NO", initialValue = 1, allocationSize = 1)
@DynamicInsert
@DynamicUpdate
public class Schedule {
	
	@Id
	@Column(name = "SCHEDULE_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_SEQ_GENERATOR")
	private Long scheduleNo;
	
	@Column(name = "SCHEDULE_TITLE")
	private String scheduleTitle;
	
	@Column(name = "SCHEDULE_START")
	private String scheduleStart;
	
	@Column(name = "SCHEDULE_END")
	private String scheduleEnd;
	
	@Column(name = "SCHEDULE_CRE")
	private String scheduleCre;
	
	@Column(name = "SCHEDULE_UPD")
	private String scheduleUpd;
	
	@Column(name = "SCHEDULE_SORT")
	private String scheduleSort;
	
	@Column(name = "SCHEDULE_COLOR")
	private String scheduleColor;
	
	@Column(name = "SCHEDULE_PLACE")
	private String schedulePlace;
	
	@Column(name = "SCHEDULE_CONTENT")
	private String scheduleContent;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_NO")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="DEPT_NO")
	private Dept dept;
	
	public void update(String scheduleTitle, String scheduleStart, String scheduleEnd,
			String scheduleSort, String scheduleColor, String schedulePlace, String scheduleContent) {
		
		this.scheduleTitle = scheduleTitle;
		this.scheduleStart = scheduleStart;
		this.scheduleEnd = scheduleEnd;
		this.scheduleSort = scheduleSort;
		this.scheduleColor = scheduleColor;
		this.schedulePlace = schedulePlace;
		this.scheduleContent = scheduleContent;
		this.scheduleUpd = LocalDate.now().toString();
	}

}
