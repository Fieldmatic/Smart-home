package com.bsep.smart.home.services.fingerprint;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class GenerateFingerprintHash {
    public String execute(String fingerprint) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] userFingerprintDigest = digest.digest(fingerprint.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(userFingerprintDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
