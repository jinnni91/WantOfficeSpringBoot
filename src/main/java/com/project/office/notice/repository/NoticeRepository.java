package com.project.office.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

	Page<Notice> findAll(Pageable pageable);
	
	
	
}
