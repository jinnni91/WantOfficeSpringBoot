package com.project.office.member.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.project.office.dept.entity.Dept;
import com.project.office.position.entity.Position;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_MEM_HISTORY")
@DynamicInsert
public class MemberHistory implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member memberNo;
	
	@ManyToOne
	@JoinColumn(name = "POSITION_NO")
	private Position positionNo;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_NO")
	private Dept deptNo;
	
	@Column(name = "MODIFY_DATE")
	private java.util.Date memberModifyDate;
	

}
