package com.bsep.smart.home.services.keystore;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.*;


@Service
@RequiredArgsConstructor
public class LoadPrivateKey {

    private final KeyStore keyStore;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    public PrivateKey execute(String alias) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        return (PrivateKey) keyStore.getKey(alias, keyStorePassword.toCharArray());
    }
}
