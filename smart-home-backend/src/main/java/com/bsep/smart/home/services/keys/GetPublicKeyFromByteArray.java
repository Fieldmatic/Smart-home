package com.bsep.smart.home.services.keys;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Service
@RequiredArgsConstructor
public class GetPublicKeyFromByteArray {
    public PublicKey execute(byte[] byteRepresentation, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(byteRepresentation);
        return keyFactory.generatePublic(keySpec);
    }
}
