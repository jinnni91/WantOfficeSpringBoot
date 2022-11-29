package com.project.office.approval.entity;
import java.sql.Date;
import java.util.List;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "TBL_PROGRESS")
@SequenceGenerator(name = "DP_SEQ_GENERATOR",
sequenceName = "SEQ_DP_NO",
initialValue = 1, allocationSize = 1)
public class Progress {
	
	@Id
	@Column(name = "DP_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DP_SEQ_GENERATOR")
	private Long dpNo;
	
	@Column(name = "DP_PRO_STATUS")
	private String dpStatus;
	
	@Column(name = "DP_COMMENT")
	private String dpcComment;
	
	@Column(name = "DP_DATE")
	private Date dpDate;
	
	@Column(name = "DP_SIGN_DATE")
	private Date dpSignDate;
	
	@Column(name = "DP_ORDER_NO")
	private Long dpOrderNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;
	
//	@ManyToOne
//	@JoinColumn(name = "DOC_NO")
//	private Document document;
	
	@Column(name = "DOC_NO")
	private Long docNo;
	
	
	
	


}
