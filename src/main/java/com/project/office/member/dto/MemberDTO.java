package com.project.office.member.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
	private PositionDTO positionNo;
	private DeptDTO deptNo;
	private java.util.Date memberJoinDate;
	private AuthDTO authNo;

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
