package com.bsep.smart.home.services.keys;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Service
@RequiredArgsConstructor
public class AddPrivateKey {

    private final KeyStore keyStore;

    @Value("${server.ssl.key-store}")
    private String keyStoreName;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    public void execute(String alias, Key privateKey, char[] password) {
        try {
            keyStore.setKeyEntry(alias, privateKey, "password".toCharArray(), null);
            keyStore.store(new FileOutputStream(keyStoreName), keyStorePassword.toCharArray());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
