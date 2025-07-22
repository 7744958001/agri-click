package com.agritech.agritech_app.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "/05HV5ChmIRp3jrTlm1dCd0bJbbqW1a0+liTrMEQ67A=nm";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    @SuppressWarnings("deprecation")
	public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @SuppressWarnings("deprecation")
	public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    @SuppressWarnings("deprecation")
	private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
