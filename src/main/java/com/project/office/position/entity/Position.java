package com.project.office.position.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_POSITION")
@SequenceGenerator(name = "POSITION_SEQ_GENERATOR", sequenceName = "SEQ_POSITION_NO", initialValue = 11, allocationSize = 1)
@DynamicInsert
public class Position {
	
	@Id
	@Column(name = "POSITION_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSITION_SEQ_GENERATOR")
	private Long positionNo;
	
	@Column(name = "POSITION_NAME")
	private String positionName;
	
	@Column(name = "POSITION_STATUS")
	private String positionStatus;
	
	@Column(name = "POSITION_DATE")
	private java.util.Date positionDate;
	
	@Column(name = "POSITION_REST")
	private Long positionRest;

}
