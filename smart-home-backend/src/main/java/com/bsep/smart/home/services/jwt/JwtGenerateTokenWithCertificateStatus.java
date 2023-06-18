package com.bsep.smart.home.services.jwt;

import com.bsep.smart.home.configProperties.CustomProperties;
import com.bsep.smart.home.model.UserCertificateStatus;
import com.bsep.smart.home.services.fingerprint.GenerateFingerprintHash;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class JwtGenerateTokenWithCertificateStatus {
    private final CustomProperties customProperties;
    private final GenerateFingerprintHash generateFingerprintHash;

    public String execute(final String email, final UserCertificateStatus certificateStatus, final String role, final long expirationMilliseconds, final String fingerprint) {
        return Jwts.builder()
                .setSubject(email)
                .claim("certificateStatus", certificateStatus)
                .claim("role", role)
                .claim("fingerprint", generateFingerprintHash.execute(fingerprint))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMilliseconds))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, customProperties.getJwtSecret().getBytes())
                .compact();
    }
}
