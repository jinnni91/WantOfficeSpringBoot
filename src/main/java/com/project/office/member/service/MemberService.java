package com.project.office.member.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.office.exception.UserNotFoundException;

import com.project.office.member.dto.MemberDTO;

import com.project.office.member.entity.Member;
import com.project.office.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;

	}
	
	// 사원 정보 조회
	public MemberDTO selectMyInfo(String memberId) {
		log.info("[MemberService] selectMyInfo Start ===========================");
		log.info("[MemberService] memberDto : {}", memberId);
		
		Member member = memberRepository.findByMemberId(memberId)
	            .orElseThrow(() -> new UserNotFoundException(memberId + "를 찾을 수 없습니다."));

		log.info("[MemberService] member : {}", member);
		
		log.info("[MemberService] selectMyInfo End ===========================");
		return modelMapper.map(member, MemberDTO.class);
	}
	
	

}
