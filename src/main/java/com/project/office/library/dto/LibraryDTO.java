package com.project.office.library.dto;

import java.util.Date;

import com.project.office.member.entity.Member;

import lombok.Data;

@Data
public class LibraryDTO {

	private Long libraryNo;
	private String libraryTitle;
	private String libraryContent;
	private String libraryStatus;
	private Date libraryCreateDate;
	private Date libraryUpdateDate;
	private Member member;
	
}
