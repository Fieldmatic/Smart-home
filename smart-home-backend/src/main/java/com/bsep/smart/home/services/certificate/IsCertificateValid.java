package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.services.keystore.LoadCertificateChain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

@Service
@RequiredArgsConstructor
public class IsCertificateValid {
    private final LoadCertificateChain loadCertificateChain;

    public boolean execute(String alias) {
        try {
            Certificate[] chain = loadCertificateChain.execute(alias);
            X509Certificate certificate = (X509Certificate) chain[0];
            PublicKey issuerPublicKey = chain[1].getPublicKey();
            certificate.checkValidity();
            certificate.verify(issuerPublicKey);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
