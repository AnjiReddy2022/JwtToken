package com.filpcart.customer.webToken;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET = "[4DB5807310E995F143F32C1311F0305FBBD21E468FAB8FACC5D605F4490FA764FA93A6FCA822B84728E16666C3AFE7D7625B4A29CA8156D9E6E3AC665021F777]\r\n"
			+ "]";
	private static final long VALIDITY = TimeUnit.MINUTES.toMillis(30);

	public String generateToken(UserDetails userDetails) {
		Map<String, String> claims = new HashMap<>();
		return Jwts.builder()
				.claims(claims)
				.subject(userDetails.getUsername())
				.issuedAt(Date.from(Instant.now()))
				.expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
				.signWith(generateKey())
				.compact();
	}

	private SecretKey generateKey() {
		byte[] decodeKey = Base64.getDecoder().decode(SECRET);
		return Keys.hmacShaKeyFor(decodeKey);
	}

}
