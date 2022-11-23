package com.project.office.library.entity;

import java.sql.Date;

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


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_LIBRARY")
@SequenceGenerator(name = "LIBRARY_SEQ_GENERATOR", 
		sequenceName = "SEQ_LIBRARY_NO", 
		initialValue = 1, allocationSize = 1)
public class Library {
	
	@Id
	@Column(name = "LIBRARY_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIBRARY_SEQ_GENERATOR")
	private Long libraryNo;
	
	@Column(name = "LIBRARY_TITLE")
	private String libraryTitle;
	
	@Column(name = "LIBRARY_CONTENT")
	private String libraryContent;
	
	@Column(name = "LIBRARY_STATUS")
	private String libraryStatus;
	
	@Column(name = "LIBRARY_CREATE_DATE")
	private java.util.Date libraryCreateDate;
	
	@Column(name = "LIBRARY_UPDATE_DATE")
	private java.util.Date libraryUpdateDate;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;

	public void update(String libraryTitle, String libraryContent, 
			 java.util.Date libraryUpdateDate) {
		

		this.libraryTitle = libraryTitle;
		this.libraryContent = libraryContent;
		this.libraryUpdateDate = libraryUpdateDate;

		
	}
	
	
}
