package com.bsep.smart.home.services.keys;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.*;

@Service
@RequiredArgsConstructor
public class CreateKeyPair {

    public KeyPair execute(String algorithm, int keySize) throws KeyStoreException {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(keySize, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
