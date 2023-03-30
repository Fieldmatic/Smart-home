package com.bsep.smart.home.services.keystore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Service
@RequiredArgsConstructor
public class DeleteEntryByAlias {
    private final KeyStore keyStore;

    private final SaveKeystore saveKeystore;

    public void execute(String alias) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
        keyStore.deleteEntry(alias);
        saveKeystore.execute();
    }
}
