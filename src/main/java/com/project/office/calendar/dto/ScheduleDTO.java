package com.project.office.calendar.dto;

import com.project.office.dept.entity.Dept;
import com.project.office.member.entity.Member;

import lombok.Data;

@Data
public class ScheduleDTO {

	private Long scheduleNo;
	private String scheduleTitle;
	private String scheduleStart;
	private String scheduleEnd;
	private String scheduleCre;
	private String scheduleUpd;
	private String scheduleSort;
	private String scheduleColor;
	private String schedulePlace;
	private String scheduleContent;
	private Member member;
	private Dept dept;
}
