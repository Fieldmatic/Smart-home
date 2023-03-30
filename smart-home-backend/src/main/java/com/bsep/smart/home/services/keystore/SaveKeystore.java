package com.bsep.smart.home.services.keystore;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Service
@RequiredArgsConstructor
public class SaveKeystore {
    private final KeyStore keyStore;
    @Value("${server.ssl.key-store-password}")
    private final String keyStorePassword;
    @Value("${server.ssl.key-store}")
    private final String keyStorePath;

    public void execute() throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
        keyStore.store(new FileOutputStream(keyStorePath), keyStorePassword.toCharArray());
    }
}
