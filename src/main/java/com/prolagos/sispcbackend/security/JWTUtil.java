package com.prolagos.sispcbackend.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JWTUtil {
	@Value("SequenciaDeCaracteresParaAssinarToken")  //se funcionar integração com application.propperties: @Value("${jwt.secret}") 
	private String secret;
 	@Value("21600000")    //se funcionar integração com application.propperties: @Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username, String id, Collection<? extends GrantedAuthority> permissao) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		Claims claims = Jwts.claims().setSubject(mapper.writeValueAsString(username));
		claims.put("jti", id);
		
		for (GrantedAuthority auth: permissao) {
			claims.put("roles",mapper.writeValueAsString(auth));
		}
        claims.put("roles", permissao);
		
		return Jwts.builder()
				.setSubject(username)
				.setId(id)
				.setClaims(claims)
				.setIssuer("br.com.prolagos.SisPC")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
