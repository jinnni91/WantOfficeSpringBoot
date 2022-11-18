package com.project.office.file.dto;

import lombok.Data;

@Data
public class FileDTO {

	private Long fileNo;
	private String fileOrgName;
	private String fileSaveName;
	private String filePath;
	private String fileStatus;
	private String fileType;
	//private Notice notice;
	//private Library library;
	//private Doc doc;
}
