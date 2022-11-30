package com.project.office.customer.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.office.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/* 거래처 명함 조회(부서 공유 거래처 포함) */
	@EntityGraph(attributePaths= {"member"})
	@Query("select c from Customer c where (c.member.memberNo = :memberNo and c.customerDelete = 'N') or (c.member.dept.deptNo = :deptNo and c.customerDelete = 'N' and c.customerShare = 'Y')")
	Page<Customer> findByMemberAndDeptAndShareAndDelete(@Param("memberNo") Long memberNo, @Param("deptNo") Long deptNo, Pageable pageable);

	
	/* 거래처 명함 수정/삭제를 위한 조회 */
	@EntityGraph(attributePaths= {"member"})
	@Query("select c from Customer c where c.member.memberNo = :memberNo and c.customerNo = :customerNo")
	Optional<Customer> findByMemberAndCustomerNo(@Param("memberNo") Long memberNo, @Param("customerNo") Long customerNo);
	
}
