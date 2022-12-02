package com.project.office.off.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.office.member.entity.Member;

public interface OffMemberRepository extends JpaRepository<Member, Long> {

	/* 연차 신청 양식에 나타낼 결재권자 조회 */
	@Query("select m from Member m where m.dept.deptNo = :deptNo and m.auth.authNo = 2")
	Member findByDeptNoAndAuthNo(@Param("deptNo") Long deptNo);
	
}
