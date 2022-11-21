package com.project.office.notice.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.office.notice.dto.NoticeDTO;
import com.project.office.notice.entity.Notice;
import com.project.office.notice.repository.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeService {
	
	private final NoticeRepository noticeRepository;
	private final ModelMapper modelMapper;
	
	public NoticeService(NoticeRepository noticeRepository,  
			ModelMapper modelMapper) {
		this.noticeRepository = noticeRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 공지사항 전체 조회*/
	public Page<NoticeDTO> selectNoticeList(int page) {
		
		log.info("[NoticeService] selectNoticeList start =========");
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("noticeNo").descending());
		
		Page<Notice> noticeList = noticeRepository.findAll(pageable);
		Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));
		
		log.info("[NoticeService] noticeDTOList : {}", noticeDTOList.getContent());
		
		log.info("[NoticeService] selectNoticeList End =====================" );
		
		return noticeDTOList;
	}
	
	/* 공지사항 상세 조회 */
	public NoticeDTO selectNoticeDetail(Long noticeNo) {
		
		NoticeDTO noticeDTO = modelMapper.map(noticeRepository.findById(noticeNo)
				.orElseThrow(() -> new RuntimeException("존재하지 않는 공지사항입니다.")), NoticeDTO.class);
		
		return noticeDTO;
	}

	



	


	
	
	



}
