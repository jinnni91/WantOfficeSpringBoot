package com.project.office.customer.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.office.customer.dto.CustomerDTO;
import com.project.office.customer.entity.Customer;
import com.project.office.customer.repository.CustomerRepository;
import com.project.office.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;
	
	public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	/* 거래처 명함 조회(부서 공유 거래처 포함) */
	public Page<CustomerDTO> getCustomerList(MemberDTO memberDTO, int page) {
		
		log.info("[CustomerService] getCustomerList Start ===========");
		
		Pageable pageable = PageRequest.of(page - 1, 4, Sort.by("customerNo").descending());
		
		Long memberNo = memberDTO.getMemberNo();
		Long deptNo = memberDTO.getDept().getDeptNo();
		
		Page<Customer> customerList = customerRepository.findByMemberAndDeptAndShare(memberNo, deptNo, pageable);
		Page<CustomerDTO> customerDTOList = customerList.map(customer -> modelMapper.map(customer, CustomerDTO.class));
		
		log.info("[CustomerService] customerDTOList : {}", customerDTOList);
		
		log.info("[CustomerService] getCustomerList End ===========");
		
		return customerDTOList;
		
	}
	
	

}
