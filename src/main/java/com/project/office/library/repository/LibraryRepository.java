package com.project.office.library.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.library.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

	Page<Library> findAll(Pageable pageable);



}
