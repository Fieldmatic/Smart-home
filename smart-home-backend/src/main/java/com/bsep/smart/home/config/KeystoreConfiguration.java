package com.bsep.smart.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
public class KeystoreConfiguration {

    @Value("${server.ssl.key-store}")
    private String keyStoreName;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    @Bean(name = "keyStore")
    public KeyStore keyStore() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        InputStream inputStream = new FileInputStream(keyStoreName);
        keyStore.load(inputStream, keyStorePassword.toCharArray());
        return keyStore;
    }
}
