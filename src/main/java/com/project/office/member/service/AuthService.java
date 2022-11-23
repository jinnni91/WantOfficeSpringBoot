package com.project.office.member.service;

import java.io.IOException;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.office.auth.repository.AuthRepository;
import com.project.office.exception.DuplicatedUsernameException;
import com.project.office.exception.FindIdFailedException;
import com.project.office.exception.LoginFailedException;
import com.project.office.jwt.TokenProvider;
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.dto.TokenDTO;
import com.project.office.member.entity.Member;
import com.project.office.member.repository.MemberRepository;
import com.project.office.position.dto.PositionDTO;
import com.project.office.position.entity.Position;
import com.project.office.position.repository.PositionRepository;
import com.project.office.util.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;
	private final PositionRepository positionRepository;
	
	public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, TokenProvider tokenProvider, PositionRepository positionRepository) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.tokenProvider = tokenProvider;
		this.positionRepository = positionRepository;
	}
	
//	@Value("${image.image-dir}")
//	private String IMAGE_DIR;
//	@Value("${image.image-url}")
//	private String IMAGE_URL;
	
	// 사원 등록
	@Transactional
	public MemberDTO signup(MemberDTO memberDto) {
		log.info("[AuthService] signup Start ===========================");
		log.info("[AuthService] memberDto : {}", memberDto);
//		String imageName = UUID.randomUUID().toString().replace("-", "");
//		String replaceFileName = null;
		
		if(memberRepository.findByMemberEmail(memberDto.getMemberEmail()) != null) {
			log.info("[AuthService] 중복된 이메일입니다.");
			throw new DuplicatedUsernameException("중복된 이메일입니다.");
		} 		
		
//		try {
//			replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, memberDto.getMemberImage());
//			memberDto.setMemberFileUrl(replaceFileName);
//			
//			log.info("[AuthService] replaceFileName : {}", replaceFileName);
			
			Position position = positionRepository.findById(memberDto.getPositionNo().getPositionNo()).orElseThrow(() -> new RuntimeException(""));
			memberDto.setMemberRest(position.getPositionRest());
			
			if(position.getPositionNo() < 5) {
				memberDto.getAuthNo().setAuthNo((long) 2);
			}
			
			memberDto.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
			memberRepository.save(modelMapper.map(memberDto, Member.class));
			
//		} catch (IOException e) {
//			e.printStackTrace();
//			try {
//				FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
			
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

	
	// 아이디 찾기
	public String findId(MemberDTO memberDto) {
		log.info("[AuthService] findId Start ===========================");
		log.info("[AuthService] memberDto : {}", memberDto);
		
		Member member = memberRepository.findByMemberNameAndMemberEmail(memberDto.getMemberName(), memberDto.getMemberEmail())
				.orElseThrow(() -> new FindIdFailedException("입력하신 정보에 해당하는 아이디를 조회할 수 없습니다."));
		
		return member.getMemberId();
	}

}
