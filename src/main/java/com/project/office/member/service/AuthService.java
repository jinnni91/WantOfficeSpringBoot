package com.project.office.member.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.office.exception.DuplicatedUsernameException;
import com.project.office.jwt.TokenProvider;
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.dto.TokenDTO;
import com.project.office.member.entity.Member;
import com.project.office.member.exception.LoginFailedException;
import com.project.office.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;
	
	public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, TokenProvider tokenProvider) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.tokenProvider = tokenProvider;
	}
	
	// 사원 등록
	@Transactional
	public MemberDTO signup(MemberDTO memberDto) {
		log.info("[AuthService] signup Start ===========================");
		log.info("[AuthService] memberDto : {}", memberDto);
		
		if(memberRepository.findByMemberEmail(memberDto.getMemberEmail()) != null) {
			log.info("[AuthService] 중복된 이메일입니다.");
			throw new DuplicatedUsernameException("중복된 이메일입니다.");
		}
		
		memberDto.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
		memberRepository.save(modelMapper.map(memberDto, Member.class));
		
		log.info("[AuthService] signup End ===========================");
		return memberDto;
	}
	
	// 로그인
	public TokenDTO login(MemberDTO memberDto) {
		log.info("[AuthService] login Start ===========================");
		log.info("[AuthService] memberDto : {}", memberDto);
		
		// 아이디 조회
		Member member = memberRepository.findByMemberId(memberDto.getMemberId())
				.orElseThrow(() -> new LoginFailedException("아이디 또는 비밀번호가 잘못되었습니다."));
		
		// 비밀번호 매칭
		if(!passwordEncoder.matches(memberDto.getMemberPassword(), member.getMemberPassword())) {
			log.info("[AuthService] Password Match Failed");
			throw new LoginFailedException("아이디 또는 비밀번호가 잘못되었습니다.");
		}
		
		// 토큰 발급
		TokenDTO tokenDto = tokenProvider.generateTokenDTO(modelMapper.map(member, MemberDTO.class));
		log.info("[AuthService] tokenDto : {}", tokenDto);
		
		log.info("[AuthService] login End ===========================");
		
		return tokenDto;
	}

}
