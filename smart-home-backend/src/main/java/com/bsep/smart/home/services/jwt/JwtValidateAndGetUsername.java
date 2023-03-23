package com.bsep.smart.home.services.jwt;

import com.bsep.smart.home.configProperties.CustomProperties;
import com.bsep.smart.home.exception.AuthTokenExpiredException;
import com.bsep.smart.home.exception.AuthTokenInvalidException;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtValidateAndGetUsername {
    private final CustomProperties customProperties;

    public String execute(final String token) {
        try {
            return Jwts.parser().setSigningKey(customProperties.getJwtSecret().getBytes()).parseClaimsJws(token).getBody().getSubject();
        } catch (final ExpiredJwtException ex) {
            throw new AuthTokenExpiredException();
        } catch (final SignatureException | MalformedJwtException | UnsupportedJwtException |
                       IllegalArgumentException ex) {
            throw new AuthTokenInvalidException();
        }
    }
}
