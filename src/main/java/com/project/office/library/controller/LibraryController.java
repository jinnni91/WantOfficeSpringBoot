package com.project.office.library.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.common.paging.PagingButton;
import com.project.office.common.paging.ResponseDTOWithPaging;
import com.project.office.common.paging.pagenation;
import com.project.office.library.dto.LibraryDTO;
import com.project.office.library.service.LibraryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/library")
public class LibraryController {
	
	private final LibraryService libraryService;
	
	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
		
	}
	
	/* 게시글 전체 조회 */
	@GetMapping("/list")
	public ResponseEntity<ResponseDTO> selectLibraryList(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[LibraryController] selectLibraryList start ==========");
		log.info("[LibraryController] page : {}", page);
		
		Page<LibraryDTO> libraryDTOList = libraryService.selectLibraryList(page);
		
		PagingButton pageBtn = pagenation.getPagingButton(libraryDTOList);
		
		log.info("[LibraryController] pageInfo : {}", pageBtn);
		
		ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
		responseDtoWithPaging.setPageBtn(pageBtn);
		responseDtoWithPaging.setData(libraryDTOList.getContent());
		
		log.info("[LibraryController] selectLibraryList End =======");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	/* 게시판 상세 조회 */
	@GetMapping("/librarys/{libraryNo}")
	public ResponseEntity<ResponseDTO> selectLibraryDetail(@PathVariable Long libraryNo) {
		
		return ResponseEntity.ok()
				.body(new ResponseDTO(HttpStatus.OK, "게시판 상세 조회 성공", libraryService.selectLibraryDetail(libraryNo)));
	}
	
	/* 게시판 작성 */
	@PostMapping("/write")
	public ResponseEntity<ResponseDTO> insertLibrary(@ModelAttribute LibraryDTO libraryDTO) {
		
		return ResponseEntity.ok()
				.body(new ResponseDTO(HttpStatus.OK, "게시글 등록 성공", libraryService.insertLibrary(libraryDTO)));
	}
	
	/* 게시글 수정 */
	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateLibarary(@ModelAttribute LibraryDTO libraryDTO) {
		
		return ResponseEntity.ok()
				.body(new ResponseDTO(HttpStatus.OK, "게시글 수정 성공", libraryService.updateLibrary(libraryDTO)));
	}
	
	/* 게시글 삭제*/
	@DeleteMapping("/delete/{libraryNo}")
	public ResponseEntity<ResponseDTO> deleteLibrary(@PathVariable Long libraryNo) {
		
		libraryService.deleteLibrary(libraryNo);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
