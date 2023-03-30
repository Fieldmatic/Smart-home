package com.bsep.smart.home.services.keystore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;

@Service
@RequiredArgsConstructor
public class LoadCertificateChain {
    private final KeyStore keyStore;

    public Certificate[] execute(String alias) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        return keyStore.getCertificateChain(alias);
    }
}
