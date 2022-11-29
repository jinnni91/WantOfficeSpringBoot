//package com.project.office.approval.controller;
//
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.project.office.approval.dto.ProgressDTO;
//import com.project.office.approval.service.ProgressService;
//import com.project.office.common.ResponseDTO;
//import com.project.office.common.paging.PagingButton;
//import com.project.office.common.paging.ResponseDTOWithPaging;
//import com.project.office.common.paging.pagenation;
//
//@RestController
//@RequestMapping("/api/aproval")
//public class ProgressController {
//
//	
//	private ProgressService progressService;
//	
//	public ProgressController(ProgressService progressService) {
//		this.progressService=progressService;	
//	}
//	
//	
//	@GetMapping("/list")
//	public ResponseEntity<ResponseDTO> selectProgress(@RequestParam(name="page", defaultValue="1") int page) {
//		
//		
//		Page<ProgressDTO> progressDTOList = progressService.selectProgress(page);
//		
//		
//		PagingButton pageBtn = pagenation.getPagingButton(progressDTOList);
//		
//		
//		ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
//		responseDtoWithPaging.setPageBtn(pageBtn);
//		responseDtoWithPaging.setData(progressDTOList.getContent());
//		
//		
//		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//}
