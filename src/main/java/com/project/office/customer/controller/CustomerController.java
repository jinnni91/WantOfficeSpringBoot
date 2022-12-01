package com.project.office.customer.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.common.paging.PagingButton;
import com.project.office.common.paging.ResponseDTOWithPaging;
import com.project.office.common.paging.pagenation;
import com.project.office.customer.dto.CustomerDTO;
import com.project.office.customer.service.CustomerService;
import com.project.office.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class CustomerController {
	
	private final CustomerService customerSerive;
	
	public CustomerController(CustomerService customerSerive) {
		this.customerSerive = customerSerive;
	}
	
	/* 거래처 명함 조회(부서 공유 거래처 포함) */
	@GetMapping("/customers")
	public ResponseEntity<ResponseDTO> getCustomerList(@AuthenticationPrincipal MemberDTO memberDTO, @RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[CustomerController] getCustomerList Start =====================");
		log.info("[CustomerController] memberDTO : {}", memberDTO);
		log.info("[CustomerController] page : {}", page);
		
		Page<CustomerDTO> customerDTOList = customerSerive.getCustomerList(memberDTO, page);
		
		PagingButton pageBtn = pagenation.getPagingButton(customerDTOList);
		log.info("[CustomerController] pageBtn : {}", pageBtn);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
	    responseDTOWithPaging.setPageBtn(pageBtn);
	    responseDTOWithPaging.setData(customerDTOList.getContent());
		
		log.info("[CustomerController] getCustomerList End =====================");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "거래처 명함 조회 성공", responseDTOWithPaging));
		
	}
	
	/* 거래처 명함 등록 */
	@PostMapping("/customer")
	public ResponseEntity<ResponseDTO> insertCustomer(@AuthenticationPrincipal MemberDTO member, @RequestBody CustomerDTO customerDTO) {
		
		log.info("[CustomerController] insertCustomer Start =====================");
		log.info("[CustomerController] insertCustomer member : {}", member);
		
		customerDTO.setMember(member);
		
		log.info("[CustomerController] insertCustomer End =====================");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "거래처 명함 등록 성공", customerSerive.insertCustomer(customerDTO)));
		
	}
	
	/* 거래처 명함 수정 */
	@PatchMapping("/customer/modify/{customerNo}")
	public ResponseEntity<ResponseDTO> updateCustomer(@AuthenticationPrincipal MemberDTO member, @PathVariable Long customerNo, @RequestBody CustomerDTO customerDTO) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "거래처 명함 수정 성공", customerSerive.updateCustomer(member, customerNo, customerDTO)));
		
	}
	
	/* 거래처 명함 삭제 */
	@PatchMapping("/customer/delete/{customerNo}")
	public ResponseEntity<ResponseDTO> deleteCustomer(@AuthenticationPrincipal MemberDTO member, @PathVariable Long customerNo, @RequestBody CustomerDTO customerDTO) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "거래처 명함 삭제 성공", customerSerive.deleteCustomer(member, customerNo, customerDTO)));
		
	}
	
}
