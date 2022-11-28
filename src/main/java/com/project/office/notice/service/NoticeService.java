package com.project.office.notice.service;

import java.sql.Date;

import javax.transaction.Transactional;

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

	/* 공지사항 등록 */
	@Transactional
	public NoticeDTO insertNoticeForAdmin(NoticeDTO noticeDTO) {
		
		log.info("[NoticeService] insertNotice Start ==========");
		log.info("[NoticeService] noticeDTO : {}", noticeDTO);
		
		noticeRepository.save(modelMapper.map(noticeDTO, Notice.class));
		
		return noticeDTO;
	}

	
	/* 공지사항 수정 */
	@Transactional
	public NoticeDTO updateNoticeForAdmin(NoticeDTO noticeDTO) {

		log.info("[NoticeService] updateNotice Start ==========");
		log.info("[NoticeService] updateDTO : {}", noticeDTO);
		
		Notice oriNotice = noticeRepository.findById(noticeDTO.getNoticeNo()).orElseThrow(
				() -> new IllegalArgumentException("해당 공지사항이 없습니다. noticeNo =" + noticeDTO.getNoticeNo()));
		
		oriNotice.update(noticeDTO.getNoticeTitle(),
				noticeDTO.getNoticeContent(),
				noticeDTO.getNoticeUpdate(),
				noticeDTO.getNoticeDelete());
		
		noticeRepository.save(oriNotice);
		
		return noticeDTO;
	}
	
	/* 공지사항 삭제 */
	public void deleteNoticeForAdmin(Long noticeNo) {
		
		Notice deleteNotice = noticeRepository.findById(noticeNo).get();
		noticeRepository.delete(deleteNotice);
//		deleteNotice.setNoticeStatus("N");
//		deleteNotice.setNoticeDelete(new Date(System.currentTimeMillis()));
		
	}


	


	
	
	



}
