package com.project.office.member.service;

import java.io.IOException;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.office.auth.entity.Auth;
import com.project.office.dept.entity.Dept;
import com.project.office.exception.DuplicatedUsernameException;
import com.project.office.exception.UserNotFoundException;
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.entity.Member;
import com.project.office.member.repository.MemberRepository;
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
	private final PositionRepository positionRepository;
	
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	@Value("${image.image-url}")
	private String IMAGE_URL;
	
	public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, PositionRepository positionRepository) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.positionRepository = positionRepository;
	}
	
	// 사원 등록
	@Transactional
	public MemberDTO signup(MemberDTO memberDto) {
		
		log.info("[AuthService] signup Start ===========================");
		log.info("[AuthService] memberDto : {}", memberDto);
		String imageName = UUID.randomUUID().toString().replace("-","");
		String replaceFileName = null;

		if(memberDto.getMemberImage() != null) {
			try {
				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, memberDto.getMemberImage());
				memberDto.setMemberFileUrl(replaceFileName);
				
				log.info("[AuthService] replaceFileName : {}", replaceFileName);
				
				Position position = positionRepository.findById(memberDto.getPosition().getPositionNo()).orElseThrow(() -> new RuntimeException(""));
				memberDto.setMemberRest(position.getPositionRest());
				
				memberDto.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
				memberRepository.save(modelMapper.map(memberDto, Member.class));
				
			} catch (IOException e) {			
				e.printStackTrace();
				
				try {
					FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
					
		log.info("[AuthService] signup End ===========================");
		
		return memberDto;
	}
		

	// 전체 사원 목록 조회
	public Page<MemberDTO> memberInfoList(MemberDTO memberDto, int page) {
		log.info("[AuthService] memberInfoList Start ===========================");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("memberNo").descending());
		
		Long memberNo = memberDto.getMemberNo();
		
		Page<Member> memberList = memberRepository.findAllWithoutAdmin(memberNo, pageable);
		Page<MemberDTO> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDTO.class));
		
		log.info("[AuthService] memberDtoList : {}", memberDtoList);
		log.info("[AuthService] memberInfoList End ===========================");
		
		return memberDtoList;
	}

	// 사원 정보 상세 조회
	public MemberDTO selectMemberInfoDetail(Long memberNo) {
		log.info("[AuthService] selectMemberInfoDetail Start ===========================");
		log.info("[AuthService] memberDto : {}", memberNo);
		
		Member member = memberRepository.findByMemberNo(memberNo)
				.orElseThrow(() -> new UserNotFoundException(memberNo + "를 찾을 수 없습니다."));
		
		log.info("[AuthService] member : {}", member);
		
		log.info("[AuthService] selectMemberInfoDetail End ===========================");
		return modelMapper.map(member, MemberDTO.class);
	}
	
	// 사원 정보 수정
	@Transactional
	public MemberDTO updateMember(MemberDTO memberDto) {
		log.info("[AuthService] updateMember Start ===========================");
		log.info("[AuthService] memberDto : {}", memberDto);
		
//		String replaceFileName = null;
//		
//		try {
			Member oriMember = memberRepository.findById(memberDto.getMemberNo()).orElseThrow(
					() ->  new IllegalArgumentException("해당 사원이 존재하지 않습니다. memberNo=" + memberDto.getMemberNo()));
//			String oriFile = oriMember.getMemberFileUrl();
			
//			if(memberDto.getMemberImage() != null) {
//				String fileName = UUID.randomUUID().toString().replace("-", "");
//				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, fileName, memberDto.getMemberImage());
//				memberDto.setMemberFileUrl(replaceFileName);
//				
//				FileUploadUtils.deleteFile(IMAGE_DIR, oriFile);
//			} else {
//				memberDto.setMemberFileUrl(oriFile);
//			}
			
			oriMember.updateMember(
					memberDto.getMemberId(),
					memberDto.getMemberName(),
					memberDto.getMemberPhone(),
					memberDto.getMemberEmail(),
					modelMapper.map(memberDto.getPosition(), Position.class),
					modelMapper.map(memberDto.getDept(), Dept.class),
					modelMapper.map(memberDto.getAuth(), Auth.class),
					memberDto.getMemberStatus()
//					memberDto.getMemberFileUrl()
					);
			memberRepository.save(oriMember);
//		} catch (IOException e) {
//			e.printStackTrace();
//			try {
//				FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
//			}catch (IOException e1) {
//				e.printStackTrace();
//			}
//		}
//		
		log.info("[MemberService] updateMyInfo End ===========");
		return memberDto;
	}

	// 사원 비활성화
	public Object deleteMember(MemberDTO memberDto, Long memberNo) {
		log.info("[AuthService] deleteMember Start ===========================");
		log.info("[AuthService] memberDto : {}", memberDto);
		
		String replaceFileName = null;
		
		try {
			Member oriMember = memberRepository.findById(memberDto.getMemberNo()).orElseThrow(
					() ->  new IllegalArgumentException("해당 사원이 존재하지 않습니다. memberNo=" + memberDto.getMemberNo()));
			String oriFile = oriMember.getMemberFileUrl();
			
			if(memberDto.getMemberImage() != null) {
				String fileName = UUID.randomUUID().toString().replace("-", "");
				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, fileName, memberDto.getMemberImage());
				memberDto.setMemberFileUrl(replaceFileName);
				
				FileUploadUtils.deleteFile(IMAGE_DIR, oriFile);
			} else {
				memberDto.setMemberFileUrl(oriFile);
			}
			
			oriMember.setMemberStatus("N");			
			memberRepository.save(oriMember);
			
		} catch (IOException e) {
			e.printStackTrace();
			try {
				FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			}catch (IOException e1) {
				e.printStackTrace();
			}
		}
		
		log.info("[MemberService] updateMyInfo End ===========");
		return memberDto;
	}
	

}
