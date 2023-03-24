package com.bsep.smart.home.services.keys;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.*;

@Service
@RequiredArgsConstructor
public class CreateKeyPair {

    private final KeyStore keyStore;

    public byte[] execute() throws KeyStoreException {
        KeyPair keyPair = generateKeyPair();
        return keyPair.getPublic().getEncoded();
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
