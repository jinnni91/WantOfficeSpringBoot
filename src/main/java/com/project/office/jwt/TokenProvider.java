package com.project.office.jwt;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.project.office.auth.dto.AuthDTO;
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.dto.TokenDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenProvider {
	
	private static final String AUTHORITIES_KEY = "auth";
	private static final String BEARER_TYPE = "bearer";
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;
	private final Key key;
	
	public TokenProvider(@Value("${jwt.secret}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public TokenDTO generateTokenDTO(MemberDTO member) {
		
		log.info("[TokenProvider] generate Start ===========================");
		
		Claims claims = Jwts
				.claims()
				.setSubject(member.getMemberId());
		
		List<AuthDTO> roles = Collections.singletonList(member.getAuthNo());
		claims.put(AUTHORITIES_KEY, roles);
		
		long now = (new Date()).getTime();
		
		Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
		
		String accessToken = Jwts.builder()
				.setClaims(claims)
				.setExpiration(accessTokenExpiresIn)
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
		
		return new TokenDTO(BEARER_TYPE, member.getMemberName(), accessToken, accessTokenExpiresIn.getTime());
	}

}
