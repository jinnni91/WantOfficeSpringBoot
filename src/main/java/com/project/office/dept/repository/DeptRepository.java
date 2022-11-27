package com.project.office.dept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.dept.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Long>{

}
