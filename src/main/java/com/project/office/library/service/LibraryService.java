package com.project.office.library.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.office.library.dto.LibraryDTO;
import com.project.office.library.entity.Library;
import com.project.office.library.repository.LibraryRepository;
import com.project.office.notice.dto.NoticeDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LibraryService {

	private final LibraryRepository libraryRepository;
	private final ModelMapper modelMapper;
	
	public LibraryService(LibraryRepository libraryRepository, ModelMapper modelMapper) {
		
		this.libraryRepository = libraryRepository;
		this.modelMapper = modelMapper;
	}
	
	
	/* 조회 */
	public Page<LibraryDTO> selectLibraryList(int page) {
		
		log.info("[LibraryService] selectLibraryList start ==========");
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("libraryNo").descending());
		
		Page<Library> libraryList = libraryRepository.findAll(pageable);
		Page<LibraryDTO> libraryDTOList = libraryList.map(library -> modelMapper.map(library, LibraryDTO.class));
		
		log.info("[LibraryService] libraryDTOList : {}", libraryDTOList.getContent());
		
		log.info("[LibraryService] selectLibraryList End =========");
		
		return libraryDTOList;
	}




	/* 상세조회 */
	public LibraryDTO selectLibraryDetail(Long libraryNo) {
		
		LibraryDTO libraryDTO = modelMapper.map(libraryRepository.findById(libraryNo)
				.orElseThrow(() -> new RuntimeException("존재하지 않는 게시판자료입니다.")), LibraryDTO.class);
		
		return libraryDTO;
	}

	/* 작성 */
	@Transactional
	public LibraryDTO insertLibrary(LibraryDTO libraryDTO) {
		
		log.info("[LibraryService] insertLibrary Start ==========");
		log.info("[LibraryService] noticeDTO : {}", libraryDTO);
		
		libraryRepository.save(modelMapper.map(libraryDTO, Library.class));
		
		return libraryDTO;
	}

	/* 수정 */
	@Transactional
	public LibraryDTO updateLibrary(LibraryDTO libraryDTO) {
		
		log.info("[LibraryService] updateLibrary Start =========");
		log.info("[LibraryService] noticeDTO : {}", libraryDTO);
		
		Library oriLibrary = libraryRepository.findById(libraryDTO.getLibraryNo()).orElseThrow(
				() -> new IllegalArgumentException("해당 게시글이 없습니다.. LibraryNo=" + libraryDTO.getLibraryNo()));
		
		oriLibrary.update(libraryDTO.getLibraryTitle(), 
				libraryDTO.getLibraryContent(), 
				libraryDTO.getLibraryUpdateDate());
		
		libraryRepository.save(oriLibrary);
		
		return libraryDTO;
	}

	/* 삭제 */
	public void deleteLibrary(Long libraryNo) {
		
		Library removeLibrary = libraryRepository.findById(libraryNo).get();
		libraryRepository.delete(removeLibrary);
		removeLibrary.setLibraryStatus("N");
	}









	

}


///* 공지사항 전체 조회*/
//public Page<NoticeDTO> selectNoticeList(int page) {
//	
//	log.info("[NoticeService] selectNoticeList start =========");
//	
//	Pageable pageable = PageRequest.of(page -1, 10, Sort.by("noticeNo").descending());
//	
//	Page<Notice> noticeList = noticeRepository.findAll(pageable);
//	Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));
//	
//	log.info("[NoticeService] noticeDTOList : {}", noticeDTOList.getContent());
//	
//	log.info("[NoticeService] selectNoticeList End =====================" );
//	
//	return noticeDTOList;
//}