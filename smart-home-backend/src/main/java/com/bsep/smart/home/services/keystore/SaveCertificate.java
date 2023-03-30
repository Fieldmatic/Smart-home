package com.bsep.smart.home.services.keystore;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

@Service
@RequiredArgsConstructor
public class SaveCertificate {
    private final KeyStore keyStore;
    @Value("${server.ssl.key-store-password}")
    private final String keyStorePassword;
    private final SaveKeystore saveKeystore;


    public void execute(String alias, PrivateKey privateKey, Certificate[] certificateChain) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
        keyStore.setKeyEntry(alias, privateKey,
                keyStorePassword.toCharArray(), certificateChain);
        saveKeystore.execute();
    }
}
