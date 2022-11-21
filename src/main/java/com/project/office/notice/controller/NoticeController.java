package com.project.office.notice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.common.paging.PagingButton;
import com.project.office.common.paging.ResponseDTOWithPaging;
import com.project.office.common.paging.pagenation;
import com.project.office.notice.dto.NoticeDTO;
import com.project.office.notice.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
	
	private final NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	/* 공지사항 전체 조회*/
	@GetMapping("/list")
	public ResponseEntity<ResponseDTO> selectNoticeList(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[NoticeController] selectNoticeList Start =========");
		log.info("[NoticeCOntroller] page : {}", page);
		
		Page<NoticeDTO> noticeDTOList = noticeService.selectNoticeList(page);
		
		PagingButton pageBtn = pagenation.getPagingButton(noticeDTOList);
		
		log.info("[ProductController] pageInfo : {}", pageBtn);
		
		ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
		responseDtoWithPaging.setPageBtn(pageBtn);
		responseDtoWithPaging.setData(noticeDTOList.getContent());
		
		log.info("[NoticeController] selectNoticeList End=====");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDtoWithPaging));

	}
	
	/* 공지사항 상세조회 */
	@GetMapping("/notices/{noticeNo}")
	public ResponseEntity<ResponseDTO> selectNoticeDetail(@PathVariable Long noticeNo) {
		
	return ResponseEntity
			.ok()
			.body
			(new ResponseDTO(HttpStatus.OK, "공지사항 상세 조회 성공", noticeService.selectNoticeDetail(noticeNo)));
			
	}
	
	
	
}
