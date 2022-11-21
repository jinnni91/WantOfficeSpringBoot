package com.project.office.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().antMatchers("/productimgs/**");
	}
	
	// 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		 return http
		         // CSRF 설정 Disable
		         .csrf()
		         	.disable()
		         .sessionManagement()
		             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		         .and()
		             .authorizeRequests()
		             .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		             .antMatchers("/auth/**").permitAll()		       
		             .antMatchers("/api/**").hasAnyRole("USER", "ADMIN") 
		         .and()
		         	.cors()
		         .and()
		         	.build();
		 
	}
	
}
