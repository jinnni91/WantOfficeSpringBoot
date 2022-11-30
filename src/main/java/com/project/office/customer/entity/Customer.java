package com.project.office.customer.entity;

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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_CUSTOMER")
@SequenceGenerator(name = "CUSTOMER_SEQ_GENERATOR",
	sequenceName = "SEQ_CUSTOMER_NO",
	initialValue = 1, allocationSize = 1)
public class Customer {
	
	@Id
	@Column(name = "CUSTOMER_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ_GENERATOR")
	private Long customerNo;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CUSTOMER_EMPLOYEE")
	private String customerEmployee;
	
	@Column(name = "CUSTOMER_PHONE")
	private String customerPhone;
	
	@Column(name = "CUSTOMER_EMAIL")
	private String customerEmail;
	
	@Column(name = "CUSTOMER_DELETE")
	private String customerDelete;
	
	@Column(name = "CUSTOMER_POSITION")
	private String customerPosition;
	
	@Column(name = "CUSTOMER_SHARE")
	private String customerShare;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;
	
	public void update(String customerEmployee, String customerPhone, String customerEmail, String customerPosition, String customerShare) {
		
		this.customerEmployee = customerEmployee;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.customerPosition = customerPosition;
		this.customerShare = customerShare;
		
	}

}
