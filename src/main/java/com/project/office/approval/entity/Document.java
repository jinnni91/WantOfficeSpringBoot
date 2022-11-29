package com.project.office.approval.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.project.office.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_DOC")
@SequenceGenerator(name = "DOC_SEQ_GENERATOR",
sequenceName = "SEQ_DOC_NO",
initialValue = 1, allocationSize = 1)
public class Document {
	
	@Id
	@Column(name = "DOC_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOC_SEQ_GENERATOR")
	private Long docNo;
	
	@Column(name = "DOC_TITLE")
	private String docTitle;
	
	@Column(name = "DOC_CONTENT")
	private String docContent;
	
	@Column(name = "DOC_UPDATE")
	private Date docUpdate;
	
	@Column(name = "DOC_DELETE")
	private Date docDelete;
	
	@Column(name = "DOC_STATUS")
	private String docStatus;
	
	@Column(name = "DOC_DATE")
	private Date docDate;
	
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "DF_NO")
	private Form form;
//	
//	
//	@OneToMany(mappedBy ="document", fetch = FetchType.LAZY)
//	private List<Progress> progress;
	
	
	@OneToMany
	@JoinColumn(name = "DOC_NO")
	private List<Progress> progress;
	

}
