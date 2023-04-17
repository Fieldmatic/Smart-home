package com.bsep.smart.home.services.jwt;

import com.bsep.smart.home.configProperties.CustomProperties;
import com.bsep.smart.home.exception.AuthTokenExpiredException;
import com.bsep.smart.home.exception.AuthTokenInvalidException;
import com.bsep.smart.home.exception.FingerprintInvalidException;
import com.bsep.smart.home.exception.SigningAlgorithmInvalidException;
import com.bsep.smart.home.services.fingerprint.GenerateFingerprintHash;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtValidateWithFingerprintAndGetUsername {
    private final CustomProperties customProperties;
    private final GenerateFingerprintHash generateFingerprintHash;
    private final GetClaimsFromToken getAllClaimsFromToken;

    private final GetAlgorithmFromToken getAlgorithmFromToken;


    public String execute(final String token, final String fingerprint) {
        try {
            validateSigningAlgorithm(token);
            validateTokenFingerprint(fingerprint, token);
            return Jwts.parser().setSigningKey(customProperties.getJwtSecret().getBytes()).parseClaimsJws(token).getBody().getSubject();
        } catch (final ExpiredJwtException ex) {
            throw new AuthTokenExpiredException();
        } catch (final SignatureException | MalformedJwtException | UnsupportedJwtException |
                       IllegalArgumentException ex) {
            throw new AuthTokenInvalidException();
        }
    }

    private void validateSigningAlgorithm(String token) {
        if (!SignatureAlgorithm.HS512.getValue().equals(getAlgorithmFromToken.execute(token)))
            throw new SigningAlgorithmInvalidException();
    }

    private void validateTokenFingerprint(String fingerprint, String token) {
        if (Objects.isNull(fingerprint)) throw new FingerprintInvalidException();
        String fingerprintHash = generateFingerprintHash.execute(fingerprint);
        String fingerprintFromToken = getFingerprintFromToken(token);
        if (!fingerprintFromToken.equals(fingerprintHash)) throw new FingerprintInvalidException();
    }

    private String getFingerprintFromToken(String token) {
        String fingerprint;
        try {
            final Claims claims = getAllClaimsFromToken.execute(token);
            fingerprint = claims.get("fingerprint", String.class);
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            fingerprint = null;
        }
        return fingerprint;
    }

}