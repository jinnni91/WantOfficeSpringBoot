package com.project.office.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_AUTH")
@SequenceGenerator(name = "AUTH_SEQ_GENERATOR", sequenceName = "SEQ_AUTH_NO", initialValue = 1, allocationSize = 1)
public class Auth {
	
	@Id
	@Column(name = "AUTH_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_SEQ_GENERATOR")
	private Long authNo;
	
	@Column(name = "AUTH_NAME")
	private String authName;

}
