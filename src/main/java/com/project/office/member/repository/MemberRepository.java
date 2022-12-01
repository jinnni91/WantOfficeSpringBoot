package com.project.office.member.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.office.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	// 이메일 중복 여부
	Member findByMemberEmail(String memberEmail);
	
	// 아이디 조회
	Optional<Member> findByMemberId(String memberId);

	// 아이디 찾기
	Optional<Member> findByMemberNameAndMemberEmail(String memberName, String memberEmail);

	// 사원 상세 조회
	Optional<Member> findByMemberNo(Long memberNo);

	// 전체 사원 조회 (관리자 제외)
	@Query("select m from Member m where m.memberNo != :memberNo")
	Page<Member> findAllWithoutAdmin(@Param("memberNo") Long memberNo, Pageable pageable);
	
	/* 사내 명함 조회(본인 제외) */
	@Query("select m from Member m where m.memberNo != :memberNo")
	Page<Member> findAllWithoutMember(@Param("memberNo") Long memberNo, Pageable pageable);

	

	
	
	
}
