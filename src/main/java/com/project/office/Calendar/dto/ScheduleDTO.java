package com.project.office.Calendar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleDTO {

	private Long scheduleNo;
	private String scheduleTitle;
	private Date scheduleStart;
	private Date scheduleEnd;
	private Date scheduleCre;
	private Date scheduleUpd;
	private String scheduleSort;
	private String scheduleColor;
	private String schedulePlace;
	private String scheduleContent;
//	private Member memberName;
//	private Dept deptNo;
}
