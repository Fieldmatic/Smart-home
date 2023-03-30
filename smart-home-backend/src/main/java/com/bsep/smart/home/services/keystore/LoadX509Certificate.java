package com.bsep.smart.home.services.keystore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;

@Service
@RequiredArgsConstructor
public class LoadX509Certificate {
    private final KeyStore keyStore;

    public X509Certificate execute(String alias) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        return (X509Certificate) keyStore.getCertificate(alias);
    }
}
