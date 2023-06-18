package com.bsep.smart.home.services.fingerprint;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class GenerateFingerprint {
    private final SecureRandom secureRandom = new SecureRandom();

    public String execute() {
        byte[] randomFgp = new byte[50];
        this.secureRandom.nextBytes(randomFgp);
        return DatatypeConverter.printHexBinary(randomFgp);
    }
}
