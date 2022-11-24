package com.project.office.approval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.approval.dto.DocumentDTO;
import com.project.office.approval.service.DocumentService;
import com.project.office.common.ResponseDTO;
import com.project.office.common.paging.PagingButton;
import com.project.office.common.paging.ResponseDTOWithPaging;
import com.project.office.common.paging.pagenation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/test")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	
	@GetMapping("/test1")
	public ResponseEntity<ResponseDTO> selectDocumentList(@RequestParam(name="page", defaultValue="1") int page) {
		
		
//		log.info("[@@@@@@@결재조회컨트롤러] page : {}", page);
		Page<DocumentDTO> documentDTOList = documentService.selectDocumentList(page);
		
		
		PagingButton pageBtn = pagenation.getPagingButton(documentDTOList);
//		log.info("[@@@@@@@@@@@@@@결재조회컨트롤러] pageInfo : {}", pageBtn);
		
		
		ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
		responseDtoWithPaging.setPageBtn(pageBtn);
		responseDtoWithPaging.setData(documentDTOList.getContent());
		
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	
	
	
	

}
