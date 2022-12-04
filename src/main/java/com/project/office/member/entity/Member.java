package com.project.office.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.project.office.auth.entity.Auth;
import com.project.office.dept.entity.Dept;
import com.project.office.position.entity.Position;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_MEMBER")
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "SEQ_MEMBER_NO", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Member {

	@Id
	@Column(name = "MEMBER_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
	private Long memberNo;
	
	@Column(name = "MEMBER_ID")
	private String memberId;
	
	@Column(name = "MEMBER_PASSWORD")
	private String memberPassword;
	
	@Column(name = "MEMBER_NAME")
	private String memberName;
	
	@Column(name = "MEMBER_PHONE")
	private String memberPhone;
	
	@Column(name = "MEMBER_EMAIL")
	private String memberEmail;
	
	@Column(name = "MEMBER_STATUS")
	private String memberStatus;
	
	@Column(name = "MEMBER_REST")
	private Long memberRest;
	
	@ManyToOne
	@JoinColumn(name = "POSITION_NO")
	private Position position;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_NO")
	private Dept dept;
	
	@Column(name = "MEMBER_JOIN_DATE")
	private java.util.Date memberJoinDate;
	
	@ManyToOne
	@JoinColumn(name = "AUTH_NO")
	private Auth auth;
	
	@Column(name = "MEMBER_FILE_URL")
	private String memberFileUrl;

	
	public void update(String memberPhone, String memberEmail, String memberFileUrl) {
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberFileUrl = memberFileUrl;
	}
	
	public void updateMember(String memberId, String memberName, String memberPhone,
			String memberEmail, Position position, Dept dept, Auth auth, String memberStatus) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.position = position;
		this.dept = dept;
		this.auth = auth;
		this.memberStatus = memberStatus;
//		this.memberFileUrl = memberFileUrl;
		
	}
	
	public void updateForCard(String memberName, String memberPhone, String memberEmail) {
		
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		
	}

	
	
}
