package com.project.office.customer.service;

import javax.transaction.Transactional;

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
		
		Page<Customer> customerList = customerRepository.findByMemberAndDeptAndShareAndDelete(memberNo, deptNo, pageable);
		Page<CustomerDTO> customerDTOList = customerList.map(customer -> modelMapper.map(customer, CustomerDTO.class));
		
		log.info("[CustomerService] customerDTOList : {}", customerDTOList);
		
		log.info("[CustomerService] getCustomerList End ===========");
		
		return customerDTOList;
		
	}

	/* 거래처 명함 등록 */
	@Transactional
	public CustomerDTO insertCustomer(CustomerDTO customerDTO) {
		
		log.info("[CustomerService] insertCustomer Start ===========");
		
		customerDTO.setCustomerDelete("N");
		
		customerRepository.save(modelMapper.map(customerDTO, Customer.class));
		
		log.info("[CustomerService] insertCustomer End ===========");
		
		return customerDTO;
	}

	/* 거래처 명함 수정 */
	public CustomerDTO updateCustomer(MemberDTO member, Long customerNo, CustomerDTO customerDTO) {
		
		log.info("[CustomerService] updateCustomer Start ===========");
		log.info("[CustomerService] customerNo : {}", customerNo);
		
		Customer foundCustomer = customerRepository.findByMemberAndCustomerNo(member.getMemberNo(), customerNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 거래처가 존재하지 않습니다."));
		
		foundCustomer.update(customerDTO.getCustomerEmployee(), customerDTO.getCustomerPhone(), customerDTO.getCustomerEmail(), customerDTO.getCustomerPosition(), customerDTO.getCustomerShare());
		
		customerRepository.save(foundCustomer);
		
		log.info("[CustomerService] updateCustomer End ===========");
		
		return customerDTO;
		
	}
	
	/* 거래처 명함 삭제 */
	public CustomerDTO deleteCustomer(MemberDTO member, Long customerNo, CustomerDTO customerDTO) {
		
		log.info("[CustomerService] deleteCustomer Start ===========");
		log.info("[CustomerService] customerNo : {}", customerNo);
		
		Customer foundCustomer = customerRepository.findByMemberAndCustomerNo(member.getMemberNo(), customerNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 거래처가 존재하지 않습니다."));
		
		foundCustomer.setCustomerDelete("Y");
		
		customerRepository.save(foundCustomer);
		
		log.info("[CustomerService] updateCustomer End ===========");
		
		return customerDTO;
		
	}
	
}
