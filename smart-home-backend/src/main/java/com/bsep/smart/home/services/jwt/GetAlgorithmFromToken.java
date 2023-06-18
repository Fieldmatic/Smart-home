package com.bsep.smart.home.services.jwt;

import com.bsep.smart.home.configProperties.CustomProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAlgorithmFromToken {

    private final CustomProperties customProperties;

    public String execute(String token) {
        String algorithm;
        try {
            algorithm = Jwts.parser()
                    .setSigningKey(customProperties.getJwtSecret().getBytes())
                    .parseClaimsJws(token)
                    .getHeader()
                    .getAlgorithm();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            algorithm = null;
        }
        return algorithm;
    }
}
