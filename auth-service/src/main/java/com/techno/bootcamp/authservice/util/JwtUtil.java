package com.techno.bootcamp.authservice.util;

import com.techno.bootcamp.authservice.model.request.ReqEncodeUserJwtDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    public String createUserJwt(ReqEncodeUserJwtDto request) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
        Key signingKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

        long expMills = System.currentTimeMillis() + 3600000L;
        Date exp = new Date(expMills);

        return Jwts.builder()
                .setSubject(request.getId())
                .claim("idUser", request.getId())
                .claim("email", request.getEmail())
                .claim("role", request.getRole())
                .setIssuer("technocenter")
                .setAudience("technocenter")
                .signWith(signingKey,signatureAlgorithm)
                .setExpiration(exp).compact();
    }
}
