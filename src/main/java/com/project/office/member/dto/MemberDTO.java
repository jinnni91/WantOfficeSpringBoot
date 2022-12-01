package com.project.office.member.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.office.auth.dto.AuthDTO;
import com.project.office.dept.dto.DeptDTO;
import com.project.office.position.dto.PositionDTO;

import lombok.Data;

@Data
public class MemberDTO implements UserDetails {

	private Long memberNo;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private String memberStatus;
	private Long memberRest;
	private PositionDTO position;
	private DeptDTO dept;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private java.util.Date memberJoinDate;
	private AuthDTO auth;
	private String memberFileUrl;
	
	private MultipartFile memberImage;

		// security 인증, 인가
		private Collection<? extends GrantedAuthority> authorities;
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return authorities;
		}

		@Override
		public String getPassword() {
			
			return memberPassword;
		}

		@Override
		public String getUsername() {
			
			return memberId;
		}

		@Override
		public boolean isAccountNonExpired() {
			
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			
			return true;
		}

		@Override
		public boolean isEnabled() {
			
			return true;
		}

		
}
