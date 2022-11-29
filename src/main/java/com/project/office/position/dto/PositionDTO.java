package com.project.office.position.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PositionDTO {

	private Long positionNo;
	private String positionName;
	private String positionStatus;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private java.util.Date positionDate;
	private Long positionRest;
	
}
