package com.techno.bootcamp.gatewayservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    public String SECRET_KEY;

    public boolean isInvalid(String token) {
        try{
            Claims claims = decodeJwt(token);
            return claims.getExpiration().before(new Date());
        } catch(JwtException e){
            System.out.println(ExceptionUtils.getStackTrace(e));
            return true;
        }
    }

    public Claims decodeJwt(String token) throws JwtException{
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
