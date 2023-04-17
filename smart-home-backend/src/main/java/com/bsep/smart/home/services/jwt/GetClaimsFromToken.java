package com.bsep.smart.home.services.jwt;

import com.bsep.smart.home.configProperties.CustomProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetClaimsFromToken {
    private final CustomProperties customProperties;

    public Claims execute(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(customProperties.getJwtSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
