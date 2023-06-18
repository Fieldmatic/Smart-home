package com.bsep.smart.home.services.issuer;

import com.bsep.smart.home.model.KeyEntryData;
import com.bsep.smart.home.services.keystore.LoadPrivateKey;
import com.bsep.smart.home.services.keystore.LoadX509Certificate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;

@Service
@RequiredArgsConstructor
public class GetKeyEntryData {
    private final LoadX509Certificate loadX509Certificate;
    private final LoadPrivateKey loadPrivateKey;

    public KeyEntryData execute(String alias) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        X509Certificate certificate = loadX509Certificate.execute(alias);
        PrivateKey privateKey = loadPrivateKey.execute(alias);
        return new KeyEntryData(certificate.getSubjectX500Principal(), privateKey, certificate.getPublicKey());
    }
}
