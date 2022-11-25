package com.project.office.member.service;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.office.exception.UserNotFoundException;
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.entity.Member;
import com.project.office.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	public CustomUserDetailsService(MemberRepository memberRepository, ModelMapper modelMapper) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		log.info("[CustomUserDetailService] loadUserByUsername Start ===============");
		log.info("[CustomUserDetailService] memberId : {}", memberId);
		
		return memberRepository.findByMemberId(memberId)
				.map(user -> addAuthorities(user))
				.orElseThrow(() -> new UserNotFoundException(memberId + " 를 찾을 수 없습니다."));	
	}
	
	private MemberDTO addAuthorities(Member member) {
		MemberDTO memberDto = modelMapper.map(member, MemberDTO.class);
		memberDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(member.getAuth().getAuthName())));
		
		return memberDto;
	}

}
