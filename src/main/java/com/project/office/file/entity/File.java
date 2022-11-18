package com.project.office.file.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.project.office.notice.entity.Notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_FILE")
@SequenceGenerator(name = "FILE_SEQ_GENERATOR", 
					sequenceName = "SEQ_FILE_NO", 
					initialValue = 1, 
					allocationSize = 1)
public class File {

	@Id
	@Column(name = "FILE_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "FILE_SEQ_GENERATOR")
	private Long fileNo;
	
	@Column(name = "FILE_ORGNAME")
	private String fileOrgName;
	
	@Column(name = "FILE_SAVENAME")
	private String fileSaveName;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Column(name = "FILE_STATUS")
	private String fileStatus;
	
	@Column(name = "FILE_TYPE")
	private String fileType;
	
	//@ManyToOne
	//@JoinColumn(name = "NOTICE_NO")
	//private Notice notice;
	
	//@ManyToOne
	//@JoinColumn(name = "LIBRARY_NO")
	//private Library library;
	
	//@ManyToOne
	//@JoinColumn(name = "DOC_NO")
	//private Doc doc;
	
}
