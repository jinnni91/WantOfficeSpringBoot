package com.project.office.calendar.dto;

import com.project.office.dept.dto.DeptDTO;
import com.project.office.member.dto.MemberDTO;

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
	private MemberDTO member;
	private DeptDTO dept;
}
