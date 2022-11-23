package com.project.office.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	// 이메일 중복 여부
	Member findByMemberEmail(String memberEmail);
	
	// 아이디 조회
	Optional<Member> findByMemberId(String memberId);

	// 아이디 찾기
	Optional<Member> findByMemberNameAndMemberEmail(String memberName, String memberEmail);

	
	
	
}
